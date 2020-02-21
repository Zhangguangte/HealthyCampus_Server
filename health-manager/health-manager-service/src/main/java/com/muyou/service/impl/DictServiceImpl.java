package com.muyou.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.mapper.TbDictMapper;
import com.muyou.pojo.TbDict;
import com.muyou.pojo.TbDictExample;
import com.muyou.service.DictService;

@Service
public class DictServiceImpl implements DictService {

	@Autowired
	private TbDictMapper dictMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${DICT_EXT}")
	private Integer DICT_EXT;
	
	@Value("${DICT_STOP}")
	private Integer DICT_STOP;
	
	@Value("${DICT_EX_LIST}")
	private String DICT_EX_LIST;
	
	@Value("${DICT_ST_LIST}")
	private String DICT_ST_LIST;

	@Value("${DICT_EXPIRE}")
	private Integer DICT_EXPIRE;

	@Override
	public List<TbDict> getDictList() {

		try {
			String json = jedisClient.get(DICT_EX_LIST);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToList(json, TbDict.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbDictExample example = new TbDictExample();
		TbDictExample.Criteria criteria = example.createCriteria();
		// 条件查询
		criteria.andTypeEqualTo(DICT_EXT);
		List<TbDict> result = dictMapper.selectByExample(example);

		try {
			jedisClient.set(DICT_EX_LIST, JsonUtils.objectToJson(result));
			jedisClient.expire(DICT_EX_LIST, DICT_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<TbDict> getStopList() {
	
		try {
			String json = jedisClient.get(DICT_ST_LIST);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToList(json, TbDict.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbDictExample example = new TbDictExample();
		TbDictExample.Criteria criteria = example.createCriteria();
		// 条件查询
		criteria.andTypeEqualTo(DICT_STOP);
		List<TbDict> result = dictMapper.selectByExample(example);
		
		try {
			jedisClient.set(DICT_ST_LIST, JsonUtils.objectToJson(result));
			jedisClient.expire(DICT_ST_LIST, DICT_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int delDict(int id) {
		dictMapper.deleteByPrimaryKey(id);
		try {
			jedisClient.del(DICT_EX_LIST);
			jedisClient.del(DICT_ST_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int addDict(TbDict tbDict) {
		dictMapper.insert(tbDict);
		try {
			jedisClient.del(DICT_EX_LIST);
			jedisClient.del(DICT_ST_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int updateDict(TbDict tbDict) {
		dictMapper.updateByPrimaryKey(tbDict);
		try {
			jedisClient.del(DICT_EX_LIST);
			jedisClient.del(DICT_ST_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

}
