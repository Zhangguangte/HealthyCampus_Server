package com.muyou.service;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.LoginForm;
import com.muyou.common.form.RegisterFrom;
import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.UserVo;

public interface UserService {

	// 登录
	public UserVo login(LoginForm dataForm) throws ServiceException;

	// 注册
	public UserVo register(RegisterFrom dataForm) throws ServiceException;

	// 获得用户信息，根据账号
	public UserVo getUserInformation(String account);

	// 查询用户，根据账号或电话
	public UserVo searchUser(String searchWords, String userid);

	// 查询用户，根据ID
	public UserVo searchUser(RequestForm requestForm);

	//查询用户，根据用户名
	public UserVo findByUsername(String string);

	//查询用户，根据电话
	public void searchPhone(RegisterFrom dataForm) throws  ServiceException;

}
