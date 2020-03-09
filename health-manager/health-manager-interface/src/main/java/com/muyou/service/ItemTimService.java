package com.muyou.service;

import com.muyou.pojo.TbTimetable;
import com.muyou.vo.TimeTableVo;

public interface ItemTimService {

	/**
	 * 教师课表数据
	 * @param id
	 * @return
	 */
	TbTimetable getItemById(Integer id);
	
	/**
	 * 获得教师课表
	 * @param cid
	 * @param year
	 * @param semester
	 * @return
	 */
	TimeTableVo getItemList(Integer cid,Integer year,Integer semester);
	
	/**
	 * 删除教师课表
	 * @param id
	 * @return
	 */
	int delItem(Integer id);
	
	/**
	 * 添加教师课表
	 * @param timetable
	 * @return
	 */
	int addItem(TbTimetable timetable);
	
	/**
	 * 更新教师课表
	 * @param id
	 * @param timetable
	 * @return
	 */
	 int updateItem(Integer id, TbTimetable timetable);
	 
	/**
	 * 教师课表数据
	 * @param id
	 * @return
	 */
	TbTimetable getNormalItemById(Integer id);
}
