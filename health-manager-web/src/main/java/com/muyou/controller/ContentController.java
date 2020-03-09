package com.muyou.controller;

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
import com.muyou.pojo.TbPanelContent;
import com.muyou.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping(value = "/list/{panelId}", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getContentByCid(@PathVariable int panelId) {
		DataTablesResult result = contentService.getPanelContentListByPanelId(panelId);
		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addContent(@ModelAttribute TbPanelContent tbPanelContent) {
		contentService.addPanelContent(tbPanelContent);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/del/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> addContent(@PathVariable int[] ids) {
		for (int id : ids) {
			contentService.deletePanelContent(id);
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateContent(@ModelAttribute TbPanelContent tbPanelContent) {
		contentService.updateContent(tbPanelContent);
		return new ResultUtil<Object>().setData(null);
	}
}
