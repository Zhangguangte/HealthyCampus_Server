package com.muyou.service;

import com.muyou.common.pojo.Result;

public interface RedisService {

	/**
	 * 获取首页缓存
	 * 
	 * @return
	 */
	String getIndexRedis();

	/**
	 * 获取讲座缓存
	 * 
	 * @return
	 */
	String getLectureRedis();

	/**
	 * 获取讲座缓存
	 * 
	 * @return
	 */
	int updateLectureRedis();

	/**
	 * 获取课表缓存
	 * 
	 * @return
	 */
	String getTimeTableRedis();

	/**
	 * 更新课表缓存
	 * 
	 * @return
	 */
	int updateTimeTableRedis();

	/**
	 * 获取疾病缓存
	 * 
	 * @return
	 */
	String getDiseaseRedis();

	/**
	 * 更新疾病缓存
	 * 
	 * @return
	 */
	int updateDiseaseRedis();
	
	/**
	 * 获取药品缓存
	 * 
	 * @return
	 */
	String getMedicineRedis();

	/**
	 * 更新药品缓存
	 * 
	 * @return
	 */
	int updateMedicineRedis();
	
	/**
	 * 获取食谱缓存
	 * 
	 * @return
	 */
	String getRecipesRedis();

	/**
	 * 更新食谱缓存
	 * 
	 * @return
	 */
	int updateRecipesRedis();
	
	/**
	 * 获取权限缓存
	 * 
	 * @return
	 */
	String getShiroRedis();

	/**
	 * 更新食谱缓存
	 * 
	 * @return
	 */
	int updateShiroRedis();
	
	/**
	 * 更新日志缓存
	 * 
	 * @return
	 */
	int updateLogRedis();
	
	/**
	 * 获取日志缓存
	 * 
	 * @return
	 */
	String getLogRedis();

	
	
}
