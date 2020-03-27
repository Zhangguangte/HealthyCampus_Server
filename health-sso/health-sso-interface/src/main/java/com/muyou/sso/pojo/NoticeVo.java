package com.muyou.sso.pojo;

import java.io.Serializable;

import com.muyou.pojo.TbNews;

public class NoticeVo implements Serializable {
	public String id;
	public String create_time;
	public String content;
	public String status;
	public String noticeType;

	public NoticeVo(TbNews news) {
		this.id = news.getId() + "";
		this.create_time = news.getCreateTime();
		this.content = news.getContent();
		this.status = news.getNewsUser().getStatus();
		this.noticeType = news.getUserType();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	
	
	
}
