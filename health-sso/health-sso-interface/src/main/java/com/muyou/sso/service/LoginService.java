package com.muyou.sso.service;

import com.muyou.common.exception.ServiceException;
import com.muyou.sso.form.LoginForm;
import com.muyou.sso.pojo.UserVo;

/**
 * 登录服务
 * @author 木友茶
 */
public interface LoginService {

	/**
	 * 登录
	 * @param dataForm
	 * @return
	 * @throws ServiceException
	 */
	UserVo userLogin(LoginForm dataForm) throws ServiceException;
	
	/**
     * 注销
     * @param token
     * @return
     */
    int logout(String token);
	
	
}
