package com.muyou.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.constant.RedisConstant;
import com.muyou.common.redis.JedisClient;
import com.muyou.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;

	@Override
	public String getLectureRedis() {
		String result = "";
		try {
			List<String> json = jedisClient.hvals(RedisConstant.LECTURE_LIST);
			if (null != json && json.size() > 0)
				result = String.join(",", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateLectureRedis() {
		try {
			jedisClient.del(RedisConstant.LECTURE_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String getTimeTableRedis() {
		String result = "";
		try {
			List<String> json = jedisClient.hvals(RedisConstant.TIMETABLE_LIST);
			if (null != json && json.size() > 0)
				result = String.join(",", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateTimeTableRedis() {
		try {
			jedisClient.del(RedisConstant.TIMETABLE_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String getDiseaseRedis() {
		String result = "";
		try {
			List<String> json = jedisClient.hvals(RedisConstant.DISEASE_CLASSIFY);
			if (null != json && json.size() > 0)
				result = String.join(",", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateDiseaseRedis() {
		try {
			jedisClient.del(RedisConstant.DISEASE_CLASSIFY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String getMedicineRedis() {
		String result = "";
		try {
			result = jedisClient.get(RedisConstant.MEDICINE_CLASSIFY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateMedicineRedis() {
		try {
			jedisClient.del(RedisConstant.MEDICINE_CLASSIFY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String getRecipesRedis() {
		StringBuilder result = new StringBuilder();
		try {
			List<String> json = jedisClient.hvals(RedisConstant.RECIPES_LIST);
			if (null != json)
				result.append(String.join(",", json));

			if (StringUtils.isNotBlank(result))
				result.append("&#10;功能食谱:");

			json = jedisClient.hvals(RedisConstant.RECIPES_FUNCTION);
			if (null != json)
				result.append(String.join(",", json));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	@Override
	public int updateRecipesRedis() {
		try {
			jedisClient.del(RedisConstant.RECIPES_LIST);
			jedisClient.del(RedisConstant.RECIPES_FUNCTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String getShiroRedis() {
		String result = "";
		try {
			result = jedisClient.get(RedisConstant.SHIRO_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateShiroRedis() {
		try {
			jedisClient.del(RedisConstant.SHIRO_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String getLogRedis() {
		String result = "";
		return result;
	}

	@Override
	public int updateLogRedis() {
		return 1;
	}
}
