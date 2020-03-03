package com.muyou.front.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.front.vo.CourseVo;

/**
 * 课表服务
 * @author 木友茶
 *
 */
public interface TimeTableService {
	List<CourseVo> getTimeTable(RequestForm requestForm);
}
