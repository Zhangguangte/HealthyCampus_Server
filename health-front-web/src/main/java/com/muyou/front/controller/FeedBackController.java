package com.muyou.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.front.service.FeedBackService;

@Controller
@RequestMapping("/service")
public class FeedBackController {

	@Autowired
	private FeedBackService feedBackService;
	
	@RequestMapping("/feedback/sendFeed")
	@ResponseBody
	public ResponseBuilder sendFeed(@RequestBody RequestForm requestForm) {
		feedBackService.sendFeed(requestForm);
		return ResponseBuilder.SUCCESS;
	}
	
}
