package com.muyou.sso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.util.StringUtil;
import com.muyou.sso.pojo.NoticeVo;
import com.muyou.sso.service.NoticeService;

@Controller
@RequestMapping("/messages")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	// 获得所有通知
	@RequestMapping("/getAllNotice")
	@ResponseBody
	public List<NoticeVo> getAllNotice(@RequestBody RequestForm form, @RequestHeader("User") String user)
			throws ServiceException {
		if (StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<NoticeVo> result = noticeService.getAllNotice(form, user);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_NEWS_NOT_FOUND);
		return result;
	}

	// 清空通知
	@RequestMapping("/clearNotice")
	@ResponseBody
	public ResponseBuilder clearNotice(@RequestHeader("User") String user) throws ServiceException {
		if (StringUtil.isEmpty(user))
			return ResponseBuilder.ERROR_INVALID_PARAMETER;
		noticeService.clearNotice(user);
		return ResponseBuilder.SUCCESS;
	}

	// 删除通知
	@RequestMapping("/deleteNotice")
	@ResponseBody
	public ResponseBuilder deleteNotice(@RequestBody RequestForm form, @RequestHeader("User") String user)
			throws ServiceException {
		if (StringUtil.isEmpty(user) || StringUtil.isEmpty(form.getQuest_id()))
			return ResponseBuilder.ERROR_INVALID_PARAMETER;
		noticeService.deleteNotice(form, user);
		return ResponseBuilder.SUCCESS;
	}

	// 查看通知
	@RequestMapping("/lookNotice")
	@ResponseBody
	public ResponseBuilder lookNotice(@RequestBody RequestForm form, @RequestHeader("User") String user)
			throws ServiceException {
		if (StringUtil.isEmpty(user) || StringUtil.isEmpty(form.getQuest_id()) || StringUtil.isEmpty(form.getContent()))
			return ResponseBuilder.ERROR_INVALID_PARAMETER;
		noticeService.lookNotice(form, user);
		return ResponseBuilder.SUCCESS;
	}

}
