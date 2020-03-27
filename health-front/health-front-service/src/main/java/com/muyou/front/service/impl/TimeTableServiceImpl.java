package com.muyou.front.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.constant.ItemConstant;
import com.muyou.common.constant.RedisConstant;
import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.DateUtil;
import com.muyou.common.util.JsonUtils;
import com.muyou.front.service.TimeTableService;
import com.muyou.front.vo.AttendCourseVo;
import com.muyou.front.vo.CourseVo;
import com.muyou.mapper.TbAttendMapper;
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbTimeAttendMapper;
import com.muyou.mapper.TbTimetableMapper;
import com.muyou.pojo.TbAttend;
import com.muyou.pojo.TbAttendExample;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbCateExample;
import com.muyou.pojo.TbTimeAttend;
import com.muyou.pojo.TbTimeAttendExample;
import com.muyou.pojo.TbCateExample.Criteria;
import com.muyou.vo.AttendVo;
import com.muyou.pojo.TbTimetable;

@Service
public class TimeTableServiceImpl implements TimeTableService {

	private final static String[] attendType = { "已签到", "补签", "请假", "缺勤" };
	@Autowired
	private TbTimetableMapper timetableMapper;

	@Autowired
	private TbTimeAttendMapper timeAttendMapper;

	@Autowired
	private TbAttendMapper attendMapper;

	@Autowired
	private TbCateMapper cateMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ATTEND_LIST}")
	private String ATTEND_LIST;

	@Value("${ATTEND_EXPIRE}")
	private Integer ATTEND_EXPIRE;

	@Override
	public List<CourseVo> getTimeTable(RequestForm requestForm) {

		try {
			String json = jedisClient.hget(RedisConstant.TIMETABLE_LIST, requestForm.getMap().get("cls") + ":"
					+ requestForm.getMap().get("year") + ":" + requestForm.getMap().get("semester"));
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, CourseVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbCateExample example = new TbCateExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(requestForm.getMap().get("cls"));
		criteria.andTypeEqualTo(ItemConstant.CATE_CLASS);
		List<TbCate> cateList = cateMapper.selectByExample(example);
		if (null == cateList || cateList.size() < 1)
			return null;

		List<TbTimetable> list = timetableMapper.selectItemByClass(ItemConstant.RELA_TIM, cateList.get(0).getId(),
				requestForm.getMap().get("year"), requestForm.getMap().get("semester"));

		List<CourseVo> result = new LinkedList<CourseVo>();
		for (TbTimetable timetable : list) {
			result.add(new CourseVo(timetable));
		}

		try {
			jedisClient.hset(RedisConstant.TIMETABLE_LIST, requestForm.getMap().get("cls") + ":" + requestForm.getMap().get("year")
					+ ":" + requestForm.getMap().get("semester"), JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public AttendCourseVo getAttend(RequestForm requestForm) {
		try {
			String json = jedisClient.hget(ItemConstant.ATTEND,
					requestForm.getContent() + ":" + requestForm.getQuest_id());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, AttendCourseVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbTimeAttendExample example = new TbTimeAttendExample();
		TbTimeAttendExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(true);
		criteria.andTIdEqualTo(Integer.valueOf(requestForm.getQuest_id()));
		List<TbTimeAttend> list = timeAttendMapper.selectByExample(example);
		AttendCourseVo result = new AttendCourseVo();
		// 课程是否开始签到
		if (null != list && list.size() > 0) {
			result.setBegin(true);
			result.setNo(list.get(0).getaNo());
			TbAttendExample example2 = new TbAttendExample();
			TbAttendExample.Criteria criteria2 = example2.createCriteria();
			criteria2.andNoEqualTo(list.get(0).getaNo());
			criteria2.andSIdEqualTo(Integer.valueOf(requestForm.getContent()));
			List<TbAttend> list2 = attendMapper.selectByExample(example2);
			// 学生是否已经签到
			if (null != list2 && list2.size() > 0) {
				result.setTime(DateUtil.getStringDate(list2.get(0).getCreated()));
			} else
				result.setTime(null);
		} else
			result.setBegin(false);

		try {
			jedisClient.hset(ItemConstant.ATTEND, requestForm.getContent() + ":" + requestForm.getQuest_id(),
					JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ResponseBuilder attendCourse(RequestForm requestForm) throws ServiceException {
		TbAttend attend = new TbAttend();
		attend.setCreated(new Date());
		attend.setNo(requestForm.getContent());
		attend.setType(1);
		attend.setsId(Integer.parseInt(requestForm.getQuest_id()));
		if (attendMapper.insert(attend) < 1)
			throw new ServiceException(new ResponseBuilder(400, 99, "签到失败"));

		try {
			jedisClient.del(ItemConstant.ATTEND);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseBuilder(ResponseBuilder.STATUS_OK, 3000, DateUtil.getStringDate(attend.getCreated()));
	}

	@Override
	public List<String> getAttendList(RequestForm requestForm) {

		try {
			String json = jedisClient
					.get(ATTEND_LIST + ":" + requestForm.getContent() + ":" + requestForm.getQuest_id());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, String.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取签到列表
		TbTimeAttendExample example = new TbTimeAttendExample();
		example.setOrderByClause("created desc");
		TbTimeAttendExample.Criteria criteria = example.createCriteria();
		criteria.andTIdEqualTo(Integer.valueOf(requestForm.getQuest_id()));
		List<TbTimeAttend> list = timeAttendMapper.selectByExample(example);
		// 学生签到数据
		List<String> result = new LinkedList<>();
		StringBuilder sbBuilder = new StringBuilder();

		TbAttendExample example2 = new TbAttendExample();
		TbAttendExample.Criteria criteria2 = example2.createCriteria();

		List<TbAttend> list2;
		for (TbTimeAttend tbTimeAttend : list) {
			sbBuilder.setLength(0);
			sbBuilder.append(DateUtil.getStringDate(tbTimeAttend.getCreated()) + " | ");
			criteria2.andNoEqualTo(tbTimeAttend.getaNo());
			criteria2.andSIdEqualTo(Integer.valueOf(requestForm.getContent()));
			// 考勤状态
			list2 = attendMapper.selectByExample(example2);
			if (null != list2 && list2.size() > 0) {
				sbBuilder.append(attendType[list2.get(0).getType()]);
			} else {
				sbBuilder.append("缺勤");
			}
			result.add(sbBuilder.toString());
		}

		try {
			jedisClient.set(ATTEND_LIST + ":" + requestForm.getContent() + ":" + requestForm.getQuest_id(),
					JsonUtils.objectToJson(result));
			jedisClient.expire(ATTEND_LIST + ":" + requestForm.getContent() + ":" + requestForm.getQuest_id(), ATTEND_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
