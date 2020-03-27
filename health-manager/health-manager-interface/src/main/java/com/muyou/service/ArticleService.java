package com.muyou.service;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.pojo.TbArticle;
import com.muyou.pojo.TbLecture;

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
	DataTablesResult getItemList(int draw, int start, int length, String orderCol, String orderDir);

	/**
	 * 获取讲座总数
	 * 
	 * @return
	 */
	DataTablesResult getAllItemCount();

	/**
	 * 通过ID获取讲座详情
	 * 
	 * @param itemId
	 * @return
	 */
	TbArticle getItemById(Integer itemId);

	/**
	 * 更新讲座
	 * 
	 * @param id
	 * @param article
	 * @return
	 */
	int updateItem(Integer id, TbArticle article);

	/**
	 * 修改讲座状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	int alertItemState(Integer id, Boolean state);

	/**
	 * 彻底删除讲座
	 * 
	 * @param id
	 * @return
	 */
	int deleteItem(int id);

	/**
	 * 添加讲座
	 * 
	 * @param article
	 * @return
	 */
	int addItem(TbArticle article);

}
