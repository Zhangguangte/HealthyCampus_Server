package com.muyou.front.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.front.service.TimeTableService;
import com.muyou.front.vo.AttendCourseVo;
import com.muyou.front.vo.CourseVo;

@Controller
@RequestMapping("/service")
public class TimeTableController {

	@Autowired
	private TimeTableService timeTableService;

	@RequestMapping("/course/getTimeTable")
	@ResponseBody
	public List<CourseVo> getTimeTable(@RequestBody RequestForm requestForm) throws ServiceException {
		if (null == requestForm.getMap())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		return timeTableService.getTimeTable(requestForm);
	}

	@RequestMapping("/course/getAttend")
	@ResponseBody
	public AttendCourseVo getAttend(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtils.isBlank(requestForm.getQuest_id()) || StringUtils.isBlank(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		return timeTableService.getAttend(requestForm);
	}

	@RequestMapping("/course/attendCourse")
	@ResponseBody
	public ResponseBuilder attendCourse(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtils.isBlank(requestForm.getQuest_id()) || StringUtils.isBlank(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		return timeTableService.attendCourse(requestForm);
	}

	@RequestMapping("/course/getAttendList")
	@ResponseBody
	public List<String> getAttendList(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtils.isBlank(requestForm.getQuest_id()) || StringUtils.isBlank(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		return timeTableService.getAttendList(requestForm);
	}
}
