package com.muyou.service;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.pojo.TbStudent;
import com.muyou.pojo.TbStudent;

public interface ItemStuService {

	/**
	 * 获取学生总数
	 * 
	 * @return
	 */
	DataTablesResult getAllItemCount();

	/**
	 * 分页搜索排序获取学生列表
	 * 
	 * @param draw
	 * @param start
	 * @param length
	 * @param cid
	 * @param orderCol
	 * @param orderDir
	 * @return
	 */
	DataTablesResult getItemList(int draw, int start, int length, int cid,String orderCol,
			String orderDir);

	 /**
     * 多条件查询获取学生列表
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
     * 通过ID获取学生详情
     * @param itemId
     * @return
     */
	TbStudent getItemById(Integer itemId);
	
	/**
	 * 通过ID
	 * 
	 * @param id
	 * @return
	 */
	TbStudent getNormalItemById(Integer id);
	
	/**
     * 更新学生
     * @param id
     * @param student
     * @return
     */
    int updateItem(Integer id,TbStudent student);
    
    /**
	 * 修改学生状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	int alertItemState(Integer id, Boolean state);

	/**
	 * 彻底删除学生
	 * 
	 * @param id
	 * @return
	 */
	int deleteItem(int id);

	/**
     * 添加学生
     * @param student
     * @return
     */
    int addItem(TbStudent student);
    
}
