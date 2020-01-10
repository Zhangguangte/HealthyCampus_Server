package com.muyou.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.BookDetailVo;
import com.muyou.common.vo.BookVo;
import com.muyou.common.vo.CourseVo;
import com.muyou.common.vo.LectureVo;
import com.muyou.mapper.TbFeedbackMapper;
import com.muyou.mapper.TbLectureMapper;
import com.muyou.mapper.TbLibraryMapper;
import com.muyou.mapper.TbTimetableMapper;
import com.muyou.pojo.TbFeedback;
import com.muyou.pojo.TbLecture;
import com.muyou.pojo.TbLectureExample;
import com.muyou.pojo.TbLibrary;
import com.muyou.pojo.TbLibraryExample;
import com.muyou.pojo.TbTimetable;
import com.muyou.pojo.TbTimetableExample;
import com.muyou.service.ServiceService;

@Service("serviceServiceImpl")
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private TbLibraryMapper libraryMapper;

	@Autowired
	private TbLectureMapper lectureMapper;

	@Autowired
	private TbFeedbackMapper feedbackMapper;

	@Autowired
	private TbTimetableMapper timetableMapper;

	/******************* 图书 ****************************/
	@Override
	public BookDetailVo searchBookDetail(RequestForm requestForm) {
		TbLibrary library = libraryMapper.selectByPrimaryKey(Integer.parseInt(requestForm.getQuest_id()));
		if (null == library)
			throw null;
		else {
			BookDetailVo book = new BookDetailVo();
			book.setClassify(library.getClassify());
			book.setName(library.getName());
			book.setAuthor(library.getAuthor());
			book.setPrice(library.getPrice() + "");
			book.setPublish(library.getPublish());
			book.setIntroduced(library.getIntroduced());
			book.setUrl(library.getUrl());
			book.setSum(library.getSum());
			book.setRest(library.getRest());
			book.setPlace(library.getPlace());
			book.setIndex(library.getbIndex());
			return book;
		}

	}

	@Override
	public List<BookVo> searchBook(RequestForm requestForm) {
		TbLibraryExample tbLibraryExample = new TbLibraryExample();
		TbLibraryExample.Criteria criteria = tbLibraryExample.createCriteria();
		criteria.andNameLike(requestForm.getContent());
		List<TbLibrary> books = libraryMapper.selectByExample(tbLibraryExample);
		if (null == books||books.size()==0)
			throw null;
		List<BookVo> bookVos = new LinkedList<>();
		BookVo bookvo;
		for (TbLibrary tbLibrary : books) {
			bookvo = new BookVo();
			bookvo.setId(tbLibrary.getId() + "");
			bookvo.setBookName(tbLibrary.getName());
			bookvo.setAuthor(tbLibrary.getAuthor());
			bookvo.setPublish(tbLibrary.getPublish());
			bookvo.setClassify(tbLibrary.getClassify());
			bookvo.setRest(tbLibrary.getRest());
			bookVos.add(bookvo);
		}
		return bookVos;
	}

	/******************* 讲座 ****************************/
	@Override
	public LectureVo getLectureDetail(RequestForm requestForm) {
		TbLecture lecture = lectureMapper.selectByPrimaryKey(Integer.parseInt(requestForm.getQuest_id()));
		if (null == lecture)
			throw null;
		LectureVo lectureVo = new LectureVo();
		lectureVo.setId(lecture.getId() + "");
		lectureVo.setTitle(lecture.getTitle());
		lectureVo.setDate(lecture.getDate());
		lectureVo.setContent(lecture.getContent());
		lectureVo.setAuthor(lecture.getAuthor());
		lectureVo.setCollege(lecture.getCollege());
		return lectureVo;
	}

	@Override
	public List<LectureVo> getLectureList(RequestForm requestForm) {
		TbLectureExample tbLectureExample = new TbLectureExample();
		tbLectureExample.setOrderByClause("date desc");
		tbLectureExample.setRow(requestForm.getRow());
		TbLectureExample.Criteria criteria = tbLectureExample.createCriteria();
		criteria.andCollegeLike(requestForm.getContent());
		List<TbLecture> books = lectureMapper.selectByExample(tbLectureExample);
		if (null == books || books.size() == 0)
			throw null;
		List<LectureVo> lectureVos = new LinkedList<>();
		LectureVo lectureVo;
		for (TbLecture lecture : books) {
			lectureVo = new LectureVo();
			lectureVo.setId(lecture.getId() + "");
			lectureVo.setTitle(lecture.getTitle());
			lectureVo.setDate(lecture.getDate());
			lectureVos.add(lectureVo);
		}
		return lectureVos;
	}

	/******************* 反馈 ****************************/
	@Override
	public void sendFeed(RequestForm requestForm) {
		feedbackMapper.insert(new TbFeedback(requestForm.getContent(), requestForm.getQuest_id()));
	}

	/******************* 课表 ****************************/
	@Override
	public List<CourseVo> getTimeTable(RequestForm requestForm) {
		TbTimetableExample tbTimetableExample = new TbTimetableExample();
		TbTimetableExample.Criteria criteria = tbTimetableExample.createCriteria();
		criteria.andMajorLike(requestForm.getContent());
		List<TbTimetable> list = timetableMapper.selectByExample(tbTimetableExample);
		if (null == list)
			throw null;
		List<CourseVo> result = new LinkedList<CourseVo>();
		
		CourseVo courseVo;
		for (TbTimetable timetable : list) {
			courseVo = new CourseVo();
			courseVo.setStart(timetable.getcStart()); 
			courseVo.setWeek(timetable.getWeeks());
			courseVo.setDescribe(timetable.getDescr());
			courseVo.setPeriod(timetable.getPeriod()); 
			result.add(courseVo);
		}
		return result;
	}

}
