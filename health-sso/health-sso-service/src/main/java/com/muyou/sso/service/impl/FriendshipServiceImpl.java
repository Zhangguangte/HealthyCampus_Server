package com.muyou.sso.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.DateUtil;
import com.muyou.common.util.JsonUtils;
import com.muyou.mapper.TbMessageListMapper;
import com.muyou.mapper.TbMessageMapper;
import com.muyou.mapper.TbRequestMapper;
import com.muyou.mapper.TbUserMapper;
import com.muyou.pojo.TbRequest;
import com.muyou.pojo.TbUser;
import com.muyou.sso.pojo.AddressListVo;
import com.muyou.sso.pojo.RequestFriendVo;
import com.muyou.sso.service.FriendshipService;

@Service
public class FriendshipServiceImpl implements FriendshipService {


	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private TbRequestMapper requestMapper;

	@Autowired
	private TbMessageMapper messageMapper;

	@Autowired
	private TbMessageListMapper messageListMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${FRIEND_LIST}")
	private String FRIEND_LIST;

	@Value("${FRIEND_REQUEST}")
	private String FRIEND_REQUEST;

	// 获得所有好友，通讯录
	@Override
	public List<AddressListVo> allFriend(String userId) {

		try {
			String json = jedisClient.hget(FRIEND_LIST, userId);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, AddressListVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<TbUser> list = userMapper.allFriend(userId);
		if (null == list)
			return null;
		
		List<AddressListVo> result = new ArrayList<AddressListVo>();
		for (TbUser user : list) {
			result.add(new AddressListVo(user));
		}
		try {
			jedisClient.hset(FRIEND_LIST, userId, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 获得所有好友请求，好友通知
	@Override
	public List<RequestFriendVo> requestFriends(String userId) {

		try {
			String json = jedisClient.hget(FRIEND_REQUEST, userId);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, RequestFriendVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<TbRequest> list = requestMapper.requestFriends(userId);
		if (null == list)
			return null;
		List<RequestFriendVo> result = new ArrayList<RequestFriendVo>();
		for (TbRequest request : list) {
			result.add(new RequestFriendVo(request));
		}
		
		try {
			jedisClient.hset(FRIEND_REQUEST, userId, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 清空好友通知
	@Override
	public void clearRequestFriends(String userId) {
		requestMapper.clearRequestFriends1(userId);
		requestMapper.clearRequestFriends2(userId);
		try {
			jedisClient.hdel(FRIEND_REQUEST, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 发送好友请求，添加好友消息
	@Override
	public void sendRequestFriend(RequestForm requestForm, String userid) {
		int count;
		count = requestMapper.isExistRequst(userid, requestForm.getQuest_id());
		if (count > 0)
			requestMapper.updateRequestTime(requestForm.getContent(), requestForm.getQuest_id(),
					DateUtil.getStringDate(), userid);
		else
			requestMapper.sendRequestFriend(requestForm.getQuest_id(), requestForm.getContent(),
					DateUtil.getStringDate(), userid);
		try {
			jedisClient.hdel(FRIEND_REQUEST, userid);
			jedisClient.hdel(FRIEND_REQUEST, requestForm.getQuest_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 同意好友请求，新好友
	@Override
	public void saveRequestFriend(RequestForm requestForm, String userid) {
		requestMapper.saveRequestFriend(userid, requestForm.getQuest_id());
		String roomid = UUID.randomUUID().toString().replace("-", "0").substring(0, 8);
		messageListMapper.addMessageList(requestForm.getMap().get("f_nickname"), roomid, userid);
		messageListMapper.addMessageList(requestForm.getMap().get("getTimeState"), roomid, requestForm.getQuest_id());
		messageMapper.newfriend(DateUtil.getStringDate(), roomid);
		try {
			jedisClient.hdel(FRIEND_REQUEST, userid);
			jedisClient.hdel(FRIEND_REQUEST, requestForm.getQuest_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 拒绝好友请求，新好友
	@Override
	public void refuseRequestFriend(RequestForm requestForm, String userid) {
		requestMapper.refuseRequestFriend(userid, requestForm.getQuest_id());
		try {
			jedisClient.hdel(FRIEND_REQUEST, userid);
			jedisClient.hdel(FRIEND_REQUEST, requestForm.getQuest_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
