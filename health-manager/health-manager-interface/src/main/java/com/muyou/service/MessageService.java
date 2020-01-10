package com.muyou.service;

import java.util.List;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.ChatForm;
import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.MessageListVo;
import com.muyou.common.vo.NoticeVo;
import com.muyou.common.vo.UserVo;

public interface MessageService {

	// 现存最后一条消息
	public List<MessageListVo> lastMessage(String userId);

	// 创建房间号
	public MessageListVo createRoom(RequestForm requestForm, String userid);
	
	// 删除房间号
	public void deleteRoomId(RequestForm requestForm);

	// 查询房间号
	public List<MessageListVo> getDoctorRoom(String userId);

	// 所有消息根据房间号
	public List<MessageListVo> allChatByRoomId(RequestForm requestForm, String userId);

	// 所有消息根据两个用户ID
	public List<MessageListVo> allChatByUid(RequestForm requestForm, String userId) throws ServiceException;

	// 添加消息（文字、录音、图片）
	public void insertContent(ChatForm chatForm, String userId);

	// 添加消息（名片）
	public UserVo insertCard(ChatForm chatForm, String userId);

	// 房间号
	public MessageListVo searchRoomid(RequestForm requestForm, String userId);

	// 获得所有通知
	public List<NoticeVo> getAllNotice(RequestForm requestForm, String userId);

	// 清空通知
	public void clearNotice(String userId);

	// 删除通知
	public void deleteNotice(RequestForm requestForm, String userId);

	
	// 查看通知
	public void lookNotice(RequestForm requestForm, String userId);

}
