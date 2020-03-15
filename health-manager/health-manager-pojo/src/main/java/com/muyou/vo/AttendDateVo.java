package com.muyou.vo;

import java.io.Serializable;
import java.util.Date;

public class AttendDateVo implements Serializable {

	private Date time;
	private String no;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
}
