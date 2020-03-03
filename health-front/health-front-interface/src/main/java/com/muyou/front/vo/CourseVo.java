package com.muyou.front.vo;

import java.io.Serializable;

import com.muyou.pojo.TbTimetable;

public class CourseVo  implements Serializable{
	public int start;
	public int week;
	public String describe;
	public int period ;
	
	public CourseVo() {}
	
	public CourseVo(TbTimetable timetable) {
		this.start = timetable.getcStart();
	    this.week = timetable.getWeeks();
	    this.describe= timetable.getDescr();
	    this.period= timetable.getPeriod();
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

}
