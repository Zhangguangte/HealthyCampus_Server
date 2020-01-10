package com.muyou.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.ChatForm;
import com.muyou.common.form.RequestForm;
import com.muyou.common.util.StringUtil;
import com.muyou.common.vo.MessageListVo;
import com.muyou.common.vo.NoticeVo;
import com.muyou.common.vo.ResponseBuilder;
import com.muyou.service.MessageService;

@Controller
@RequestMapping("/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;

	// 现存最后一条消息
	@RequestMapping("/lastMessage")
	@ResponseBody
	public List<MessageListVo> lastMessage(@RequestHeader("User") String user) throws ServiceException {
		if (StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<MessageListVo> result = messageService.lastMessage(user);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_MESSAGE_NOT_FOUND);
		return result;
	}

	// 查询房间号
	@RequestMapping("/getDoctorRoom")
	@ResponseBody
	public List<MessageListVo> getDoctorRoom(@RequestHeader("User") String user) throws ServiceException {
		if (StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<MessageListVo> result = messageService.getDoctorRoom(user);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_ROOM_NOT_FOUND);
		return result;
	}

	// 查询房间号
	@RequestMapping("/createRoom")
	@ResponseBody
	public MessageListVo createRoom(@RequestBody RequestForm form, @RequestHeader("User") String user)
			throws ServiceException {
		if (StringUtil.isEmpty(user) || StringUtil.isEmpty(form.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		MessageListVo result = messageService.createRoom(form, user);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_MAX_ROOM);
		return result;
	}

	// 删除房间号
	@RequestMapping("/deleteRoomId")
	@ResponseBody
	public ResponseBuilder deleteRoomId(@RequestBody RequestForm form) throws ServiceException {
		if (StringUtil.isEmpty(form.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		messageService.deleteRoomId(form);
		return ResponseBuilder.SUCCESS;
	}

	// 所有消息根据房间号
	@RequestMapping("/allChatByRoomId")
	@ResponseBody
	public List<MessageListVo> allChatByRoomId(@RequestBody RequestForm form, @RequestHeader("User") String user)
			throws ServiceException {
		if (StringUtil.isEmpty(user) || StringUtil.isEmpty(form.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<MessageListVo> result = messageService.allChatByRoomId(form, user);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_MESSAGE_NOT_FOUND);
		return result;
	}

	// 所有消息根据两个用户ID
	@RequestMapping("/allChatByUid")
	@ResponseBody
	public List<MessageListVo> allChatByUid(@RequestBody RequestForm form, @RequestHeader("User") String user)
			throws ServiceException {
		if (StringUtil.isEmpty(user) || StringUtil.isEmpty(form.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<MessageListVo> result = messageService.allChatByUid(form, user);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_MESSAGE_NOT_FOUND);
		return result;
	}

	// 添加消息（文字、录音、图片）
	@RequestMapping("/insertContent")
	@ResponseBody
	public ResponseBuilder insertContent(@RequestBody ChatForm form, @RequestHeader("User") String user)
			throws ServiceException {
		if (!form.validate())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		messageService.insertContent(form, user);
		return ResponseBuilder.SUCCESS;
	}

	// 添加消息（名片）
	@RequestMapping("/insertCard")
	@ResponseBody
	public ResponseBuilder insertCard(@RequestBody ChatForm form, @RequestHeader("User") String user)
			throws ServiceException {
		if (!form.validate())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		messageService.insertCard(form, user);
		return ResponseBuilder.SUCCESS;
	}

	// 房间号
	@RequestMapping("/searchRoomid")
	@ResponseBody
	public MessageListVo searchRoomid(@RequestBody RequestForm form, @RequestHeader("User") String user)
			throws ServiceException {
		if (StringUtil.isEmpty(form.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		MessageListVo result = messageService.searchRoomid(form, user);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_MESSAGE_NOT_FOUND);
		return result;
	}

	/*
	 * 通知系列
	 */

	// 获得所有通知
	@RequestMapping("/getAllNotice")
	@ResponseBody
	public List<NoticeVo> getAllNotice(@RequestBody RequestForm form, @RequestHeader("User") String user)
			throws ServiceException {
		if (StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<NoticeVo> result = messageService.getAllNotice(form, user);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_NEWS_NOT_FOUND);
		return result;
	}

	// 清空通知
	@RequestMapping("/clearNotice")
	@ResponseBody
	public ResponseBuilder clearNotice(@RequestHeader("User") String user) throws ServiceException {
		if (StringUtil.isEmpty(user))
			return ResponseBuilder.ERROR_INVALID_PARAMETER;
		messageService.clearNotice(user);
		return ResponseBuilder.SUCCESS;
	}

	// 删除通知
	@RequestMapping("/deleteNotice")
	@ResponseBody
	public ResponseBuilder deleteNotice(@RequestBody RequestForm form, @RequestHeader("User") String user)
			throws ServiceException {
		if (StringUtil.isEmpty(user)||StringUtil.isEmpty(form.getQuest_id()))
			return ResponseBuilder.ERROR_INVALID_PARAMETER;
		messageService.deleteNotice(form, user);
		return ResponseBuilder.SUCCESS;
	}

	// 查看通知
	@RequestMapping("/lookNotice")
	@ResponseBody
	public ResponseBuilder lookNotice(@RequestBody RequestForm form, @RequestHeader("User") String user) throws ServiceException {
		if (StringUtil.isEmpty(user)||StringUtil.isEmpty(form.getQuest_id())||StringUtil.isEmpty(form.getContent()))
			return ResponseBuilder.ERROR_INVALID_PARAMETER;
		messageService.lookNotice(form,user);
		return ResponseBuilder.SUCCESS;
	}

}
