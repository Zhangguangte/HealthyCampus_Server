package com.muyou.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.annotation.SystemControllerLog;
import com.muyou.common.constant.HealthConstant;
import com.muyou.common.form.LoginForm;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.CookieUtils;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.ResultUtil;
import com.muyou.service.LoginService;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	@SystemControllerLog(description = "管理员登录系统")
	public Result<Object> adminLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		// 用户登录
		Result<Object> result = loginService.adminLogin(new LoginForm(username, password));
		if (null != result) { // 登录成功
			// 验证成功
			Subject subject = SecurityUtils.getSubject();
			// MD5加密
			String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
			UsernamePasswordToken token = new UsernamePasswordToken(username, md5Pass);
			CookieUtils.setCookie(request, response, HealthConstant.TOKEN_KEY, (String)result.getResult());
			try {
				subject.login(token);
				return new ResultUtil<Object>().setData(null);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultUtil<Object>().setErrorMsg("用户名或密码错误");
			}
		} else { // 失败
			return new ResultUtil<Object>().setErrorMsg("用户名或密码错误");
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	@SystemControllerLog(description = "管理员注销系统")
	public Object adminLogout(HttpServletRequest request, HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
        subject.logout();
		// 获得客服端的Cookie
		String token = CookieUtils.getCookieValue(request, HealthConstant.TOKEN_KEY);
		// 不存在，返回
		if (StringUtils.isBlank(token))
			return new ResultUtil<Object>().setData(null);
		// 删除Redis内的用户数据
		loginService.adminLogout(token);
		// 删除客服端的Cookie
		CookieUtils.deleteCookie(request, response, HealthConstant.TOKEN_KEY);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/unlock", method = RequestMethod.GET)
	@ResponseBody
	public Object unLock(String username, String password, String token) throws UnsupportedEncodingException {
		// 转化为中文
		username = new String(username.getBytes("iso8859-1"), "utf-8");
		return loginService.unLock(username, password, token);
	}
	
	@RequestMapping(value="/token/{token}",method = RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		return JsonUtils.objectToJson(loginService.getAdminByToken(token));
		
	}
	
}
