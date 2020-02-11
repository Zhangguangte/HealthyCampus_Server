package com.muyou.sso.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.util.DateUtil;
import com.muyou.mapper.TbUserMapper;
import com.muyou.pojo.TbUser;
import com.muyou.sso.form.RegisterFrom;
import com.muyou.sso.pojo.UserVo;
import com.muyou.sso.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private TbUserMapper userMapper;
	
	
	@Override
	public UserVo createUser(RegisterFrom dataForm) throws ServiceException{
		
		TbUser user = userMapper.findUserByAccountOrPhone(dataForm.getPhone());
		if (null != user) {
			throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
		}
		
		user.setPassword(DigestUtils.md5DigestAsHex(dataForm.getPassword().getBytes()).toLowerCase());
		user.setCreateTime(DateUtil.getStringDate());
		user.setLastModifyTime(DateUtil.getStringDate());
		user.setNickname(UUID.randomUUID().toString().substring(8).replace("-", "0"));
		user.setPhone(dataForm.getPhone());
		user.setInitials(user.getNickname().charAt(0) + "");
		
		// 4、把用户信息插入到数据库中。
		userMapper.insert(user);
		// 5、返回E3Result。
		return new UserVo(user);
	}

}
