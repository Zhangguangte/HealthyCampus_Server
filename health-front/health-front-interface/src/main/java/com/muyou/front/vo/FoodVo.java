package com.muyou.front.vo;

import java.io.Serializable;

import com.muyou.pojo.TbRecipes;

public class FoodVo implements Serializable {
	public String id;
	public String dishName;
	public String practice;
	public String ingredients;
	public String pictureUrl;
	public String mainProcess;
	public String calorie;
	public String components;

	public String flavor;
	public String productionTime;
	public boolean collect;

	public FoodVo() {}
	
	public FoodVo(TbRecipes recipes) {
		this.id = recipes.getId()+"";
		this.dishName = recipes.getDishName();
		this.practice = recipes.getPractice();
		this.ingredients = recipes.getIngredients();
		this.pictureUrl = recipes.getPictureUrl();
		this.mainProcess = recipes.getMainProcess();
		this.productionTime = recipes.getProductionTime();
		this.calorie = recipes.getCalorie();
		this.components = recipes.getComponents();
		this.flavor = recipes.getFlavor();
	
	}
	
	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getMainProcess() {
		return mainProcess;
	}

	public void setMainProcess(String mainProcess) {
		this.mainProcess = mainProcess;
	}

	public String getCalorie() {
		return calorie;
	}

	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	public String getComponents() {
		return components;
	}

	public void setComponents(String components) {
		this.components = components;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getProductionTime() {
		return productionTime;
	}

	public void setProductionTime(String productionTime) {
		this.productionTime = productionTime;
	}

	public boolean isCollect() {
		return collect;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	public void setCollect(boolean collect) {
		this.collect = collect;
	}
}
