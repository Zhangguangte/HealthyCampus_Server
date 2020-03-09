package com.muyou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.pojo.Result;
import com.muyou.common.pojo.ZTreeNode;
import com.muyou.common.util.ResultUtil;
import com.muyou.pojo.TbPanel;
import com.muyou.service.PanelService;

@Controller
@RequestMapping(value = "/panel")
public class PanelController {

	@Autowired
	private PanelService panelService;

	@RequestMapping(value = "/index/list", method = RequestMethod.GET)
	@ResponseBody
	public List<ZTreeNode> getIndexPanel() {
		return panelService.getPanelList(0, false);
	}

	@RequestMapping(value = "/indexAll/list", method = RequestMethod.GET)
	@ResponseBody
	public List<ZTreeNode> getAllIndexPanel() {
		return panelService.getPanelList(0, true);
	}

	@RequestMapping(value = "/indexBanner/list", method = RequestMethod.GET)
	@ResponseBody
	public List<ZTreeNode> getIndexBannerPanel() {
		return panelService.getPanelList(-1, true);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addContentCategory(@ModelAttribute TbPanel tbPanel) {
		panelService.addPanel(tbPanel);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateContentCategory(@ModelAttribute TbPanel tbPanel) {
		panelService.updatePanel(tbPanel);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/del/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteContentCategory(@PathVariable int[] ids) {
		for (int id : ids) {
			panelService.deletePanel(id);
		}
		return new ResultUtil<Object>().setData(null);
	}

}
