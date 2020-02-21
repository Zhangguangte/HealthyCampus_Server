package com.muyou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.ResultUtil;
import com.muyou.pojo.TbDict;
import com.muyou.service.DictService;

@Controller
@RequestMapping("/dict")
public class DictController {

	@Autowired
	private DictService dictService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getDictList() {
		DataTablesResult result = new DataTablesResult();
		List<TbDict> list = dictService.getDictList();
		result.setData(list);
		result.setSuccess(true);
		return result;
	}

	@RequestMapping(value = "/stop/list", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getStopList() {
		DataTablesResult result = new DataTablesResult();
		List<TbDict> list = dictService.getStopList();
		result.setData(list);
		result.setSuccess(true);
		return result;
	}

	@RequestMapping(value = "/del/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> delDict(@PathVariable int[] ids) {
		for (int id : ids) {
			dictService.delDict(id);
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addDict(@ModelAttribute TbDict tbDict) {
		dictService.addDict(tbDict);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateDict(@ModelAttribute TbDict tbDict) {
		dictService.updateDict(tbDict);
		return new ResultUtil<Object>().setData(null);
	}

}
