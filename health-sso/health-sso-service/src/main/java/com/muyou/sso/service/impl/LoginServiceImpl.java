package com.muyou.sso.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.StringUtil;
import com.muyou.mapper.TbUserMapper;
import com.muyou.pojo.TbUser;
import com.muyou.sso.form.LoginForm;
import com.muyou.sso.pojo.UserVo;
import com.muyou.sso.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public UserVo userLogin(LoginForm dataForm) throws ServiceException {
		// 判断用户名密码是否正确。
		TbUser user = userMapper.findUserByAccountOrPhone(dataForm.getAccount());

		// 校验密码
		if (null == user || !DigestUtils.md5DigestAsHex(dataForm.getPassword().getBytes())
				.equals(user.getPassword().toLowerCase())) {
			throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
		}

		// 密码设置为null
		user.setPassword(null);

		// 如果已经登陆，删除原有的令牌
		Boolean exists = jedisClient.exists("SESSION:" + user.getId() + ":*");
		if (exists) {
			jedisClient.del("SESSION:" + user.getId() + ":*");
		}

		// 用户登录成功，设置令牌
		String token = UUID.randomUUID().toString();
		UserVo uservo = new UserVo(user);
		uservo.setToken(token);

		// redis存储用户数据
		jedisClient.set("SESSION:" + uservo.getId() + ":" + token, JsonUtils.objectToJson(uservo));

		return uservo;
	}

	@Override
	public int logout(String token) {
		jedisClient.del("SESSION:" + token);
		return 1;
	}

}
