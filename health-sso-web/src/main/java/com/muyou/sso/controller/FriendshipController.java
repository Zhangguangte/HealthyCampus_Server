package com.muyou.sso.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.util.StringUtil;
import com.muyou.sso.pojo.AddressListVo;
import com.muyou.sso.pojo.RequestFriendVo;
import com.muyou.sso.service.FriendshipService;

/**
 * 好友控制器
 * 
 * @author 木友茶
 *
 */
@Controller
@RequestMapping("/friends")
public class FriendshipController {

	@Autowired
	private FriendshipService friendshipService;

	// 获得所有好友，通讯录
	@RequestMapping("/allFriend")
	@ResponseBody
	public List<AddressListVo> allFriend(HttpServletRequest request) throws ServiceException {
		String user = (String) request.getAttribute("USER");
		if (StringUtils.isBlank(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<AddressListVo> result = friendshipService.allFriend(user);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_FRIEND_NOT_FOUND);
		return result;
	}

	// 获得所有好友请求，好友通知
	@RequestMapping("/requestFriends")
	@ResponseBody
	public List<RequestFriendVo> requestFriends(HttpServletRequest request) throws ServiceException {

		String user = (String) request.getAttribute("USER");
		if (StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<RequestFriendVo> result = friendshipService.requestFriends(user);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}

	// 清空好友通知
	@RequestMapping("/clearRequestFriends")
	@ResponseBody
	public ResponseBuilder clearRequestFriends(HttpServletRequest request) throws ServiceException {
		String user = (String) request.getAttribute("USER");
		if (StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		friendshipService.clearRequestFriends(user);
		return ResponseBuilder.SUCCESS;
	}

	// 发送好友请求，添加好友消息
	@RequestMapping("/sendRequestFriend")
	@ResponseBody
	public ResponseBuilder sendRequestFriend(@RequestBody RequestForm requestForm, HttpServletRequest request)
			throws ServiceException {

		String user = (String) request.getAttribute("USER");
		if (StringUtil.isEmpty(requestForm.getQuest_id()) || StringUtil.isEmpty(requestForm.getContent())
				|| StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		friendshipService.sendRequestFriend(requestForm, user);
		return ResponseBuilder.SUCCESS;
	}

	// 同意好友请求，新好友
	@RequestMapping("/saveRequestFriend")
	@ResponseBody
	public ResponseBuilder saveRequestFriend(@RequestBody RequestForm requestForm, HttpServletRequest request)
			throws ServiceException {
		String user = (String) request.getAttribute("USER");
		if (StringUtil.isEmpty(requestForm.getQuest_id()) || StringUtil.isEmpty(user) || null == requestForm.getMap())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		friendshipService.saveRequestFriend(requestForm, user);
		return ResponseBuilder.SUCCESS;
	}

	// 拒绝好友请求，新好友
	@RequestMapping("/refuseRequestFriend")
	@ResponseBody
	public ResponseBuilder refuseRequestFriend(@RequestBody RequestForm requestForm, HttpServletRequest request)
			throws ServiceException {
		String user = (String) request.getAttribute("USER");
		if (StringUtil.isEmpty(requestForm.getQuest_id()) || StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		friendshipService.refuseRequestFriend(requestForm, user);
		return ResponseBuilder.SUCCESS;
	}
}
