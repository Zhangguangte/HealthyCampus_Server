package com.muyou.service;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.pojo.TbMedicine;
import com.muyou.vo.MedicineVo;

public interface ItemMedService {

	/**
	 * 获取药品总数
	 * 
	 * @return
	 */
	DataTablesResult getAllItemCount();

	/**
	 * 分页搜索排序获取药品列表
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
     * 多条件查询获取药品列表
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
     * 通过ID获取药品详情
     * @param itemId
     * @return
     */
	MedicineVo getItemById(Integer itemId);
	
	/**
	 * 通过ID
	 * 
	 * @param id
	 * @return
	 */
	TbMedicine getNormalItemById(Integer id);
	
	/**
     * 更新药品
     * @param id
     * @param diseaseVo
     * @return
     */
    int updateItem(Integer id,MedicineVo medicineVo);
    
    /**
	 * 修改药品状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	int alertItemState(Integer id, Boolean state);

	/**
	 * 彻底删除药品
	 * 
	 * @param id
	 * @return
	 */
	int deleteItem(int id);

	/**
     * 添加药品
     * @param diseaseVo
     * @return
     */
    int addItem(MedicineVo medicineVo);
}
