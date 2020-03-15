package com.muyou.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.muyou.pojo.TbAdmin;
import com.muyou.service.AdminService;

public class MyRealm extends AuthorizingRealm {

	private static final Logger log = LoggerFactory.getLogger(MyRealm.class);

	@Autowired
	private AdminService adminService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

		System.out.println("MyRealm" + 4444);
		// 获取用户名
		String username = principal.getPrimaryPrincipal().toString();
		System.out.println(adminService);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 获得授权角色
		authorizationInfo.setRoles(adminService.getRoles(username));
		// 获得授权权限
		authorizationInfo.setStringPermissions(adminService.getPermissions(username));
		return authorizationInfo;
	}

	/**
	 * 先执行登录验证
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("MyRealm" + 5555);
		// 获取用户名密码
		String username = token.getPrincipal().toString();
		TbAdmin admin = adminService.getAdminByUsername(username);
		if (admin != null) {
			// 得到用户账号和密码存放到authenticationInfo中用于Controller层的权限判断 第三个参数随意不能为null
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin.getName(), admin.getPassword(),
					admin.getName());
			return authenticationInfo;
		} else {
			return null;
		}
	}

}
