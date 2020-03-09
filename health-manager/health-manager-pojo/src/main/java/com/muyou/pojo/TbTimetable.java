package com.muyou.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TbTimetable implements Serializable {
	private Integer id;

	private Integer tId;

	private String descr;

	private Integer semester;

	private Integer weeks;

	private Integer cStart;

	private Integer period;

	private Integer cYear;

	private Boolean state;

	private Date created;

	private Date updated;

	private List<String> cid;

	private List<String> cname;

	public List<String> getCid() {
		return cid;
	}

	public void setCid(List<String> cid) {
		this.cid = cid;
	}

	public List<String> getCname() {
		return cname;
	}

	public void setCname(List<String> cname) {
		this.cname = cname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr == null ? null : descr.trim();
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Integer getWeeks() {
		return weeks;
	}

	public void setWeeks(Integer weeks) {
		this.weeks = weeks;
	}

	public Integer getcStart() {
		return cStart;
	}

	public void setcStart(Integer cStart) {
		this.cStart = cStart;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getcYear() {
		return cYear;
	}

	public void setcYear(Integer cYear) {
		this.cYear = cYear;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}