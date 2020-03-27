package com.muyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.ResultUtil;
import com.muyou.search.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired(required = false)
	private SearchService searchService;

	@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getInfo() {
		if(null == searchService) {
			throw new GlobalException("Solr服务未开启");
		}
		return searchService.getInfo();
	}

	@RequestMapping(value = "/disease/importDisease", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> importDisease() {
		if(null == searchService) {
			throw new GlobalException("Solr服务未开启");
		}
		int result = searchService.importAllDiseaseItems();
		if (0 == result)
			return new ResultUtil<Object>().setErrorMsg("数据导入失败");
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/medicine/importMedicine", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> importMedicine() {
		if(null == searchService) {
			throw new GlobalException("Solr服务未开启");
		}
		int result = searchService.importAllMedicineItems();
		if (0 == result)
			return new ResultUtil<Object>().setErrorMsg("数据导入失败");
		searchService.importAllMedicineItems();
		return new ResultUtil<Object>().setData(null);
	}

}
