package com.muyou.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.LoginForm;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.util.StringUtil;
import com.muyou.sso.form.RegisterFrom;
import com.muyou.sso.pojo.UserVo;
import com.muyou.sso.service.UserService;
/**
 * 用户控制器
 * @author 木友茶
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private Logger log= LoggerFactory.getLogger(UserController.class);
    
	@Autowired
	private UserService userService;

	// 登录
	@RequestMapping("/login")
	@ResponseBody
	public UserVo userLogin(@RequestBody(required = false) LoginForm form) throws ServiceException {
		if (form.validate())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo userVo = userService.userLogin(form);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		return userVo;
	}
	
	// 注册
	@RequestMapping("/register")
	@ResponseBody
	public UserVo createUser(@RequestBody(required = false) RegisterFrom form) throws ServiceException {
		if (form.validate())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo userVo = userService.createUser(form);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return userVo;
	}
	
	// 注销
	@RequestMapping("/logout")
	@ResponseBody
	public ResponseBuilder userLogout(HttpServletRequest request) throws ServiceException {
		String user = (String) request.getAttribute("USER");
		if (StringUtil.isEmpty(user))
			log.error("用户注销失败");
		userService.userLogout(user);
		return ResponseBuilder.SUCCESS;
	}
		
		
	// 获得用户信息:账号
	@RequestMapping("/information/{account}")
	@ResponseBody
	public UserVo getUserInformation(@PathVariable("account") String account) throws ServiceException {
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
			HttpServletRequest request) throws ServiceException {
		
		String user = (String) request.getAttribute("USER");
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
	public ResponseBuilder searchPhone(@RequestBody(required = false) RegisterFrom form) throws ServiceException {
		if (StringUtil.isEmpty(form.getPhone()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		int result = userService.searchPhone(form);
		if (0 != result)
			throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
		return ResponseBuilder.SUCCESS;
	}

	
}
