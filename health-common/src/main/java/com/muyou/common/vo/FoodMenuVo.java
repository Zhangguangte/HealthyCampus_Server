package com.muyou.common.vo;

import java.io.Serializable;

public class FoodMenuVo implements Serializable {
	public String id;
	public String pictureUrl;
	public String dishName;
	public String calorie;
	public String type;
	public int mold;

	public int getMold() {
		return mold;
	}

	public void setMold(int mold) {
		this.mold = mold;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getCalorie() {
		return calorie;
	}

	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
