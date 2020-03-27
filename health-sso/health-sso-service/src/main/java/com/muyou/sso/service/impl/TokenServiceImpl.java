package com.muyou.sso.service.impl;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.constant.HealthConstant;
import com.muyou.common.pojo.Result;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.ResultUtil;
import com.muyou.pojo.TbAdmin;
import com.muyou.sso.pojo.UserVo;
import com.muyou.sso.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	@Value("${USESSION}")
	private String USESSION;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public Result<Object> getAdminByToken(String token) {
		String json = jedisClient.get(HealthConstant.ASESSION + ":" + token);
		if (StringUtils.isBlank(json))
			return new ResultUtil<>().setErrorMsg("登录过期，请重新登录");

		// 重置过期时间
		jedisClient.expire(HealthConstant.ASESSION + ":" + token, HealthConstant.ASESSION_EXPIRE);
		Result<Object> result = new ResultUtil<>().setData(JsonUtils.jsonToPojo(json, TbAdmin.class));

		SimpleDateFormat sdp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		result.setMessage(sdp1.format(((TbAdmin) result.getResult()).getCreateTime()));

		return result;
	}

	@Override
	public UserVo getUserByToken(String id,String token) {
		
		System.out.println("token"+token);
		System.out.println("id"+id);
		 
		String json = jedisClient.hget(USESSION + ":" + id, token);
		if (StringUtils.isBlank(json))
			return null;
		return JsonUtils.jsonToPojo(json, UserVo.class);
	}

}
