package com.muyou.common.pojo;

import java.io.Serializable;

public class DiseaseSortVo implements Serializable {
	public String id;
	public String title;
	public String introduction;
	public String url;

	public DiseaseSortVo() {}
	
//	public DiseaseSortVo(TbDisease disease) {
//		this.id = disease.getId() + "";
//		this.title = disease.getDiseaseName();
//		this.introduction = disease.getDiseaseIntroduce();
//		this.url = disease.getDiseaseUrl();
//	}

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
