package com.muyou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muyou.common.pojo.DataTablesResult;
import com.muyou.mapper.TbArticleMapper;
import com.muyou.pojo.TbArticle;
import com.muyou.pojo.TbArticleExample;
import com.muyou.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private TbArticleMapper articleMapper;

	@Override
	public DataTablesResult getItemList(int draw, int start, int length, String orderCol, String orderDir) {
		DataTablesResult result = new DataTablesResult();

		// 分页执行查询返回结果
		PageHelper.startPage(start / length + 1, length);
		TbArticleExample example = new TbArticleExample();
		List<TbArticle> list = articleMapper.selectByExample(example);
		PageInfo<TbArticle> pageInfo = new PageInfo<>(list);
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal((int) pageInfo.getTotal());
		result.setSuccess(true);
		result.setData(list);

		return result;
	}
}
