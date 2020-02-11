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
import com.muyou.front.pojo.LectureVo;
import com.muyou.front.service.LectureService;
import com.muyou.mapper.TbLectureMapper;
import com.muyou.pojo.TbLecture;
import com.muyou.pojo.TbLectureExample;


@Service
public class LectureServiceImpl implements LectureService {

	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private TbLectureMapper lectureMapper;
	
	@Value("${LECTURE_DETAIL}")
	private String LECTURE_DETAIL;
	
	@Value("${LECTURE_LIST}")
	private String LECTURE_LIST;
	
	@Override
	public LectureVo getLectureDetail(RequestForm requestForm) {

		try {
			String json = jedisClient.hget(LECTURE_DETAIL, requestForm.getQuest_id());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, LectureVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbLecture lecture = lectureMapper.selectByPrimaryKey(Integer.parseInt(requestForm.getQuest_id()));
		if (null == lecture)
			throw null;
		LectureVo lectureVo = new LectureVo(lecture,1);

		try {
			jedisClient.hset(LECTURE_DETAIL, requestForm.getQuest_id(), JsonUtils.objectToJson(lectureVo));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lectureVo;
	}

	@Override
	public List<LectureVo> getLectureList(RequestForm requestForm) {

		try {
			List<String> json = jedisClient.lrange(
					LECTURE_LIST + ":" + requestForm.getContent() + ":" + requestForm.getRow(),
					requestForm.getRow() * 15, (requestForm.getRow() + 1) * 15);
			if (null != json && json.size() > 0) {
				List<LectureVo> list = new LinkedList<>();
				for (String string : json) {
					list.add(JsonUtils.jsonToPojo(string, LectureVo.class));
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbLectureExample tbLectureExample = new TbLectureExample();
		tbLectureExample.setOrderByClause("date desc");
		tbLectureExample.setRow(requestForm.getRow());
		TbLectureExample.Criteria criteria = tbLectureExample.createCriteria();
		criteria.andCollegeLike(requestForm.getContent());
		List<TbLecture> books = lectureMapper.selectByExample(tbLectureExample);
		if (null == books || books.size() == 0)
			throw null;
		List<LectureVo> lectureVos = new LinkedList<>();
		for (TbLecture lecture : books) {
			lectureVos.add(new LectureVo(lecture,0));
		}

		try {
			for (int i = lectureVos.size() - 1; i > -1; i--)
				jedisClient.lpush(LECTURE_LIST + ":" + requestForm.getContent() + ":" + requestForm.getRow(),
						JsonUtils.objectToJson(lectureVos.get(i)));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lectureVos;
	}

}
