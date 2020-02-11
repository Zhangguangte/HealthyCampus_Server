package com.muyou.sso.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.sso.form.LoginForm;
import com.muyou.sso.pojo.UserVo;
import com.muyou.sso.service.LoginService;

/**
 * 登录控制器
 * 
 * @author 木友茶
 *
 */
@Controller
@RequestMapping("/users")
public class LoginController {

	@Autowired
	private LoginService loginService;

	// 登录
	@RequestMapping("/login")
	@ResponseBody
	public UserVo login(@RequestBody(required = false) LoginForm form) throws ServiceException {
		if (form.validate())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo userVo = loginService.userLogin(form);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		return userVo;
	}

}
