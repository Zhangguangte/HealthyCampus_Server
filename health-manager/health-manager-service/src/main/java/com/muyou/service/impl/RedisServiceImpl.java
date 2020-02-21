package com.muyou.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.redis.JedisClient;
import com.muyou.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;

	@Value("${LECTURE_LIST}")
	private String LECTURE_LIST;

	@Value("${TIMETABLE_LIST}")
	private String TIMETABLE_LIST;

	@Value("${DISEASE_CLASS}")
	private String DISEASE_CLASS;

	@Value("${MEDICINE_CLASSIFY}")
	private String MEDICINE_CLASSIFY;

	@Value("${RECIPES_LIST}")
	private String RECIPES_LIST;

	@Value("${RECIPES_FUNCTION}")
	private String RECIPES_FUNCTION;

	@Value("${SHIRO_LIST}")
	private String SHIRO_LIST;

	@Value("${LOG_LIST}")
	private String LOG_LIST;
	
	
	@Override
	public String getIndexRedis() {

		return null;
	}


	@Override
	public String getLectureRedis() {
		String result = "";
		try {
			List<String> json = jedisClient.hvals(LECTURE_LIST);
			if (null != json)
				result = String.join(",", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateLectureRedis() {
		try {
			jedisClient.del(LECTURE_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String getTimeTableRedis() {
		String result = "";
		try {
			List<String> json = jedisClient.hvals(TIMETABLE_LIST);
			if (null != json)
				result = String.join(",", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateTimeTableRedis() {
		try {
			jedisClient.del(TIMETABLE_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String getDiseaseRedis() {
		String result = "";
		try {
			List<String> json = jedisClient.hvals(DISEASE_CLASS);
			if (null != json)
				result = String.join(",", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateDiseaseRedis() {
		try {
			jedisClient.del(DISEASE_CLASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String getMedicineRedis() {
		String result = "";
		try {
			result = jedisClient.get(MEDICINE_CLASSIFY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateMedicineRedis() {
		try {
			jedisClient.del(MEDICINE_CLASSIFY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String getRecipesRedis() {
		String result = "";
		try {
			List<String> json = jedisClient.hvals(RECIPES_LIST);
			if (null != json)
				result = String.join(",", json);

			if (StringUtils.isNotBlank(result))
				result += "&#10;功能食谱:";
			
			json = jedisClient.hvals(RECIPES_FUNCTION);
			if (null != json)
				result += String.join(",", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateRecipesRedis() {
		try {
			jedisClient.del(RECIPES_LIST);
			jedisClient.del(RECIPES_FUNCTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String getShiroRedis() {
		String result = "";
		try {
			result = jedisClient.get(SHIRO_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateShiroRedis() {
		try {
			jedisClient.del(SHIRO_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	@Override
	public String getLogRedis() {
		String result = "";
		try {
			result = jedisClient.get(LOG_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateLogRedis() {
		try {
			jedisClient.del(LOG_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
}
