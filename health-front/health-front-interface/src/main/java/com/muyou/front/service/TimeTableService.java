package com.muyou.front.service;

import java.util.List;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.front.vo.AttendCourseVo;
import com.muyou.front.vo.CourseVo;

/**
 * 课表服务
 * 
 * @author 木友茶
 *
 */
public interface TimeTableService {
	/**
	 * 课表数据
	 * @param requestForm
	 * @return
	 */
	List<CourseVo> getTimeTable(RequestForm requestForm);

	/**
	 * 是否正在签到
	 * @param requestForm
	 * @return
	 */
	AttendCourseVo getAttend(RequestForm requestForm);

	/**
	 * 签到
	 * @param requestForm
	 * @return
	 */
	ResponseBuilder attendCourse(RequestForm requestForm) throws ServiceException;

	/**
	 * 过往签到历史
	 * @param requestForm
	 * @return
	 */
	List<String> getAttendList(RequestForm requestForm) throws ServiceException;
}
