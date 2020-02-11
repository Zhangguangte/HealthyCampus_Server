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

}
