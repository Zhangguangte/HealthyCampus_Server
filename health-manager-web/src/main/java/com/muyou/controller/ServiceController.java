package com.muyou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.util.StringUtil;
import com.muyou.common.vo.BookDetailVo;
import com.muyou.common.vo.BookVo;
import com.muyou.common.vo.CourseVo;
import com.muyou.common.vo.LectureVo;
import com.muyou.common.vo.ResponseBuilder;
import com.muyou.service.ServiceService;

@Controller
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	private ServiceService serviceService;

	/******************* 图书 ****************************/
	@RequestMapping("/library/searchBookDetail")
	@ResponseBody
	public BookDetailVo searchBookDetail(@RequestBody RequestForm requestForm) throws ServiceException {
		if(StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		BookDetailVo book = serviceService.searchBookDetail(requestForm);
		if (null == book)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return book;
	}

	@RequestMapping("/library/searchBook")
	@ResponseBody
	public List<BookVo> searchBook(@RequestBody RequestForm requestForm) throws ServiceException  {
		if(StringUtil.isEmpty(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<BookVo> books = serviceService.searchBook(requestForm);
		if (null == books)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return books;
	}

	/******************* 讲座 ****************************/
	@RequestMapping("/lecture/getLectureDetail")
	@ResponseBody
	public LectureVo getLectureDetail(@RequestBody RequestForm requestForm) throws ServiceException {
		if(StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		
		LectureVo lectureVo = serviceService.getLectureDetail(requestForm);
		if (null == lectureVo)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return lectureVo;
	}

	@RequestMapping("/lecture/getLectureList")
	@ResponseBody
	public List<LectureVo> getLectureList(@RequestBody RequestForm requestForm) throws ServiceException {
		if(StringUtil.isEmpty(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		
		List<LectureVo> lectureVos = serviceService.getLectureList(requestForm);
		if (null == lectureVos)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return lectureVos;
	}

	/******************* 反馈 ****************************/
	@RequestMapping("/feedback/sendFeed")
	@ResponseBody
	public ResponseBuilder sendFeed(@RequestBody RequestForm requestForm) {
		serviceService.sendFeed(requestForm);
		return ResponseBuilder.SUCCESS;
	}

	/******************* 课表 ****************************/
	 
	@RequestMapping("/course/getTimeTable")
	@ResponseBody
	public List<CourseVo> getTimeTable(@RequestBody RequestForm requestForm) throws ServiceException {
		if(StringUtil.isEmpty(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<CourseVo> courseVos = serviceService.getTimeTable(requestForm);
		if (null == courseVos)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return courseVos;
	}

}
