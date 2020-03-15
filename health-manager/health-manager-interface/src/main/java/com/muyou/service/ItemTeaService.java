package com.muyou.service;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.pojo.TbAdmin;
import com.muyou.pojo.TbRecipes;

public interface ItemTeaService {

	/**
	 * 通过ID
	 * 
	 * @param id
	 * @return
	 */
	TbAdmin getNormalItemById(Integer id);
	
	/**
	 * 获取教师总数
	 * 
	 * @return
	 */
	DataTablesResult getAllItemCount();
	
	/**
	 * 分页搜索排序获取教师列表
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
	 * 分页搜索排序获取学院教师列表
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
	DataTablesResult getItemCollege(int draw, int start, int length, int cid, String search, String orderCol,
			String orderDir);
	
    /**
     * 更新教师
     * @param id
     * @param admin
     * @return
     */
    int updateItem(Integer id,TbAdmin admin);
    
    /**
	 * 修改教师状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	int alertItemState(Integer id, Boolean state);

	void updateRedis(Integer id);
}
