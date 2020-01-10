package com.muyou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 界面配置
 * @author asus
 *
 */

@Controller
public class PageController {

	@RequestMapping("/")
	public String showIndex()
	{
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showpage(@PathVariable String page)
	{
		return page;
	}
}
