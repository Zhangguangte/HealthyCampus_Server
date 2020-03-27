package com.muyou.service;

import java.util.List;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.pojo.TbTimetable;
import com.muyou.vo.AttendDateVo;
import com.muyou.vo.AttendVo;
import com.muyou.vo.TimeTableVo;

public interface ItemTimService {

	/**
	 * 教师课表数据
	 * 
	 * @param id
	 * @return
	 */
	TbTimetable getItemById(Integer id);

	/**
	 * 获得教师课表
	 * 
	 * @param id
	 * @param year
	 * @param semester
	 * @return
	 */
	TimeTableVo getItemList(Integer id, Integer year, Integer semester);

	/**
	 * 删除教师课表
	 * 
	 * @param id
	 * @return
	 */
	int delItem(Integer id);

	/**
	 * 添加教师课表
	 * 
	 * @param timetable
	 * @return
	 */
	int addItem(TbTimetable timetable);

	/**
	 * 更新教师课表
	 * 
	 * @param id
	 * @param timetable
	 * @return
	 */
	int updateItem(Integer id, TbTimetable timetable);

	/**
	 * 教师课表数据
	 * 
	 * @param id
	 * @return
	 */
	TbTimetable getNormalItemById(Integer id);

	/**
	 * 开始考勤
	 * 
	 * @param id
	 * @return
	 */
	int beginAttend(Integer id);

	/**
	 * 结束考勤
	 * 
	 * @param id
	 * @return
	 */
	int finishAttend(Integer id);

	/**
	 * 是否存在考勤
	 * 
	 * @param id
	 * @return
	 */
	AttendVo beforeAttend(Integer id);

	/**
	 * 考勤列表
	 * 
	 * @param id
	 * @return
	 */
	List<AttendVo> attendList(Integer id);
	
	/**
	 * 历史记录
	 * 
	 * @param tid
	 * @return
	 */
	List<AttendDateVo> attendDate(Integer tid);
	
	/**
	 * 根据日期获得考勤列表
	 * @param date
	 * @param tid
	 * @return
	 */
	DataTablesResult attendListByDate(String date,Integer tid);
	
	/**
	 * 修改考勤记录
	 * @param id
	 * @param no
	 * @param type
	 * @return
	 */
	int updateAttend(Integer id, String no, Integer type);
}
