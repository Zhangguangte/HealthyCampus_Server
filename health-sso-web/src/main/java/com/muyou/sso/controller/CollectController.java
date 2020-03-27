package com.muyou.sso.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.muyou.sso.pojo.CollectVo;
import com.muyou.sso.service.CollectService;

@Controller
@RequestMapping("/users")
public class CollectController {

	@Autowired
	private CollectService collectService;

	// 收藏
	@RequestMapping("/saveCollect")
	@ResponseBody
	public ResponseBuilder saveCollect(@RequestBody(required = false) RequestForm form, HttpServletRequest request)
			throws ServiceException {
		String user = (String) request.getAttribute("USER");
		if (StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		collectService.saveCollect(form, user);
		return ResponseBuilder.SUCCESS;
	}

	// 所有收藏
	@RequestMapping("/getAllCollect")
	@ResponseBody
	public List<CollectVo> getAllCollect(HttpServletRequest request) throws ServiceException {
		String user = (String) request.getAttribute("USER");
		if (StringUtil.isEmpty(user))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<CollectVo> result = collectService.getAllCollect(user);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_COLLECT_NOT_FOUND);
		return result;
	}

	// 删除收藏
	@RequestMapping("/deleteCollect")
	@ResponseBody
	public ResponseBuilder deleteCollect(@RequestBody(required = false) RequestForm form, HttpServletRequest request)
			throws ServiceException {
		if (null == form || 0 == form.getId())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		collectService.deleteCollect(form, (String) request.getAttribute("USER"));
		return ResponseBuilder.SUCCESS;
	}

}
