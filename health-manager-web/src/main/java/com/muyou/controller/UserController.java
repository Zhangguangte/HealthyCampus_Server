package com.muyou.controller;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.LoginForm;
import com.muyou.common.form.RegisterFrom;
import com.muyou.common.form.RequestForm;
import com.muyou.common.util.StringUtil;
import com.muyou.common.vo.ResponseBuilder;
import com.muyou.common.vo.UserVo;
import com.muyou.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	// 登录
	@RequestMapping("/login")
	@ResponseBody
	public UserVo login(@RequestBody(required=false) LoginForm form) throws ServiceException {
		if (form.validate())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo userVo = userService.login(form);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		return userVo;
	}

	// 注册
	@RequestMapping("/register")
	@ResponseBody
	public UserVo register(@RequestBody(required=false) RegisterFrom form) throws ServiceException {
		System.out.println("form"+form);
		if (form.validate())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo userVo = userService.register(form);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return userVo;
	}

	// 获得用户信息:账号
	@RequestMapping("/information/{account}")
	@ResponseBody
	public UserVo getUserInformation(@PathVariable("account") String account) throws ServiceException {
		// System.out.println("account" + account);
		if (StringUtil.isEmpty(account))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo userVo = userService.getUserInformation(account);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		return userVo;
	}

	// 查询用户:账号、电话
	@RequestMapping("/searchUser/{searchWords}")
	@ResponseBody
	public UserVo searchUser(@PathVariable("searchWords") String searchWords,
			@RequestHeader(value = "User", required = false) String user) throws ServiceException {
		System.out.println("searchWords:" + searchWords);
		System.out.println("user:" + user);
		if (StringUtil.isEmpty(searchWords))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);

		UserVo userVo = userService.searchUser(searchWords, user);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		return userVo;
	}

	// 查询用户:根据主键
	@RequestMapping("/searchUser")
	@ResponseBody
	public UserVo searchUser(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo user = userService.searchUser(requestForm);
		if (null == user)
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		return user;
	}

	// 查询用户:根据电话
	@RequestMapping("/register/searchPhone")
	@ResponseBody
	public ResponseBuilder searchPhone(@RequestBody(required=false) RegisterFrom form) throws ServiceException {
		System.out.println("form"+form);
		if (StringUtil.isEmpty(form.getPhone()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		userService.searchPhone(form);
		return ResponseBuilder.SUCCESS;
	}

}
