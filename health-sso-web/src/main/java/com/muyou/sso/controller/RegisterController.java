package com.muyou.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.sso.form.RegisterFrom;
import com.muyou.sso.pojo.UserVo;
import com.muyou.sso.service.RegisterService;

/**
 * 注册控制器
 * 
 * @author 木友茶
 *
 */
@Controller
@RequestMapping("/users")
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	// 注册
	@RequestMapping("/register")
	@ResponseBody
	public UserVo register(@RequestBody(required = false) RegisterFrom form) throws ServiceException {
		System.out.println("form" + form);
		if (form.validate())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		UserVo userVo = registerService.createUser(form);
		if (null == userVo)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return userVo;
	}

}
