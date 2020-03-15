package com.muyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.ResultUtil;
import com.muyou.pojo.TbAdmin;
import com.muyou.service.AdminService;
import com.muyou.service.ItemTeaService;

@Controller
@RequestMapping("/item/teacher")
public class ItemTeaController {

	@Autowired
	private ItemTeaService itemTeaService;

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	@ResponseBody
	public Result<TbAdmin> getItemById(@PathVariable Integer itemId) {
		return new ResultUtil<TbAdmin>().setData(adminService.getTeacherById(itemId));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemList(int draw, int start, int length, int cid,
			@RequestParam("search[value]") String search, @RequestParam("order[0][column]") int orderCol,
			@RequestParam("order[0][dir]") String orderDir, String searchItem, String minDate, String maxDate) {

		// 获取客户端需要排序的列
		String[] cols = { "id", "createTime" };
		switch (orderCol) {
		case 9:
			orderCol = 1;
			break;
		default:
			orderCol = 0;
			break;
		}

		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "createTime";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "desc";
		}

		return itemTeaService.getItemList(draw, start, length, cid, search, orderColumn, orderDir);
	}

	@RequestMapping(value = "/college", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemCollege(int draw, int start, int length, int cid,
			@RequestParam("search[value]") String search, @RequestParam("order[0][column]") int orderCol,
			@RequestParam("order[0][dir]") String orderDir, String searchItem, String minDate, String maxDate) {

		// 获取客户端需要排序的列
		String[] cols = { "id", "createTime" };
		switch (orderCol) {
		case 9:
			orderCol = 1;
			break;
		default:
			orderCol = 0;
			break;
		}

		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "createTime";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "desc";
		}

		return itemTeaService.getItemCollege(draw, start, length, cid, search, orderColumn, orderDir);
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getAllItemCount() {
		return itemTeaService.getAllItemCount();
	}

	@RequestMapping(value = "/stop/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> stopItem(@PathVariable Integer id) {
		itemTeaService.alertItemState(id, false);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/start/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> startItem(@PathVariable Integer id) {
		itemTeaService.alertItemState(id, true);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/del/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteItem(@PathVariable Integer[] ids) {
		for (Integer id : ids) {
			adminService.deleteTeacher(id);
			itemTeaService.updateRedis(id);
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateItem(@RequestParam Integer id, TbAdmin admin) {
		adminService.updateTeacher(id, admin);
		itemTeaService.updateRedis(null);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addAdmin(@RequestParam Integer cid, @ModelAttribute TbAdmin admin) {
		adminService.addTeacher(cid, admin);
		itemTeaService.updateRedis(null);
		return new ResultUtil<Object>().setData(null);
	}

}
