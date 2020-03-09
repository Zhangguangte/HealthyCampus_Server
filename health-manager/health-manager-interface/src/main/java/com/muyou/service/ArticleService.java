package com.muyou.service;

import com.muyou.common.pojo.DataTablesResult;

public interface ArticleService {

	/**
	 * 分页搜索排序获取文章列表
	 * 
	 * @param draw
	 * @param start
	 * @param length
	 * @param orderCol
	 * @param orderDir
	 * @return
	 */
	DataTablesResult getItemList(int draw, int start, int length, String orderCol,
			String orderDir);
	
}
