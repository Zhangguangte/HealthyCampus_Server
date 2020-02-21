package com.muyou.sso.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.form.LoginForm;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.CookieUtils;
import com.muyou.common.util.ResultUtil;
import com.muyou.sso.service.AdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> adminLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {

		int gtResult = 1;

		// 验证成功
		if (gtResult == 1) {
			// 用户登录
			Result<Object> result = adminService.adminLogin(new LoginForm(username, password));
			
			if (200 == result.getCode()) {	//登录成功
				CookieUtils.setCookie(request, response, TOKEN_KEY, (String)result.getResult());
				return new ResultUtil<Object>().setData(null);
			} else {		//失败
				return new ResultUtil<Object>().setErrorMsg("用户名或密码错误");
			}
		} else {
			// 验证失败
			return new ResultUtil<Object>().setErrorMsg("验证失败");
		}
	}

	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	@ResponseBody
    public Object adminLogout(HttpServletRequest request,HttpServletResponse response, String callback){

		//获得客服端的Cookie
		String token = CookieUtils.getCookieValue(request, TOKEN_KEY);
		
		//不存在，返回
		if(StringUtils.isBlank(token))
			return new ResultUtil<Object>().setData(null);
		
		//删除Redis内的用户数据
		adminService.adminLogout(token);
		
		//删除客服端的Cookie
		CookieUtils.deleteCookie(request, response, TOKEN_KEY);
		
		if (StringUtils.isNotBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(null);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		
		return new ResultUtil<Object>().setData(null);
    }
	
	@RequestMapping(value = "/unlock",method = RequestMethod.GET)
	@ResponseBody
    public Object unLock(String username,String password,String token,String callback ) throws UnsupportedEncodingException{

		//转化为中文
		username = new String(username.getBytes("iso8859-1"), "utf-8");
		
		Result<Object> result =  adminService.unLock(username,password,token);
		
		if (StringUtils.isNotBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		
		return result;
    }
	
	
}
