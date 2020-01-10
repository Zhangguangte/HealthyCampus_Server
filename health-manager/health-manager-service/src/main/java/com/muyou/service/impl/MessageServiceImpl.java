package com.muyou.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.ChatForm;
import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.MessageListVo;
import com.muyou.common.vo.NoticeVo;
import com.muyou.common.vo.ResponseBuilder;
import com.muyou.common.vo.UserVo;
import com.muyou.mapper.TbMessageListMapper;
import com.muyou.mapper.TbMessageMapper;
import com.muyou.mapper.TbNewsMapper;
import com.muyou.mapper.TbNewsUserMapper;
import com.muyou.mapper.TbUserMapper;
import com.muyou.pojo.TbMessage;
import com.muyou.pojo.TbMessageList;
import com.muyou.pojo.TbNews;
import com.muyou.pojo.TbUser;
import com.muyou.service.MessageService;

@Service("messageServiceImpl")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private TbMessageMapper messageMapper;

	@Autowired
	private TbMessageListMapper messagelistMapper;

	@Autowired
	private TbUserMapper userMapper;
	
	@Autowired
	private TbNewsMapper newsMapper;
	
	@Autowired
	private TbNewsUserMapper newsUserMapper;
	
	// 现存最后一条消息
	@Override
	public List<MessageListVo> lastMessage(String userId) {
		List<TbMessage> list = messageMapper.lastMessage(userId);
		if (null == list || list.size() == 0)
			return null;
		List<MessageListVo> result = new ArrayList<MessageListVo>();
		MessageListVo messageListVo;
		for (TbMessage message : list) {
			messageListVo = new MessageListVo();
			messageListVo.setType(message.getType());
			messageListVo.setContent(message.getContent());
			messageListVo.setRoom_id(message.getRoomId());
			messageListVo.setBelongId(message.getFromId());
			messageListVo.setFile_path(message.getFilePath());
			messageListVo.setCreate_time(message.getCreateTime());
			messageListVo.setSentStatus(message.getSentstatus());
			if (null != message.getList()) {
				messageListVo.setUnread(message.getList().getUnread());
				messageListVo.setAnother_name(message.getList().getAnotherName());
			}
			result.add(messageListVo);
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
		return new MessageListVo(roomid);
	}

	// 删除房间号
	@Override
	public void deleteRoomId(RequestForm requestForm) {
		messagelistMapper.deleteRoomId(requestForm.getContent());
	}

	// 查询房间号
	@Override
	public List<MessageListVo> getDoctorRoom(String userId) {
		List<TbMessageList> list = messagelistMapper.getDoctorRoom(userId);
		if (null == list || list.size() == 0)
			return null;
		List<MessageListVo> result = new LinkedList<>();
		MessageListVo messageList;
		for (TbMessageList m : list) {
			messageList = new MessageListVo();
			messageList.setRoom_id(m.getRoomId());
			messageList.setAnother_name(m.getAnotherName());
			result.add(messageList);
		}
		return result;
	}

	// 所有消息根据房间号
	@Override
	public List<MessageListVo> allChatByRoomId(RequestForm requestForm, String userId) {
		List<TbMessage> list = messageMapper.allChatByRoomId(requestForm.getQuest_id(), 15 * requestForm.getRow());
		if (null == list || list.size() == 0)
			return null;
		List<MessageListVo> result = new ArrayList<MessageListVo>();
		MessageListVo messageListVo;
		for (TbMessage message : list) {
			messageListVo = new MessageListVo();
			messageListVo.setType(message.getType());
			messageListVo.setContent(message.getContent());
			messageListVo.setRoom_id(message.getRoomId());
			messageListVo.setBelongId(message.getFromId());
			messageListVo.setFile_path(message.getFilePath());
			messageListVo.setCreate_time(message.getCreateTime());
			messageListVo.setSentStatus(message.getSentstatus());
			System.out.println("message.Stirng:"+message.toString());
			System.out.println("*****************************");
			result.add(messageListVo);
		}
		messagelistMapper.lookmessage(requestForm.getQuest_id(), userId);
		return result;
	}

	// 所有消息根据两个用户ID
	@Override
	public List<MessageListVo> allChatByUid(RequestForm requestForm, String userId) throws ServiceException {
		TbMessageList messageList = messagelistMapper.searchRoomId(requestForm.getQuest_id(), userId);
		if(messageList ==null)
			throw new ServiceException(ResponseBuilder.ERROR_ROOM_NOT_FOUND);
		requestForm.setQuest_id(messageList.getRoomId());
		return allChatByRoomId(requestForm, userId);
	}

	// 添加消息（文字、录音、图片）
	@Override
	public void insertContent(ChatForm chatForm, String userId) {
		messageMapper.insertContent(chatForm, userId);
		messagelistMapper.addUnread(chatForm.getRoom_id(), userId);
	}

	// 添加消息（名片）
	@Override
	public UserVo insertCard(ChatForm chatForm, String userId) {
		TbUser user = userMapper.findByAccount(chatForm.getUser_id());
		if(null == user)
			return null;
		messageMapper.insertCard(user.getAvatar() + "," + user.getNickname() + "," + user.getAccount(), chatForm, userId);
		messagelistMapper.addUnread(chatForm.getRoom_id(), userId);
		
		UserVo userVo = new UserVo();
		userVo.setId(user.getId());
		userVo.setAccount(user.getAccount());
		userVo.setNickname(user.getNickname());
		userVo.setAvatar(user.getAvatar());
		userVo.setDescription(user.getDescription());
		userVo.setLocation(user.getLocation());
		userVo.setSex(user.getSex());
		userVo.setPhone(user.getPhone());
		userVo.setInitials(user.getInitials());
		userVo.setCreateTime(user.getCreateTime());
		userVo.setLastmodifyTime(user.getLastModifyTime());
		return userVo;
	}

	// 房间号
	@Override
	public MessageListVo searchRoomid(RequestForm requestForm, String userId) {
		TbMessageList m = messagelistMapper.searchRoomId(requestForm.getQuest_id(), userId);
		if(null == m)
			return null;
		MessageListVo messageList = new MessageListVo();
		messageList.setRoom_id(m.getRoomId());
		messageList.setAnother_name(m.getAnotherName());
		return messageList;
	}

	/*
	 * 通知系列
	 */

//	 获得所有通知
	@Override
	public List<NoticeVo> getAllNotice(RequestForm requestForm, String userId) {
		
		if (requestForm.getQuest_id() == null || "".equals(requestForm.getQuest_id())) {
			requestForm.setQuest_id("0000-00-00 00:00:00") ;
		}
		System.out.println("requestForm.getQuest_id() "+requestForm.getQuest_id() );
		System.out.println("userId"+userId);
		List<TbNews> list = newsMapper.getAllNotice(userId, requestForm.getQuest_id());
		if (null == list || list.size() == 0)
			return null;
		List<NoticeVo> result = new LinkedList<NoticeVo>();
		NoticeVo noticeVo;
		for (TbNews news : list) {
			noticeVo = new NoticeVo();
			noticeVo.setId(news.getId()+"");
			noticeVo.setCreate_time(news.getCreateTime());
			noticeVo.setContent(news.getContent());
			noticeVo.setStatus(news.getNewsUser().getStatus());
			noticeVo.setNoticeType(news.getUserType());
			result.add(noticeVo);
		}
		System.out.println(result.size());
		return result;
	}
	// 清空通知
	@Override
	public void clearNotice(String userId) {
		newsUserMapper.clearNotice(userId);
	}

	// 删除通知
	@Override
	public void deleteNotice(RequestForm requestForm, String userId) {
		newsUserMapper.deleteNotice(requestForm.getQuest_id(), userId);
	}
	// 查看通知
	@Override
	public void lookNotice(RequestForm requestForm, String userId) {
		newsUserMapper.lookNotice(requestForm.getQuest_id(), requestForm.getContent(), userId);
	}

}
