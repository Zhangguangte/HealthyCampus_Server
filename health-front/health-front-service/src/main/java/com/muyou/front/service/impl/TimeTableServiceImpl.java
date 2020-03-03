package com.muyou.front.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.front.service.TimeTableService;
import com.muyou.front.vo.CourseVo;
import com.muyou.mapper.TbTimetableMapper;
import com.muyou.pojo.TbTimetable;
import com.muyou.pojo.TbTimetableExample;

@Service
public class TimeTableServiceImpl implements TimeTableService {


	@Autowired
	private TbTimetableMapper timetableMapper;

	@Autowired
	private JedisClient jedisClient;

	
	@Value("${TIMETABLE_LIST}")
	private String TIMETABLE_LIST;

	@Override
	public List<CourseVo> getTimeTable(RequestForm requestForm) {

		try {
			String json = jedisClient.hget(TIMETABLE_LIST, requestForm.getContent());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, CourseVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbTimetableExample tbTimetableExample = new TbTimetableExample();
		TbTimetableExample.Criteria criteria = tbTimetableExample.createCriteria();
		criteria.andMajorLike(requestForm.getContent());
		List<TbTimetable> list = timetableMapper.selectByExample(tbTimetableExample);
		if (null == list)
			throw null;
		List<CourseVo> result = new LinkedList<CourseVo>();
		for (TbTimetable timetable : list) {
			result.add(new CourseVo(timetable));
		}

		try {
			jedisClient.hset(TIMETABLE_LIST, requestForm.getContent(), JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
