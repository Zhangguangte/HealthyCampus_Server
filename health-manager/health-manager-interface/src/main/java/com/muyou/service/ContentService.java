package com.muyou.service;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.pojo.TbPanelContent;

public interface ContentService {

	/**
	 * 通过panelId获取板块具体内容
	 * 
	 * @param panelId
	 * @return
	 */
	DataTablesResult getPanelContentListByPanelId(int panelId);

	/**
	 * 添加板块内容
	 * 
	 * @param tbPanelContent
	 * @return
	 */
	int addPanelContent(TbPanelContent tbPanelContent);

	/**
	 * 删除板块内容
	 * 
	 * @param id
	 * @return
	 */
	int deletePanelContent(int id);

	/**
	 * 编辑板块内容
	 * 
	 * @param tbPanelContent
	 * @return
	 */
	int updateContent(TbPanelContent tbPanelContent);

	/**
     * 通过id获取板块内容
     * @param id
     * @return
     */
    TbPanelContent getTbPanelContentById(int id);
}
