package com.muyou.server.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.glassfish.jersey.internal.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import com.muyou.common.util.StringUtil;
import com.muyou.common.vo.UserVo;
import com.muyou.server.requestWrapper.AuthorizationHttpServletRequestWrapper;
import com.muyou.service.UserService;

public class AuthorizationFilter implements Filter {

	public static final List<Pattern> noFilterPattern;

	static {
		noFilterPattern = new ArrayList<Pattern>();
		noFilterPattern.add(Pattern.compile("^(/users/login)"));
		noFilterPattern.add(Pattern.compile("^(/users/register)"));
		// noFilterPattern.add(Pattern.compile("^(messages/upPicture)"));
		// noFilterPattern.add(Pattern.compile("^(messages/upload2IM)"));
	}

	
	@Autowired
	private UserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String servletPath = request.getServletPath();
		System.out.println("请求路径过滤信息路径为：" + servletPath);
		
		//是否已经登陆
		for (Pattern pattern : noFilterPattern) {
			if (pattern.matcher(servletPath).find()) {
				chain.doFilter(arg0, arg1);
				return;
			}
		}
		
		String authCode = request.getHeader("Authorization");
//		System.out.println("authCode:" + authCode + "\n");
		if (StringUtil.isEmpty(authCode)) {
			chain.doFilter(request, response);
			return ;
		}
		String[] tempArray = authCode.split(" ");
		if (tempArray.length != 2) {
			chain.doFilter(request, response);
			return ;
		}

		authCode = new String(Base64.decode(tempArray[1].getBytes()));
		tempArray = authCode.split(":");
		if (tempArray.length != 2) {
			chain.doFilter(request, response);
			return ;
		}

		UserVo user = userService.findByUsername(tempArray[0]);
		if (user == null || !user.getPassword().equals(tempArray[1])) {
			chain.doFilter(request, response);
			return ;
		}
		System.out.println("User：" + user.getId());
		System.out.println("+++++++++++++++++++++++" + "\n");

		HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);
		requestWrapper.addHeader("User", user.getId());
		chain.doFilter(requestWrapper, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}


	class HeaderMapRequestWrapper extends HttpServletRequestWrapper {

		public HeaderMapRequestWrapper(HttpServletRequest request) {
			super(request);
		}

		private Map<String, String> headerMap = new HashMap<String, String>();

		public void addHeader(String name, String value) {
			headerMap.put(name, value);
		}

		@Override
		public String getHeader(String name) {
			String headerValue = super.getHeader(name);
			if (headerMap.containsKey(name)) {
				headerValue = headerMap.get(name);
			}
			return headerValue;
		}

		@Override
		public Enumeration<String> getHeaderNames() {
			List<String> names = Collections.list(super.getHeaderNames());
			for (String name : headerMap.keySet()) {
				names.add(name);
			}
			return Collections.enumeration(names);
		}

		@Override
		public Enumeration<String> getHeaders(String name) {
			List<String> values = Collections.list(super.getHeaders(name));
			if (headerMap.containsKey(name)) {
				values.add(headerMap.get(name));
			}
			return Collections.enumeration(values);
		}
	}

}
