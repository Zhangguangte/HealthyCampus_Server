package com.muyou.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.annotation.SystemControllerLog;
import com.muyou.common.pojo.Result;
import com.muyou.common.pojo.ZTreeNode;
import com.muyou.common.util.ResultUtil;
import com.muyou.pojo.TbCate;
import com.muyou.service.ItemCateService;

@Controller
@RequestMapping("/item/cate")
public class ItemCateController {

	@Autowired
	private ItemCateService itemCateService;

	@Value("${CATE_DEPART}")
	private Integer CATE_DEPART;

	@Value("${CATE_PART}")
	private Integer CATE_PART;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<ZTreeNode> getItemCateList(@RequestParam(name = "id", required = false) Integer parentId,
			@RequestParam("type") int type, @RequestParam(name = "factor", required = false) Integer factor) {

		if (null == parentId)
			switch (factor) {
			case -1:// 根目录
				List<ZTreeNode> result = new LinkedList<ZTreeNode>();
				ZTreeNode node = new ZTreeNode();
				node.setId(0);
				node.setStatus(1);
				node.setSortOrder(1);
				node.setName("根目录");
				node.setpId(-1);
				node.setIsParent(true);
				result.add(node);
				return result;
			case 0: // 根目录->所有子目录
				parentId = 0;
				break;
			case 1: // 部位节点
				parentId = CATE_PART;
				break;
			case 2: // 科室节点
				parentId = CATE_DEPART;
				break;
			case 3: // 药品节点,食谱节点,图书节点,讲座节点
				parentId = 0;
				break;
			}

		return itemCateService.getItemCateList(parentId, type);

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addItemCategory(@ModelAttribute TbCate tbItemCat, @RequestParam("type") int type) {
		itemCateService.addItemCate(tbItemCat, type);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateItemCategory(@ModelAttribute TbCate tbItemCat) {
		itemCateService.updateItemCate(tbItemCat);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteItemCategory(@PathVariable int id, @RequestParam("type") int type) {
		itemCateService.deleteItemCate(id, type);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/canteen/add/{ids}/{cid}/{names}", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> addCanteenItemCate(@PathVariable Integer[] ids, @PathVariable("cid") Integer parentId,
			@PathVariable("names") String[] names) {
		for (int i = 0; i < ids.length; i++) {
			itemCateService.addCanteenItemCate(ids[i], parentId, names[i]);
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/canteen/del/{ids}/{cid}", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> delCanteenItemCate(@PathVariable Integer[] ids, @PathVariable("cid") Integer parentId) {

		for (int i = 0; i < ids.length; i++) {
			itemCateService.delCanteenItemCate(ids[i], parentId);

		}
		return new ResultUtil<Object>().setData(null);
	}

}
