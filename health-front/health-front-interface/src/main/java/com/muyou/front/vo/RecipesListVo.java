package com.muyou.front.vo;

import java.io.Serializable;
import java.util.List;

public class RecipesListVo implements Serializable{
	public List<String> classList;
	public List<FoodMenuVo> foodList;
	
	public RecipesListVo() {}
	
	public List<String> getClassList() {
		return classList;
	}
	public void setClassList(List<String> classList) {
		this.classList = classList;
	}
	public List<FoodMenuVo> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<FoodMenuVo> foodList) {
		this.foodList = foodList;
	}
	

}
