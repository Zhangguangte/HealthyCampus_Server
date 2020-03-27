package com.muyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.DateUtil;
import com.muyou.common.util.ResultUtil;
import com.muyou.pojo.TbStudent;
import com.muyou.service.ItemStuService;

/**
 * 学生控制器
 * 
 * @author 木友茶
 *
 */
@Controller
@RequestMapping("/item/student")
public class ItemStuController {

	@Autowired
	private ItemStuService itemStuService;

	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	@ResponseBody
	public Result<TbStudent> getItemById(@PathVariable Integer itemId) {
		return new ResultUtil<TbStudent>().setData(itemStuService.getItemById(itemId));
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getAllItemCount() {
		return itemStuService.getAllItemCount();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemList(int draw, int start, int length, int cid,
			@RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir,
			String searchItem, String minDate, String maxDate) {
		if (-1 == cid) {
			DataTablesResult result = new DataTablesResult();
			result.setData(null);
			result.setRecordsTotal(0);
			result.setRecordsFiltered(0);
		}

		// 获取客户端需要排序的列
		String[] cols = { "id", "created" };
		switch (orderCol) {
		case 6:
			orderCol = 1;
			break;
		default:
			orderCol = 0;
			break;
		}

		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "created";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "asc";
		}

		return itemStuService.getItemList(draw, start, length, cid, orderColumn, orderDir);
	}

	@RequestMapping(value = "/listSearch", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemSearchList(int draw, int start, int length, int cid, String searchKey,
			String minDate, String maxDate, @RequestParam("search[value]") String search,
			@RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir) {

		if (-1 == cid) {
			DataTablesResult result = new DataTablesResult();
			result.setData(null);
			result.setRecordsTotal(0);
			result.setRecordsFiltered(0);
		}

		String[] cols = { "id", "created" };
		switch (orderCol) {
		case 6:
			orderCol = 1;
			break;
		default:
			orderCol = 0;
			break;
		}

		// 默认排序列
		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "created";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "asc";
		}
		if (!search.isEmpty()) {
			searchKey = search;
		}

		return itemStuService.getItemSearchList(draw, start, length, cid, searchKey, minDate, maxDate, orderColumn,
				orderDir);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateItem(@PathVariable Integer id, TbStudent student) {
		student.setBirthday(student.getBirthday());
		itemStuService.updateItem(id, student);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/stop/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> stopItem(@PathVariable Integer id) {
		itemStuService.alertItemState(id, false);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/start/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> startItem(@PathVariable Integer id) {
		itemStuService.alertItemState(id, true);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/del/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteItem(@PathVariable Integer[] ids) {
		for (Integer id : ids) {
			itemStuService.deleteItem(id);
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addItem(TbStudent student) {
		student.setBirthday(student.getBirthday());
		itemStuService.addItem(student);
		return new ResultUtil<Object>().setData(null);
	}

}
