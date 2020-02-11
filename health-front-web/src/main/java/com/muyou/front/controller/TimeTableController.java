package com.muyou.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.util.StringUtil;
import com.muyou.front.pojo.CourseVo;
import com.muyou.front.service.TimeTableService;

@Controller
@RequestMapping("/service")
public class TimeTableController {

	@Autowired
	private TimeTableService timeTableService;

	@RequestMapping("/course/getTimeTable")
	@ResponseBody
	public List<CourseVo> getTimeTable(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<CourseVo> courseVos = timeTableService.getTimeTable(requestForm);
		if (null == courseVos)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return courseVos;
	}

}
