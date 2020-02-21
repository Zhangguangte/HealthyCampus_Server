package com.muyou.sso.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.mapper.TbCollectMapper;
import com.muyou.pojo.TbCollect;
import com.muyou.pojo.TbCollectExample;
import com.muyou.sso.pojo.CollectVo;
import com.muyou.sso.service.CollectService;

@Service
public class CollectServiceImpl implements CollectService {

	@Autowired
	private TbCollectMapper collectMapper;

	@Value("${COLLECT}")
	private String COLLECT;

	@Value("${COLLECT_LIST}")
	private String COLLECT_LIST;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public void saveCollect(RequestForm form, String userid) {
		TbCollectExample example = new TbCollectExample();
		TbCollectExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(form.getQuest_id());
		criteria.andUIdEqualTo(userid);
		criteria.andTypeIdEqualTo(form.getMap().get("ID"));
		List<TbCollect> list = collectMapper.selectByExample(example);
		if (null != list && 0 != list.size())
			collectMapper.deleteByPrimaryKey(list.get(0).getId());
		else {
			TbCollect tbCollect = new TbCollect();
			tbCollect.setType(form.getQuest_id());
			tbCollect.setuId(userid);
			tbCollect.setTypeId(form.getMap().get("ID"));
			tbCollect.setUrl(form.getMap().get("URL"));
			tbCollect.setName(form.getMap().get("NAME"));
			collectMapper.insert(tbCollect);
		}
		try {
			jedisClient.hdel(COLLECT_LIST, userid);
			jedisClient.del(COLLECT + ":" + userid);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean isCollect(String type, String tid, String userid) {

		try {
			String json = jedisClient.hget(COLLECT + ":" + userid, type + ":" + tid);
			if (StringUtils.isNotBlank(json)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbCollectExample example = new TbCollectExample();
		TbCollectExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(type);
		criteria.andUIdEqualTo(userid);
		criteria.andTypeIdEqualTo(tid);
		int count = collectMapper.countByExample(example);

		try {
			jedisClient.hset(COLLECT + ":" + userid, type + ":" + tid, String.valueOf(0 != count));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0 != count;
	}

	@Override
	public List<CollectVo> getAllCollect(String userid) {

		try {
			String json = jedisClient.hget(COLLECT_LIST, userid);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, CollectVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbCollectExample example = new TbCollectExample();
		TbCollectExample.Criteria criteria = example.createCriteria();
		criteria.andUIdEqualTo(userid);
		List<TbCollect> list = collectMapper.selectByExample(example);
		if (null == list || 0 == list.size())
			return null;
		List<CollectVo> result = new LinkedList<CollectVo>();
		for (TbCollect collect : list) {
			result.add(new CollectVo(collect));
		}

		try {
			jedisClient.hset(COLLECT_LIST, userid, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void deleteCollect(RequestForm form, String userid) {
		collectMapper.deleteByPrimaryKey(form.getId());
		try {
			jedisClient.hdel(COLLECT_LIST, userid);
			jedisClient.del(COLLECT + ":" + userid);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
