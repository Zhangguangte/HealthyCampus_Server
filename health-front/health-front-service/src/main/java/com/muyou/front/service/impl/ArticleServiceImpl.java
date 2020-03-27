package com.muyou.front.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.constant.HealthConstant;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.front.service.ArticleService;
import com.muyou.mapper.TbArticleMapper;
import com.muyou.vo.BannerVo;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private TbArticleMapper articleMapper;

	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public List<BannerVo> getBannerList() {
		try {
			String json = jedisClient.get(HealthConstant.BANNER);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToList(json, BannerVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<BannerVo> result = articleMapper.getBannerList(HealthConstant.BANNERID);
		if(null == result || result.size() <1){
			return null;
		}
		
		try {
			jedisClient.set(HealthConstant.BANNER, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

}
