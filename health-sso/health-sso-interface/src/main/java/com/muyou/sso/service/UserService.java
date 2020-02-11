package com.muyou.sso.service;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.sso.form.RegisterFrom;
import com.muyou.sso.pojo.UserVo;

/**
 * 用户服务
 * @author 木友茶
 *
 */
public interface UserService {


	// 获得用户信息，根据账号
	public UserVo getUserInformation(String account);

	// 查询用户，根据账号或电话
	public UserVo searchUser(String searchWords, String userid);

	// 查询用户，根据ID
	public UserVo searchUser(RequestForm requestForm);

//	// 查询用户，根据用户账号
//	public UserVo findByAccount(String account);

	// 查询用户，根据电话
	public void searchPhone(RegisterFrom dataForm) throws ServiceException;


}
