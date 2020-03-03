package com.muyou.service;

import java.util.List;

import com.muyou.common.pojo.ZTreeNode;
import com.muyou.pojo.TbCate;

public interface ItemCateService {

	/**
	 * 通过id获取
	 * 
	 * @param id
	 * @return
	 */
	TbCate getItemCateById(int id);

	/**
	 * 获得分类树
	 * 
	 * @param parentId
	 * @return
	 */
	List<ZTreeNode> getItemCateList(int parentId, int type);

	/**
	 * 添加分类
	 * @param cate
	 * @param type
	 * @return
	 */
	int addItemCate(TbCate cate,int type);

	/**
	 * 编辑分类
	 * 
	 * @param cate
	 * @return
	 */
	int updateItemCate(TbCate cate);

	/**
	 * 删除单个分类
	 * 
	 * @param id
	 */
	void deleteItemCate(int id, int type);

	/**
	 * 递归删除
	 * 
	 * @param id
	 */
	void deleteZTree(int id, int type);

}
