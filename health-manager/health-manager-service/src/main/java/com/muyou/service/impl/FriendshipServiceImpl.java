package com.muyou.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.common.util.DateUtil;
import com.muyou.common.vo.AddressListVo;
import com.muyou.common.vo.RequestFriendVo;
import com.muyou.mapper.TbFriendshipMapper;
import com.muyou.mapper.TbMessageListMapper;
import com.muyou.mapper.TbMessageMapper;
import com.muyou.mapper.TbRequestMapper;
import com.muyou.mapper.TbUserMapper;
import com.muyou.pojo.TbRequest;
import com.muyou.pojo.TbUser;
import com.muyou.service.FriendshipService;

@Service("friendshipServiceImpl")
public class FriendshipServiceImpl implements FriendshipService {

	@Autowired
	private TbFriendshipMapper friendshipMapper;
	
	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private TbRequestMapper requestMapper;

	@Autowired
	private TbMessageMapper messageMapper;

	@Autowired
	private TbMessageListMapper messageListMapper;

	// 获得所有好友，通讯录
	@Override
	public List<AddressListVo> allFriend(String userId) {
		List<TbUser> list = userMapper.allFriend(userId);
		if (null == list)
			return null;
		List<AddressListVo> result = new ArrayList<AddressListVo>();
		AddressListVo addressListVo;
		for (TbUser user : list) {
			addressListVo = new AddressListVo();
			addressListVo.setNickname(user.getNickname());
			addressListVo.setAccount(user.getAccount());
			addressListVo.setSortLetters(user.getInitials());
			result.add(addressListVo);
		}
		return result;
	}

	// 获得所有好友请求，好友通知
	@Override
	public List<RequestFriendVo> requestFriends(String userId) {
		List<TbRequest> list = requestMapper.requestFriends(userId);
		if (null == list)
			return null;
		List<RequestFriendVo> result = new ArrayList<RequestFriendVo>();
		RequestFriendVo requestFriend;
		for (TbRequest request : list) {
			requestFriend = new RequestFriendVo();
			requestFriend.setId(request.getId() + "");
			requestFriend.setUser_id(request.getUserId());
			requestFriend.setRequest_user_id(request.getFriend().getId());
			requestFriend.setNickname(request.getFriend().getNickname());
			requestFriend.setContent(request.getContent());
			requestFriend.setStatus(request.getStatus());
			// requestFriend.setHeadImg(request.getFriend().getAvatar());
			result.add(requestFriend);
		}
		return result;
	}

	// 清空好友通知
	@Override
	public void clearRequestFriends(String userId) {
		requestMapper.clearRequestFriends1(userId);
		requestMapper.clearRequestFriends2(userId);
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
	}

	// 同意好友请求，新好友
	@Override
	public void saveRequestFriend(RequestForm requestForm, String userid) {
		requestMapper.saveRequestFriend(userid, requestForm.getQuest_id());
		String roomid = UUID.randomUUID().toString().replace("-", "0").substring(0, 8);
		messageListMapper.addMessageList(requestForm.getMap().get("f_nickname"), roomid, userid);
		messageListMapper.addMessageList(requestForm.getMap().get("getTimeState"), roomid, requestForm.getQuest_id());
		messageMapper.newfriend(DateUtil.getStringDate(), roomid);
	}

	// 拒绝好友请求，新好友
	@Override
	public void refuseRequestFriend(RequestForm requestForm, String userid) {
		requestMapper.refuseRequestFriend(userid, requestForm.getQuest_id());
	}

}
