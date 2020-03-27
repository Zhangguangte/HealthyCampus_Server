package com.muyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.constant.ItemConstant;
import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.ResultUtil;
import com.muyou.service.ItemCateService;
import com.muyou.service.ItemRecService;
import com.muyou.vo.RecipesVo;

/**
 * 食谱控制器
 * @author 木友茶
 *
 */
@Controller
@RequestMapping("/item/recipes")
public class ItemRecController {

	@Autowired
	private ItemRecService itemRecService;

	@Autowired
	private ItemCateService itemCateService;
	
	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	@ResponseBody
	public Result<RecipesVo> getItemById(@PathVariable Integer itemId) {
		return new ResultUtil<RecipesVo>().setData(itemRecService.getItemById(itemId));
	}
	
	@RequestMapping(value = "/show/{itemId}/{fac}", method = RequestMethod.GET)
	@ResponseBody
	public Result<RecipesVo> getItemShowById(@PathVariable Integer itemId,@PathVariable Integer fac) {
		if(0 == fac)
			return new ResultUtil<RecipesVo>().setData(itemRecService.getItemShowById(itemId));
		else
			return new ResultUtil<RecipesVo>().setData(itemRecService.getItemById(itemId));
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getAllItemCount() {
		return itemRecService.getAllItemCount();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemList(int draw, int start, int length, int cid,
			@RequestParam("search[value]") String search, @RequestParam("order[0][column]") int orderCol,
			@RequestParam("order[0][dir]") String orderDir, String searchItem, String minDate, String maxDate) {

		// 获取客户端需要排序的列
		String[] cols = { "re.id","re.time","re.type","re.calorie","re.physique", "re.created"};
		switch (orderCol) {
		case 1:
			orderCol = 0;
			break;
		case 7:
			orderCol = 1;
			break; 
		case 8:
			orderCol = 2;
			break;
		case 9: 
			orderCol = 3;
			break;
		case 11:
			orderCol = 4;
			break;
		case 12:
			orderCol = 5;
			break;
		default:orderCol = 0;
			break;
		}

		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "re.created";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "desc";
		}

		return itemRecService.getItemList(draw, start, length, cid, search, orderColumn, orderDir);
	}

	@RequestMapping(value = "/listSearch", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemSearchList(int draw, int start, int length, int cid, String searchKey,
			String minDate, String maxDate, @RequestParam("search[value]") String search,
			@RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir) {

		// 获取客户端需要排序的列
		String[] cols = { "re.id","re.time","re.type","re.calorie","re.physique", "re.created"};
		switch (orderCol) {
		case 1:
			orderCol = 0;
			break;
		case 7:
			orderCol = 1;
			break;
		case 8:
			orderCol = 2;
			break;
		case 9:
			orderCol = 3;
			break;
		case 11:
			orderCol = 4;
			break;
		case 12:
			orderCol = 5;
			break;
		default:orderCol = 0;
			break;
		}

		// 默认排序列
		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "re.created";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "desc";
		}
		if (!search.isEmpty()) {
			searchKey = search;
		}

		return itemRecService.getItemSearchList(draw, start, length, cid, searchKey, minDate, maxDate, orderColumn,
				orderDir);
	}

	@RequestMapping(value = "/listMeal", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemMealList(int draw, int start, int length, int cid, String searchKey,
			String minDate, String maxDate, @RequestParam("search[value]") String search,
			@RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir) {

		// 获取客户端需要排序的列
		String[] cols = { "re.id","re.time","re.type","re.calorie","re.physique", "re.created"};
		switch (orderCol) {
		case 1:
			orderCol = 0;
			break;
		case 7:
			orderCol = 1;
			break;
		case 8:
			orderCol = 2;
			break;
		case 9:
			orderCol = 3;
			break;
		case 11:
			orderCol = 4;
			break;
		case 12:
			orderCol = 5;
			break;
		default:orderCol = 0;
			break;
		}

		// 默认排序列
		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "re.created";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "desc";
		}
		if (!search.isEmpty()) {
			searchKey = search;
		}

		return itemRecService.getItemMealList(draw, start, length, cid, searchKey, minDate, maxDate, orderColumn,
				orderDir);
	}

	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateItem(@PathVariable Integer id, RecipesVo recipesVo) {
		itemRecService.updateItem(id, recipesVo);
		itemCateService.updateItemCate(id,ItemConstant.CATE_CATTEN);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/stop/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> stopItem(@PathVariable Integer id) {
		itemRecService.alertItemState(id, false);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/start/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> startItem(@PathVariable Integer id) {
		itemRecService.alertItemState(id, true);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/del/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteItem(@PathVariable Integer[] ids) {
		for (Integer id : ids) {
			itemRecService.deleteItem(id);
			itemCateService.delCanteen(id, ItemConstant.CATE_CATTEN);
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addItem(RecipesVo recipesVo) {
		itemRecService.addItem(recipesVo);
		return new ResultUtil<Object>().setData(null);
	}
	
}
