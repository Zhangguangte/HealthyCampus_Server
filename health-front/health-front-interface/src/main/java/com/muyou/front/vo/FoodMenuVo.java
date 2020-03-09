package com.muyou.front.vo;

import java.io.Serializable;

import com.muyou.pojo.TbRecipes;

public class FoodMenuVo implements Serializable {
	public String id;
	public String pictureUrl;
	public String dishName;
	public String calorie;
	public String type;
	public int mold; // -1代表标题;-2代表菜肴项
	
	private static String[] title = { "breakfast", "lunch", "dinner" };

	public FoodMenuVo() {
	}

	public FoodMenuVo(TbRecipes recipes) {
		this.id = recipes.getId() + "";
		this.pictureUrl = recipes.getUrl();
		this.dishName = recipes.getName();
		this.calorie = recipes.getCalorie();
		this.type = title[recipes.getType()];
		this.mold = -2;
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

	public String getCalorie() {
		return calorie;
	}

	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMold() {
		return mold;
	}

	public void setMold(int mold) {
		this.mold = mold;
	}

}
