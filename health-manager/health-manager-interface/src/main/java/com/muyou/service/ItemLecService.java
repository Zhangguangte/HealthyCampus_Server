package com.muyou.service;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.pojo.TbLecture;
import com.muyou.vo.LectureVo;

public interface ItemLecService {

	/**
	 * 获取讲座总数
	 * 
	 * @return
	 */
	DataTablesResult getAllItemCount();

	/**
	 * 分页搜索排序获取讲座列表
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
     * 多条件查询获取讲座列表
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
     * 通过ID获取讲座详情
     * @param itemId
     * @return
     */
	LectureVo getItemById(Integer itemId);
	
	/**
	 * 通过ID
	 * 
	 * @param id
	 * @return
	 */
	TbLecture getNormalItemById(Integer id);
	
	/**
     * 更新讲座
     * @param id
     * @param lectureVo
     * @return
     */
    int updateItem(Integer id,LectureVo lectureVo);
    
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
     * @param lectureVo
     * @return
     */
    int addItem(LectureVo lectureVo);
    
}
