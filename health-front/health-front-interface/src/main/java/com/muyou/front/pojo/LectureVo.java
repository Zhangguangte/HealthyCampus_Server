package com.muyou.front.pojo;

import java.io.Serializable;

import com.muyou.pojo.TbLecture;

public class LectureVo implements Serializable {

	public String id;
	public String content;
	public String date;
	public String title;
	public String college;
	public String author;

	public LectureVo() {}
	
	//1.代表详细信息;0.代表基本信息
	public LectureVo(TbLecture lecture, int type) {
		this.id = lecture.getId() + "";
		this.title = lecture.getTitle();
		this.date = lecture.getDate();
		if (type == 1) {
			this.content = lecture.getContent();
			this.college = lecture.getCollege();
			this.author = lecture.getAuthor();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
