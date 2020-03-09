package com.muyou.service;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.pojo.TbLibrary;
import com.muyou.vo.LibraryVo;

public interface ItemLibService {

	/**
	 * 获取图书总数
	 * 
	 * @return
	 */
	DataTablesResult getAllItemCount();

	/**
	 * 分页搜索排序获取图书列表
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
     * 多条件查询获取图书列表
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
     * 通过ID获取图书详情
     * @param itemId
     * @return
     */
	LibraryVo getItemById(Integer itemId);
	
	/**
	 * 通过ID
	 * 
	 * @param id
	 * @return
	 */
	TbLibrary getNormalItemById(Integer id);
	
	/**
     * 更新图书
     * @param id
     * @param libraryVo
     * @return
     */
    int updateItem(Integer id,LibraryVo libraryVo);
    
    /**
	 * 修改图书状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	int alertItemState(Integer id, Boolean state);

	/**
	 * 彻底删除图书
	 * 
	 * @param id
	 * @return
	 */
	int deleteItem(int id);

	/**
     * 添加图书
     * @param libraryVo
     * @return
     */
    int addItem(LibraryVo libraryVo);
    
}
