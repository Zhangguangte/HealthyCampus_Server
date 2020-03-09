package com.muyou.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LectureVo implements Serializable {


	private Integer id;

	private String title;

	private String college;

	private String content;

	private String author;

	private Boolean status;

	private Date created;

	private Date updated;

	private List<String> cid;

	private List<String> cname;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college == null ? null : college.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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
}
