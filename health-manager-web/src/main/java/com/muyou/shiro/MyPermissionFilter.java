package com.muyou.shiro;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPermissionFilter extends AuthorizationFilter {

	private static final Logger log = LoggerFactory.getLogger(MyPermissionFilter.class);

	private static Integer factor;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
		Subject subject = this.getSubject(request, response);
		String[] perms = (String[]) ((String[]) o);
		boolean isPermitted = true;
		if (subject.getPrincipal() == null) {
			if (FilterUtil.isAjax(request)) {
				log.info("未登录或登录时间过长,是ajax！");
				factor = 0;
			} else {
				log.info("未登录或登录时间过长,不是ajax！");
				this.saveRequestAndRedirectToLogin(request, response);
			}
			// isPermitted = false;
		} else {
			if (perms != null && perms.length > 0) {
				if (perms.length == 1) {
					if (!subject.isPermitted(perms[0])) {
						isPermitted = false;
					}
				} else if (!subject.isPermittedAll(perms)) {
					isPermitted = false;
				}
			}
			if (!isPermitted) {
				if (FilterUtil.isAjax(request)) {
					log.info("没有该权限，并且是Ajax请求");
					factor=1;
				} else {
					return isPermitted;
				}
			}
		}
		return isPermitted;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		switch (factor) {
		case 0:
			resultMap.put("success", false);
			resultMap.put("message", "您还未登录或登录时间过长,请重新登录!");
			FilterUtil.out(response, resultMap);
			break;
		case 1:
			resultMap.put("success", false);
			resultMap.put("message", "抱歉,您没有该权限!看就看，你点它干什么...");
			FilterUtil.out(response, resultMap);
			break;
		default:
			resultMap.put("success", false);
			resultMap.put("message", "不好,有内奸,取消交易,撤!");
			FilterUtil.out(response, resultMap);
			break;
		}
		return false;
	}

}
