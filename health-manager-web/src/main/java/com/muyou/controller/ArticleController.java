package com.muyou.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.FileUtil;
import com.muyou.common.util.ResultUtil;
import com.muyou.pojo.TbArticle;
import com.muyou.service.ArticleService;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
@RequestMapping("/article")
public class ArticleController {

	private Logger log = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfig;

	@Value("${HTML_GEN_PATH}")
	private String HTML_GEN_PATH;

	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	@ResponseBody
	public Result<TbArticle> getItemById(@PathVariable Integer itemId) {
		return new ResultUtil<TbArticle>().setData(articleService.getItemById(itemId));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getItemList(int draw, int start, int length, @RequestParam("order[0][column]") int orderCol,
			@RequestParam("order[0][dir]") String orderDir, String searchItem, String minDate, String maxDate) {
		// 获取客户端需要排序的列
		String[] cols = { "id", "picUrl", "title", "introduction", "created", "updated", "status" };
		String orderColumn = cols[orderCol];
		if (orderColumn == null) {
			orderColumn = "created";
		}
		// 获取排序方式 默认为desc(asc)
		if (orderDir == null) {
			orderDir = "asc";
		}
		return articleService.getItemList(draw, start, length, orderColumn, orderDir);
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getAllItemCount() {
		return articleService.getAllItemCount();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateItem(@PathVariable Integer id, TbArticle article) {
		articleService.updateItem(id, article);
		HtmlGen(id);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/stop/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> stopItem(@PathVariable Integer id) {
		articleService.alertItemState(id, false);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/start/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> startItem(@PathVariable Integer id) {
		articleService.alertItemState(id, true);
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/del/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> deleteItem(@PathVariable Integer[] ids) {
		for (Integer id : ids) {
			articleService.deleteItem(id);
			try {
				FileUtil.deleteFile(HTML_GEN_PATH + id + ".html");
			} catch (Exception e) {
				log.error("页面删除失败");
				e.printStackTrace();
			}
		}
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addItem(TbArticle article) {
		int id = articleService.addItem(article);
		HtmlGen(id);
		return new ResultUtil<Object>().setData(null);
	}

	private void HtmlGen(int id) {
		try {
			TbArticle article = articleService.getItemById(id);
			Map<String, Object> dataModel = new HashMap<String, Object>();
			dataModel.put("item", article);
			Configuration configuration = freeMarkerConfig.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			Writer out = new FileWriter(new File(HTML_GEN_PATH + id + ".html"));
			template.process(dataModel, out);
			out.close();
		} catch (Exception e) {
			log.error("生成页面失败");
			e.printStackTrace();
		}

	}

}
