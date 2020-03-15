package com.muyou.vo;

import java.io.Serializable;
import java.util.Date;

public class AttendVo implements Serializable {

	private Integer total;

	private String name;
	
	private String avator;
	
	private Date time;

	private Boolean isFinsh;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Boolean getIsFinsh() {
		return isFinsh;
	}

	public void setIsFinsh(Boolean isFinsh) {
		this.isFinsh = isFinsh;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvator() {
		return avator;
	}

	public void setAvator(String avator) {
		this.avator = avator;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
