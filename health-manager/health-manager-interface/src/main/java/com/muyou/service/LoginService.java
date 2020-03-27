package com.muyou.service;

import com.muyou.common.form.LoginForm;
import com.muyou.common.pojo.Result;

public interface LoginService {

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
	 * @param username
	 * @param password
	 * @param token
	 * @return
	 */
	Result<Object> unLock(String username,String password,String token);
	
	/**
	 * 获得用户数据
	 * @param token
	 * @return
	 */
	Result<Object> getAdminByToken(String token);
}
