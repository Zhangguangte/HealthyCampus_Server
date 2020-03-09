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
import com.muyou.common.util.ResultUtil;
import com.muyou.service.ItemLibService;
import com.muyou.vo.LibraryVo;

/**
 * 图书控制器
 * 
 * @author 木友茶
 *
 */
@Controller
@RequestMapping("/item/library")
public class ItemLibController {

	@Autowired
	private ItemLibService itemlibService;

	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	@ResponseBody
	public Result<LibraryVo> getItemById(@PathVariable Integer itemId) {
		return new ResultUtil<LibraryVo>().setData(itemlibService.getItemById(itemId));
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getAllItemCount() {
		return itemlibService.getAllItemCount();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemList(int draw, int start, int length, int cid,
			@RequestParam("search[value]") String search, @RequestParam("order[0][column]") int orderCol,
			@RequestParam("order[0][dir]") String orderDir, String searchItem, String minDate, String maxDate) {

		// 获取客户端需要排序的列
		String[] cols = { "l.id", "l.price", "l.created" };
		switch (orderCol) {
		case 6:
			orderCol = 1;
			break;
		case 12:
			orderCol = 2;
			break;
		default:
			orderCol = 0;
			break;
		}

		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "l.created";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "desc";
		}

		return itemlibService.getItemList(draw, start, length, cid, search, orderColumn, orderDir);
	}

	@RequestMapping(value = "/listSearch", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemSearchList(int draw, int start, int length, int cid, String searchKey,
			String minDate, String maxDate, @RequestParam("search[value]") String search,
			@RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir) {

		String[] cols = { "l.id", "l.price", "l.created" };
		switch (orderCol) {
		case 6:
			orderCol = 1;
			break;
		case 12:
			orderCol = 2;
			break;
		default:
			orderCol = 0;
			break;
		}

		// 默认排序列
		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "l.created";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "desc";
		}
		if (!search.isEmpty()) {
			searchKey = search;
		}

		return itemlibService.getItemSearchList(draw, start, length, cid, searchKey, minDate, maxDate, orderColumn,
				orderDir);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateItem(@PathVariable Integer id, LibraryVo libraryVo) {
		itemlibService.updateItem(id, libraryVo);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/stop/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> stopItem(@PathVariable Integer id) {
		itemlibService.alertItemState(id, false);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/start/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> startItem(@PathVariable Integer id) {
		itemlibService.alertItemState(id, true);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/del/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteItem(@PathVariable Integer[] ids) {
		for (Integer id : ids) {
			itemlibService.deleteItem(id);
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addItem(LibraryVo libraryVo) {
		itemlibService.addItem(libraryVo);
		return new ResultUtil<Object>().setData(null);
	}

}
