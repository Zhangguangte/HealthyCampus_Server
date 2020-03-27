package com.muyou.sso.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.glassfish.jersey.internal.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.muyou.common.util.StringUtil;
import com.muyou.sso.pojo.UserVo;
import com.muyou.sso.service.TokenService;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 输出路径
		String servletPath = request.getServletPath();
		System.out.println("请求路径拦截信息路径为：" + servletPath);

		// 获得认证头数据
		String authCode = request.getHeader("Authorization");
		if (StringUtil.isEmpty(authCode)) {
			return false;
		}

		// 分割数据,获得账号和令牌
		String[] tempArray = authCode.split(" ");
		
		if (tempArray.length != 3) {
			return false;
		}

		// 解析，获得用户ID
		authCode = new String(Base64.decode(tempArray[1].getBytes()));
		
		// 根据用户ID和令牌获得用户数据
		UserVo user = tokenService.getUserByToken(authCode, tempArray[2]);
		
		if (null == user)
			return false;
		
//		System.out.println(user.getId());
//		System.out.println(tempArray[2]);
		request.setAttribute("USER", user.getId());
		request.setAttribute("TOKEN", tempArray[2]);
		
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
