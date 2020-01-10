package com.muyou.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.muyou.common.util.JsonUtil;
import com.muyou.common.util.StringUtil;
import com.muyou.common.vo.ResponseBuilder;
import com.muyou.service.UserService;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String user = request.getHeader("User");
//		System.out.println("user:" + user + "\n");
		if (StringUtil.isEmpty(user)) {
			// 重置response
			response.reset();
			response.setStatus(ResponseBuilder.STATUS_UNAUTHORIZED);
			// 设置编码格式
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.getWriter().write(JsonUtil.toJson(ResponseBuilder.ERROR_AUTHORIZATION_FAIL));
			return false;
		} else
			return true;
	}

}
