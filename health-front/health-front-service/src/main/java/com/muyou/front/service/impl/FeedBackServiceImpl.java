package com.muyou.front.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.front.service.FeedBackService;
import com.muyou.mapper.TbFeedbackMapper;
import com.muyou.pojo.TbFeedback;

@Service
public class FeedBackServiceImpl implements FeedBackService {

	@Autowired
	private TbFeedbackMapper feedbackMapper;
	
	@Override
	public void sendFeed(RequestForm requestForm) {
		feedbackMapper.insert(new TbFeedback(requestForm.getContent(), requestForm.getQuest_id()));
	}
}
