package com.muyou.common.vo;

import java.io.Serializable;

public class FoodRecommendVo implements Serializable{
	public String id;
	public String picture;
	public String title;
	public String description;
	public int mold;

	public FoodRecommendVo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMold() {
		return mold;
	}

	public void setMold(int mold) {
		this.mold = mold;
	}

}
