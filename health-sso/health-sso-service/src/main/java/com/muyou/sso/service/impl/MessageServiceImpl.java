package com.muyou.sso.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.ChatForm;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.mapper.TbMessageListMapper;
import com.muyou.mapper.TbMessageMapper;
import com.muyou.mapper.TbUserMapper;
import com.muyou.pojo.TbMessage;
import com.muyou.pojo.TbMessageList;
import com.muyou.pojo.TbUser;
import com.muyou.sso.pojo.MessageListVo;
import com.muyou.sso.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private TbMessageMapper messageMapper;

	@Autowired
	private TbMessageListMapper messagelistMapper;

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${MESSAGE_LAST}")
	private String MESSAGE_LAST;

	@Value("${MESSAGE_ROOM}")
	private String MESSAGE_ROOM;

	@Value("${MESSAGE_LIST}")
	private String MESSAGE_LIST;

	// 现存最后一条消息
	@Override
	public List<MessageListVo> lastMessage(String userId) {

		try {
			String json = jedisClient.hget(MESSAGE_LAST, userId);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, MessageListVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<TbMessage> list = messageMapper.lastMessage(userId);
		if (null == list || list.size() == 0)
			return null;
		List<MessageListVo> result = new ArrayList<MessageListVo>();
		for (TbMessage message : list) {
			result.add(new MessageListVo(message));
		}

		try {
			jedisClient.hset(MESSAGE_LAST, userId, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 创建房间号
	@Override
	public MessageListVo createRoom(RequestForm requestForm, String userid) {
		List<TbMessageList> list = messagelistMapper.getDoctorRoom(userid);
		if (null != list && list.size() == 2)
			return null;
		String roomid = UUID.randomUUID().toString().replace("-", "0").substring(0, 8);
		messagelistMapper.addDoctorMessageList(requestForm.getContent(), roomid, userid);
		try {
			jedisClient.hdel(MESSAGE_ROOM, userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new MessageListVo(roomid);
	}

	// 删除房间号
	@Override
	public void deleteRoomId(RequestForm requestForm) {
		messagelistMapper.deleteRoomId(requestForm.getContent());
		try {
			jedisClient.hdel(MESSAGE_ROOM, requestForm.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询房间号
	@Override
	public List<MessageListVo> getDoctorRoom(String userId) {
		try {
			String json = jedisClient.hget(MESSAGE_ROOM, userId);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, MessageListVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<TbMessageList> list = messagelistMapper.getDoctorRoom(userId);
		if (null == list || list.size() == 0)
			return null;
		List<MessageListVo> result = new LinkedList<>();
		for (TbMessageList m : list) {
			result.add(new MessageListVo(m));
		}

		try {
			jedisClient.hset(MESSAGE_ROOM, userId, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 所有消息根据房间号
	@Override
	public List<MessageListVo> allChatByRoomId(RequestForm requestForm, String userId) {

		try {
			List<String> json = jedisClient.lrange(MESSAGE_LIST + ":" + requestForm.getQuest_id(),
					requestForm.getRow() * 15, (requestForm.getRow() + 1) * 15);
			if (null != json && json.size() > 0) {
				List<MessageListVo> list = new LinkedList<MessageListVo>();
				for (String string : json) {
					list.add(JsonUtils.jsonToPojo(string, MessageListVo.class));
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<TbMessage> list = messageMapper.allChatByRoomId(requestForm.getQuest_id(), 15 * requestForm.getRow());
		if (null == list || list.size() == 0)
			return null;
		List<MessageListVo> result = new ArrayList<MessageListVo>();
		for (TbMessage message : list) {
			result.add(new MessageListVo(message));
		}
		messagelistMapper.lookmessage(requestForm.getQuest_id(), userId);

		try {
			for (int i = result.size() - 1; i > -1; i--) {
				jedisClient.lpush(MESSAGE_LIST + ":" + requestForm.getQuest_id(),
						JsonUtils.objectToJson(result.get(i)));
			}
			if (requestForm.getRow() == 0)
				jedisClient.hdel(MESSAGE_LAST, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 所有消息根据两个用户ID
	@Override
	public List<MessageListVo> allChatByUid(RequestForm requestForm, String userId) throws ServiceException {
		TbMessageList messageList = messagelistMapper.searchRoomId(requestForm.getQuest_id(), userId);
		if (messageList == null)
			throw new ServiceException(ResponseBuilder.ERROR_ROOM_NOT_FOUND);
		requestForm.setQuest_id(messageList.getRoomId());
		return allChatByRoomId(requestForm, userId);
	}

	// 添加消息（文字、录音、图片）
	@Override
	public void insertContent(ChatForm chatForm, String userId) {

		TbMessage message = new TbMessage();
		message.setFromId(userId);
		message.setType(chatForm.getType());
		message.setContent(chatForm.getContent());
		message.setCreateTime(chatForm.getCreate_time());
		message.setRoomId(chatForm.getRoom_id());
		message.setFilePath(chatForm.getFile_path());
		message.setSentstatus("SENT");
		messageMapper.insert(message);
		// messageMapper.insertContent(chatForm, userId);
		messagelistMapper.addUnread(chatForm.getRoom_id(), userId);

		try {
			jedisClient.lpush(MESSAGE_LIST + ":" + chatForm.getRoom_id(), JsonUtils.objectToJson(message));
			jedisClient.hdel(MESSAGE_LAST, userId);
		} catch (Exception e) {
			// jedisClient.ltrim(chatForm.getRoom_id(), 0, -1);
			e.printStackTrace();
		}

	}

	// 添加消息（名片）
	@Override
	public void insertCard(ChatForm chatForm, String userId) {
		TbUser user = userMapper.findByAccount(chatForm.getUser_id());

		TbMessage message = new TbMessage();
		message.setFromId(userId);
		message.setType(chatForm.getType());
		message.setContent(user.getAvatar() + "," + user.getNickname() + "," + user.getAccount());
		message.setCreateTime(chatForm.getCreate_time());
		message.setRoomId(chatForm.getRoom_id());
		message.setFilePath(chatForm.getFile_path());
		message.setSentstatus("SENT");
		messageMapper.insert(message);
		messagelistMapper.addUnread(chatForm.getRoom_id(), userId);

		try {
			jedisClient.lpush(MESSAGE_LIST + ":" + chatForm.getRoom_id(), JsonUtils.objectToJson(message));
			jedisClient.hdel(MESSAGE_LAST, userId);
		} catch (Exception e) {
			jedisClient.ltrim(chatForm.getRoom_id(), 0, -1);
			e.printStackTrace();
		}
	}

	// 房间号
	@Override
	public MessageListVo searchRoomid(RequestForm requestForm, String userId) {
		TbMessageList m = messagelistMapper.searchRoomId(requestForm.getQuest_id(), userId);
		if (null == m)
			return null;
		return new MessageListVo(m);
	}

}
