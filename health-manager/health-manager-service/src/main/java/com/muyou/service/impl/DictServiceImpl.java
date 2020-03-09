package com.muyou.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.constant.DictConstant;
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
		update();
		return 1;
	}

	@Override
	public int addDict(TbDict tbDict) {
		dictMapper.insert(tbDict);
		update();
		return 1;
	}

	@Override
	public int updateDict(TbDict tbDict) {
		dictMapper.updateByPrimaryKey(tbDict);
		update();
		return 1;
	}

	public void update() {
		try {
			// 更新词典标识
			jedisClient.set(DictConstant.LAST_MODIFIED, String.valueOf(System.currentTimeMillis()));
			jedisClient.set(DictConstant.ETAG, String.valueOf(System.currentTimeMillis()));
			// 更新缓存
			jedisClient.del(DICT_EX_LIST);
			jedisClient.del(DICT_ST_LIST);
			jedisClient.del(DictConstant.EXT_KEY);
			jedisClient.del(DictConstant.STOP_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<Object, Object> getRemoteList(String type) {

		Map<Object, Object> result = new HashMap<Object, Object>();

		// 获取数据
		try {
			String json = jedisClient.get(type);
			if (StringUtils.isNotBlank(json)) {
				result = JsonUtils.jsonToPojo(json, HashMap.class);
				result.put(DictConstant.NEW, false);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		StringBuilder dicStr = new StringBuilder();

		List<TbDict> list = null;
		if (DictConstant.EXT_KEY.equals(type))
			list = getDictList();
		else
			list = getStopList();
		for (TbDict tbDict : list) {
			dicStr.append(tbDict.getDict() + "\r\n");
		}

		result.put(type, dicStr.toString());
		// 设置更新时间
		try {
			result.put(DictConstant.LAST_MODIFIED, jedisClient.get(DictConstant.LAST_MODIFIED));
			result.put(DictConstant.ETAG, jedisClient.get(DictConstant.ETAG));
		} catch (Exception e) {
			e.printStackTrace();
			result.put(DictConstant.LAST_MODIFIED, String.valueOf(System.currentTimeMillis()));
			result.put(DictConstant.ETAG, String.valueOf(System.currentTimeMillis()));
		}

		try {
			// 更新词典标识
			jedisClient.set(type, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.put(DictConstant.NEW, true);
		return result;

	}

}
