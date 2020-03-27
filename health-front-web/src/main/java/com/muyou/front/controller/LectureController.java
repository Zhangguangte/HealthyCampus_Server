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
import com.muyou.front.service.LectureService;
import com.muyou.front.vo.LectureVo;

@Controller
@RequestMapping("/service")
public class LectureController {

	@Autowired
	private LectureService lectureService;
	
	@RequestMapping("/lecture/getLectureDetail")
	@ResponseBody
	public LectureVo getLectureDetail(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		LectureVo lectureVo = lectureService.getLectureDetail(requestForm);
		if (null == lectureVo)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return lectureVo;
	}

	@RequestMapping("/lecture/getLectureList")
	@ResponseBody
	public List<LectureVo> getLectureList(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);

		List<LectureVo> lectureVos = lectureService.getLectureList(requestForm);
		if (null == lectureVos)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return lectureVos;
	}
	
}
