package com.muyou.front.vo;

import java.io.Serializable;

import com.muyou.pojo.TbIngredients;
import com.muyou.pojo.TbRecipes;

public class FoodRecommendVo implements Serializable {
	public String id;
	public String picture;
	public String title;
	public String description;
	public int mold;	//0.代表推荐食谱;1.代表食材小知识

	public FoodRecommendVo() {}
	
	public FoodRecommendVo(TbRecipes recipes) {
		this.id = recipes.getId()+"";
		this.picture = recipes.getUrl();
		this.title = recipes.getName();
		this.mold = 0;
	}

	public FoodRecommendVo(TbIngredients ingredients) {
		this.id = ingredients.getId()+"";
		this.picture = ingredients.getUrl();
		this.title = ingredients.getName();
		int ra = (int) (Math.random() * 9) % 3;
		switch (ra) {
		case 0:
			this.description = ingredients.getSynopsis();
			break;
		case 1:
			this.description = ingredients.getNutritive();
			break;
		case 2:
			this.description = ingredients.getEffect();
			break;

		}

		this.mold = 1;
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
