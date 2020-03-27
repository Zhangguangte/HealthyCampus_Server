package com.muyou.sso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.constant.HealthConstant;
import com.muyou.common.exception.GlobalException;
import com.muyou.common.redis.JedisClient;
import com.muyou.mapper.TbLogMapper;
import com.muyou.pojo.TbLog;
import com.muyou.sso.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private TbLogMapper tbLogMapper;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public int addLog(TbLog tbLog) {
		if (tbLogMapper.insert(tbLog) != 1) {
			throw new GlobalException("保存日志失败");
		}
		try {
			jedisClient.del(HealthConstant.LOG_COUNT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

}
