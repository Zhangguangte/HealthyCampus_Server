package com.muyou.service;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.pojo.TbRecipes;
import com.muyou.vo.RecipesVo;

public interface ItemRecService {

	/**
	 * 获取食谱总数
	 * 
	 * @return
	 */
	DataTablesResult getAllItemCount();

	/**
	 * 分页搜索排序获取食谱列表
	 * 
	 * @param draw
	 * @param start
	 * @param length
	 * @param cid
	 * @param search
	 * @param orderCol
	 * @param orderDir
	 * @return
	 */
	DataTablesResult getItemList(int draw, int start, int length, int cid, String search, String orderCol,
			String orderDir);

	 /**
     * 多条件查询获取食谱列表
     * @param draw
     * @param start
     * @param length
     * @param cid
     * @param search
     * @param minDate
     * @param maxDate
     * @param orderCol
     * @param orderDir
     * @return
     */
    DataTablesResult getItemSearchList(int draw,int start,int length,int cid,
                                       String search,String minDate,String maxDate,
                                       String orderCol,String orderDir);

    /**
     * 分页搜索排序查询获取三餐列表
     * @param draw
     * @param start
     * @param length
     * @param cid
     * @param search
     * @param minDate
     * @param maxDate
     * @param orderCol
     * @param orderDir
     * @return
     */
    DataTablesResult getItemMealList(int draw,int start,int length,int cid,
                                       String search,String minDate,String maxDate,
                                       String orderCol,String orderDir);

    
    /**
     * 通过ID获取食谱详情
     * @param itemId
     * @return
     */
	RecipesVo getItemById(Integer itemId);
	
	/**
	 * 通过ID获取食谱详情
	 * @param itemId
	 * @return
	 */
	RecipesVo getItemShowById(Integer itemId);
	
	/**
	 * 通过ID
	 * 
	 * @param id
	 * @return
	 */
	TbRecipes getNormalItemById(Integer id);
	
	/**
     * 更新食谱
     * @param id
     * @param recipesVo
     * @return
     */
    int updateItem(Integer id,RecipesVo recipesVo);
    
    /**
	 * 修改食谱状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	int alertItemState(Integer id, Boolean state);

	/**
	 * 彻底删除食谱
	 * 
	 * @param id
	 * @return
	 */
	int deleteItem(int id);

	/**
     * 添加食谱
     * @param recipesVo
     * @return
     */
    int addItem(RecipesVo recipesVo);
    
}
