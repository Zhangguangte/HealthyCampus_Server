package com.muyou.sso.service;

import com.muyou.common.exception.ServiceException;
import com.muyou.sso.form.RegisterFrom;
import com.muyou.sso.pojo.UserVo;

/**
 * 
 * @author 木友茶
 *
 */
public interface RegisterService {
	UserVo createUser(RegisterFrom dataForm) throws ServiceException;
}
