package com.muyou.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.AddressListVo;
import com.muyou.common.vo.RequestFriendVo;

public interface FriendshipService {

	// 获得所有好友，通讯录
	public List<AddressListVo> allFriend(String userId);

	// 获得所有好友请求，好友通知
	public List<RequestFriendVo> requestFriends(String userId);

	// 清空好友通知
	public void clearRequestFriends(String userId);

	// 发送好友请求，添加好友消息
	public void sendRequestFriend(RequestForm requestForm, String userid);

	// 同意好友请求，新好友
	public void saveRequestFriend(RequestForm requestForm, String userid);

	// 拒绝好友请求，新好友
	public void refuseRequestFriend(RequestForm requestForm, String userid);

}
