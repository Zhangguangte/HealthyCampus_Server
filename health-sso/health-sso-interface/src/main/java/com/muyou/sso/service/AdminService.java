package com.muyou.sso.service;

import com.muyou.common.form.LoginForm;
import com.muyou.common.pojo.Result;

public interface AdminService {

	/**
	 * 管理员登录
	 * @param dataForm
	 * @return
	 */
	Result<Object> adminLogin(LoginForm dataForm);

	/**
	 * 管理员注销
	 * @param token
	 */
	int adminLogout(String token);
	
	/**
	 * 解锁屏幕
	 * @param account
	 * @param password
	 * @param token
	 * @return
	 */
	Result<Object> unLock(String account,String password,String token);

}
