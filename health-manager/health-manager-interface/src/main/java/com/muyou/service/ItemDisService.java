package com.muyou.service;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.pojo.TbDisease;
import com.muyou.vo.DiseaseVo;

public interface ItemDisService {

	/**
     * 通过ID获取疾病详情
     * @param itemId
     * @return
     */
	DiseaseVo getItemById(Integer itemId);
    
	/**
	 * 通过ID
	 * 
	 * @param id
	 * @return
	 */
	TbDisease getNormalItemById(Integer id);

	/**
	 * 分页搜索排序获取疾病列表
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
     * 多条件查询获取疾病列表
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
     * 更新疾病
     * @param id
     * @param diseaseVo
     * @return
     */
    int updateItem(Integer id,DiseaseVo diseaseVo);
    
	/**
	 * 获取疾病总数
	 * 
	 * @return
	 */
	DataTablesResult getAllItemCount();

	/**
	 * 修改疾病状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	int alertItemState(Integer id, Boolean state);

	/**
	 * 彻底删除疾病
	 * 
	 * @param id
	 * @return
	 */
	int deleteItem(int id);

	/**
     * 添加疾病
     * @param diseaseVo
     * @return
     */
    int addItem(DiseaseVo diseaseVo);
}
