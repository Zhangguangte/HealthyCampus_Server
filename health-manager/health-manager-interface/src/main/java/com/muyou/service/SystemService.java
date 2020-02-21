package com.muyou.service;

import java.util.List;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.pojo.Result;
import com.muyou.pojo.TbBase;
import com.muyou.pojo.TbShiroFilter;

public interface SystemService {

	/**
	 * 获得shiro过滤链配置
	 * 
	 * @return
	 */
	List<TbShiroFilter> getShiroFilter();

	/**
	 * 统计过滤链数目
	 * 
	 * @return
	 */
	int countShiroFilter();

	/**
	 * 添加shiro过滤链
	 * 
	 * @param tbShiroFilter
	 * @return
	 */
	int addShiroFilter(TbShiroFilter tbShiroFilter);

	/**
	 * 更新shiro过滤链
	 * 
	 * @param tbShiroFilter
	 * @return
	 */
	int updateShiroFilter(TbShiroFilter tbShiroFilter);

	/**
	 * 删除shiro过滤链
	 * 
	 * @param id
	 * @return
	 */
	int deleteShiroFilter(int id);

	/**
	 * 获得网页浏览量
	 * 
	 * @param isIncr
	 * @return
	 */
	DataTablesResult getBrowseCount(boolean isIncr);

	/**
	 * 获取网站基础设置
	 * 
	 * @return
	 */
	Result<TbBase> getBase();

	/**
	 * 更新网站信息
	 * 
	 * @param tbBase
	 * @return
	 */
	int updateBase(TbBase tbBase);

	/**
	 * 获取日志列表
	 * 
	 * @param draw
	 * @param start
	 * @param length
	 * @param search
	 * @param orderCol
	 * @param orderDir
	 * @return
	 */
	DataTablesResult getLogList(int draw, int start, int length, String search, String orderCol, String orderDir);

	/**
	 * 统计日志数量
	 * 
	 * @return
	 */
	int countLog();
	
	 /**
     * 删除日志
     * @param id
     * @return
     */
    int deleteLog(int id);
    
    
}
