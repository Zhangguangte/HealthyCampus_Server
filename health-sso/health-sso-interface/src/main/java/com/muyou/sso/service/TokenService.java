package com.muyou.sso.service;

import com.muyou.common.pojo.Result;
import com.muyou.sso.pojo.UserVo;

/**
 * 令牌服务
 * @author 木友茶
 *
 */
public interface TokenService {

	Result<Object> getAdminByToken(String token);
	
	UserVo getUserByToken(String token,String id);
}
