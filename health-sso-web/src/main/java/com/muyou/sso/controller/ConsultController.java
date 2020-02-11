package com.muyou.sso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.util.StringUtil;
import com.muyou.sso.form.ConsultPictureForm;
import com.muyou.sso.service.ConsultService;

@Controller
@RequestMapping("/consult")
public class ConsultController {

    private static final Logger LOG = LoggerFactory.getLogger(ConsultController.class);

	
	@Autowired
	private ConsultService consultService;

	// 图片咨询
	@RequestMapping("/saveConsultPicture")
	@ResponseBody
	public ResponseBuilder saveConsultPicture(@RequestBody ConsultPictureForm form,@RequestHeader("User") String user) throws ServiceException {
		if(StringUtil.isEmpty(form.getDescribe()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		consultService.saveConsultPicture(form,user);
		return ResponseBuilder.SUCCESS;
	}

}
