package com.muyou.service;

import java.util.List;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.BookDetailVo;
import com.muyou.common.vo.BookVo;
import com.muyou.common.vo.CourseVo;
import com.muyou.common.vo.LectureVo;

public interface ServiceService {
	/******************* 图书 ****************************/
	public BookDetailVo searchBookDetail(RequestForm requestForm) ;

	public List<BookVo> searchBook(RequestForm requestForm) ;

	/*********** ********* 讲座 ****************************/
	public LectureVo getLectureDetail(RequestForm requestForm) ;

	public List<LectureVo> getLectureList(RequestForm requestForm);

	/******************* 反馈 ****************************/
	public void sendFeed(RequestForm requestForm);

	/******************* 课表 ****************************/
	public List<CourseVo> getTimeTable(RequestForm requestForm);

}
