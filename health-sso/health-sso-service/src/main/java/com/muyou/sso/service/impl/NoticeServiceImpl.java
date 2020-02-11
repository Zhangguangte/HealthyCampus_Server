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
import com.muyou.mapper.TbNewsMapper;
import com.muyou.mapper.TbNewsUserMapper;
import com.muyou.pojo.TbNews;
import com.muyou.sso.pojo.NoticeVo;
import com.muyou.sso.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private TbNewsMapper newsMapper;

	@Autowired
	private TbNewsUserMapper newsUserMapper;

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${NOTICAE}")
	private String NOTICAE;

	// 获得所有通知
	@Override
	public List<NoticeVo> getAllNotice(RequestForm requestForm, String userId) {

		try {
			String json = jedisClient.hget(NOTICAE, userId);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, NoticeVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (requestForm.getQuest_id() == null || "".equals(requestForm.getQuest_id())) {
			requestForm.setQuest_id("0000-00-00 00:00:00");
		}
		List<TbNews> list = newsMapper.getAllNotice(userId, requestForm.getQuest_id());
		if (null == list || list.size() == 0)
			return null;
		List<NoticeVo> result = new LinkedList<NoticeVo>();
		for (TbNews news : list) {
			result.add(new NoticeVo(news));
		}

		try {
			jedisClient.hset(NOTICAE, userId, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 清空通知
	@Override
	public void clearNotice(String userId) {
		newsUserMapper.clearNotice(userId);
		try {
			jedisClient.hdel(NOTICAE, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除通知
	@Override
	public void deleteNotice(RequestForm requestForm, String userId) {
		newsUserMapper.deleteNotice(requestForm.getQuest_id(), userId);
		try {
			jedisClient.hdel(NOTICAE, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查看通知
	@Override
	public void lookNotice(RequestForm requestForm, String userId) {
		newsUserMapper.lookNotice(requestForm.getQuest_id(), requestForm.getContent(), userId);
		try {
			jedisClient.hdel(NOTICAE, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
