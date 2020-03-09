package com.muyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.pojo.Result;
import com.muyou.common.util.ResultUtil;
import com.muyou.pojo.TbTimetable;
import com.muyou.service.ItemTimService;
import com.muyou.vo.RecipesVo;

/**
 * 课表控制器
 * 
 * @author 木友茶
 *
 */
@Controller
@RequestMapping("/item/timetable")
public class ItemTimController {

	@Autowired
	private ItemTimService itemTimService;

	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	@ResponseBody
	public Result<TbTimetable> getItemById(@PathVariable Integer itemId) {
		return new ResultUtil<TbTimetable>().setData(itemTimService.getItemById(itemId));
	}
	
	@RequestMapping(value = "/list/{cid}/{year}/{semester}", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getItemList(@PathVariable("cid") Integer cid, @PathVariable("year") Integer year,
			@PathVariable("semester") Integer semester) {
		return new ResultUtil<Object>().setData(itemTimService.getItemList(cid, year, semester));
	}

	@RequestMapping(value = "/del/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> delItem(@PathVariable Integer[] ids) {
		for (Integer id : ids) {
			itemTimService.delItem(id);
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addItem(TbTimetable timetable) {
		int result = itemTimService.addItem(timetable);
		if (result == 0)
			return new ResultUtil<Object>().setErrorMsg("添加课程期间已有课程");
		else
			return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateItem(@PathVariable Integer id, TbTimetable timetable) {
		itemTimService.updateItem(id, timetable);
		return new ResultUtil<Object>().setData(null);
	}
	
}
