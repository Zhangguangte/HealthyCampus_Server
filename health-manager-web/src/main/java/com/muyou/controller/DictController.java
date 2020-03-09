package com.muyou.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.constant.DictConstant;
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

	@RequestMapping(value = "/777")
	@ResponseBody
	public String gettest(HttpServletResponse response) {
		System.out.println("77777777");

		response.addHeader(DictConstant.LAST_MODIFIED, String.valueOf(System.currentTimeMillis()));
		response.addHeader(DictConstant.ETAG, String.valueOf(System.currentTimeMillis()));
		return "7777";
	}

	@RequestMapping(value = "/getDictList", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getDictExtList(HttpServletResponse response) {
		Map<Object, Object> map = dictService.getRemoteList(DictConstant.EXT_KEY);
		Boolean bol = (Boolean) map.get(DictConstant.NEW);
		System.out.println("999999999");
		// if (bol)
		// return (String) map.get(DictConstant.EXT_KEY);

		response.addHeader(DictConstant.LAST_MODIFIED, (String) map.get(DictConstant.LAST_MODIFIED));
		response.addHeader(DictConstant.ETAG, (String) map.get(DictConstant.ETAG));
		return (String) map.get(DictConstant.EXT_KEY);
	}

	@RequestMapping(value = "/getStopDictList", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getStopDictList(HttpServletResponse response) {
		Map<Object, Object> map = dictService.getRemoteList(DictConstant.STOP_KEY);
		Boolean bol = (Boolean) map.get(DictConstant.NEW);
		System.out.println("88888");

		// if (bol)
		// return (String) map.get(DictConstant.STOP_KEY);

		response.addHeader(DictConstant.LAST_MODIFIED, (String) map.get(DictConstant.LAST_MODIFIED));
		response.addHeader(DictConstant.ETAG, (String) map.get(DictConstant.ETAG));
		return (String) map.get(DictConstant.STOP_KEY);
	}

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
