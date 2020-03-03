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
import com.muyou.pojo.TbDisease;
import com.muyou.service.ItemDisService;
import com.muyou.vo.DiseaseVo;

@Controller
@RequestMapping("/item/disease")
public class ItemDisController {

	@Autowired
	private ItemDisService itemDisService;

	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	@ResponseBody
	public Result<DiseaseVo> getItemById(@PathVariable Integer itemId) {
		return new ResultUtil<DiseaseVo>().setData(itemDisService.getItemById(itemId));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemList(int draw, int start, int length, int cid,
			@RequestParam("search[value]") String search, @RequestParam("order[0][column]") int orderCol,
			@RequestParam("order[0][dir]") String orderDir, String searchItem, String minDate, String maxDate) {

		// 获取客户端需要排序的列
		String[] cols = { "d.created", "d.id" };
		if (10 == orderCol) {
			orderCol = 0;
		}
		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "d.created";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "desc";
		}

		return itemDisService.getItemList(draw, start, length, cid, search, orderColumn, orderDir);
	}

	@RequestMapping(value = "/listSearch", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemSearchList(int draw, int start, int length, int cid, String searchKey,
			String minDate, String maxDate, @RequestParam("search[value]") String search,
			@RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir) {

		// 获取客户端需要排序的列
		String[] cols = { "d.created", "d.id" };

		if (10 == orderCol) {
			orderCol = 0;
		}

		// 默认排序列
		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "d.created";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "desc";
		}
		if (!search.isEmpty()) {
			searchKey = search;
		}

		System.out.println(searchKey);

		DataTablesResult result = itemDisService.getItemSearchList(draw, start, length, cid, searchKey, minDate, maxDate,
				orderColumn, orderDir);
		return result;
	}

	@RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
	@ResponseBody
    public Result<Object> updateItem(@PathVariable Integer id, DiseaseVo diseaseVo){
		itemDisService.updateItem(id,diseaseVo);
        return new ResultUtil<Object>().setData(null);
    }
	
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getAllItemCount() {
		return itemDisService.getAllItemCount();
	}

	@RequestMapping(value = "/stop/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<TbDisease> stopItem(@PathVariable Integer id) {
		itemDisService.alertItemState(id, false);
		return new ResultUtil<TbDisease>().setData(null);
	}

	@RequestMapping(value = "/start/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<TbDisease> startItem(@PathVariable Integer id) {
		itemDisService.alertItemState(id, true);
		return new ResultUtil<TbDisease>().setData(null);
	}

	@RequestMapping(value = "/del/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteItem(@PathVariable Integer[] ids) {
		for (Integer id : ids) {
			itemDisService.deleteItem(id);
		}
		return new ResultUtil<Object>().setData(null);
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
    public Result<Object> addItem(DiseaseVo diseaseVo){
        itemDisService.addItem(diseaseVo);
        return new ResultUtil<Object>().setData(null);
    }
}
