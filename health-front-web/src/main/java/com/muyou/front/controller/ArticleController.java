package com.muyou.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.front.service.ArticleService;
import com.muyou.vo.BannerVo;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/article/bannerList")
	@ResponseBody
	public List<BannerVo> getBannerList() throws ServiceException {
		List<BannerVo> result = articleService.getBannerList();
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}

}
