package com.muyou.sso.service.impl;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.sso.pojo.UserVo;
import com.muyou.sso.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private JedisClient jedisClient;

	@Override
	public UserVo getUserByToken(String token, String id) {
		String json = jedisClient.get("SESSION:" + id + ":" + token);

		if (StringUtils.isBlank(json))
			return null;

		UserVo user = JsonUtils.jsonToPojo(json, UserVo.class);
		return user;
	}

}
