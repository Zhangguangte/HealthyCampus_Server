package com.muyou.vo;

import java.io.Serializable;

import com.muyou.pojo.TbDisease;

public class DiseaseSortVo implements Serializable {
	public String id;
	public String title;
	public String introduction;
	public String url;

	public DiseaseSortVo() {}
	
	public DiseaseSortVo(TbDisease disease) {
		this.id = disease.getId() + "";
		this.title = disease.getName();
		this.introduction = disease.getIntroduce();
		this.url = disease.getUrl();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
