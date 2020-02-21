package com.muyou.service;

import java.util.List;

import com.muyou.pojo.TbDict;

public interface DictService {

	/**
	 * 获取扩展词库列表
	 * 
	 * @return
	 */
	List<TbDict> getDictList();

	/**
     * 获取停用词库列表
     * @return
     */
    List<TbDict> getStopList();
    
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	int delDict(int id);

	/**
	 * 添加
	 * 
	 * @param tbDict
	 * @return
	 */
	int addDict(TbDict tbDict);

	/**
	 * 更新
	 * 
	 * @param tbDict
	 * @return
	 */
	int updateDict(TbDict tbDict);

}
