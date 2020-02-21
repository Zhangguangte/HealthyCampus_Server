package com.muyou.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.mapper.TbUserMapper;
import com.muyou.pojo.TbUserExample;
import com.muyou.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private TbUserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${USER}")
	private String USER;

	@Value("${COUNT}")
	private String COUNT;
	
	@Override
	public DataTablesResult getUserCount() {
		try {
			String json = jedisClient.hget(USER, COUNT);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbUserExample example = new TbUserExample();
		DataTablesResult result = new DataTablesResult();
		result.setRecordsTotal(userMapper.countByExample(example));

		try {
			jedisClient.hset(USER, COUNT, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	
}
