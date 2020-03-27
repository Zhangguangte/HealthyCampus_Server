package com.muyou.sso.service;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.LoginForm;
import com.muyou.common.form.RequestForm;
import com.muyou.sso.form.RegisterFrom;
import com.muyou.sso.pojo.UserVo;

/**
 * 用户服务
 * @author 木友茶
 *
 */
public interface UserService {

	/**
	 * 注册用户
	 * @param dataForm
	 * @return
	 * @throws ServiceException
	 */
	UserVo createUser(RegisterFrom dataForm) throws ServiceException;

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
    int userLogout(String id);
    
	// 获得用户信息，根据账号
	public UserVo getUserInformation(String account);

	// 查询用户，根据账号或电话
	public UserVo searchUser(String searchWords, String userid);

	// 查询用户，根据ID
	public UserVo searchUser(RequestForm requestForm);

	// 查询用户，根据电话
	public int searchPhone(RegisterFrom dataForm);

	/**
	 * 更新用户
	 * @param id
	 * @param userVo
	 * @return
	 */
	public int updateUser(String id,UserVo userVo)  throws ServiceException;
	
	
	/**
	 * 更新学生信息
	 * @param id
	 * @param form
	 * @return
	 */
	public int updateStudent(String id,RequestForm form)  throws ServiceException;
	
	/**
	 * 更新头像
	 */
	public int updateUser(String id,String url) throws ServiceException;
}
