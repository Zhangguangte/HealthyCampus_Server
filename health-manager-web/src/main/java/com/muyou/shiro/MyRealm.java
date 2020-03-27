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
		System.out.println("MyRealm:AuthorizationInfo");
		// 获取用户账号
		String account = principal.getPrimaryPrincipal().toString();
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 获得授权角色
		authorizationInfo.setRoles(adminService.getRoles(account));
		// 获得授权权限
		authorizationInfo.setStringPermissions(adminService.getPermissions(account));
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
		System.out.println("MyRealm:doGetAuthenticationInfo");
		// 获取用户名密码
		String account = token.getPrincipal().toString();
		TbAdmin admin = adminService.getAdminByUsername(account);
		if (admin != null) {
			// 得到用户账号和密码存放到authenticationInfo中用于Controller层的权限判断
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin.getAccount(), admin.getPassword(),
					admin.getName());
			return authenticationInfo;
		} else {
			return null;
		}
	}

}
