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
import com.muyou.front.service.LectureService;
import com.muyou.front.vo.LectureVo;
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbLectureMapper;
import com.muyou.pojo.TbLecture;

@Service
public class LectureServiceImpl implements LectureService {

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbLectureMapper lectureMapper;

	@Value("${RELA_LEC}")
	private Integer RELA_LEC;

	@Autowired
	private TbCateMapper cateMapper;

	@Value("${LECTURE_DETAIL}")
	private String LECTURE_DETAIL;

	@Value("${LECTURE_EXPIRE}")
	private Integer LECTURE_EXPIRE;

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

		LectureVo lectureVo = new LectureVo(lecture, 1);
		List<String> cateList = cateMapper.selectCateNameByItemIdAndType(Integer.parseInt(lectureVo.getId()), RELA_LEC);
		lectureVo.setCollege(String.join(",", cateList));

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
			String json = jedisClient.hget(LECTURE_LIST, requestForm.getContent() + ":" + requestForm.getRow());
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToList(json, LectureVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<TbLecture> lectures = lectureMapper.selectItemByCollege(requestForm.getContent(), requestForm.getRow());
		if (null == lectures || lectures.size() == 0)
			return null;
		List<LectureVo> result = new LinkedList<>();
		for (TbLecture lecture : lectures) {
			result.add(new LectureVo(lecture, 0));
		}

		try {
			jedisClient.hset(LECTURE_LIST, requestForm.getContent() + ":" + requestForm.getRow(),
					JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
