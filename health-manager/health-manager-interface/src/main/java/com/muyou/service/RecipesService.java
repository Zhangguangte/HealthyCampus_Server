package com.muyou.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.FoodMenuVo;
import com.muyou.common.vo.FoodRecommendVo;
import com.muyou.common.vo.FoodVo;
import com.muyou.common.vo.IngredientResultVo;
import com.muyou.common.vo.RecipesListVo;

public interface RecipesService {

	// 获得三餐
	public List<FoodMenuVo> getRecipesByThreeMeals(RequestForm requestForm);

	// 获得菜肴细节
	public FoodVo getRecipeDetail(RequestForm requestForm);

	// 获得推荐菜肴细节
	public List<FoodRecommendVo> getRecommendRecipes();

	// 获得材料细节
	public IngredientResultVo getIngredientResult(RequestForm requestForm);

	//功能食谱
	public RecipesListVo getRecipesList(RequestForm requestForm);

	//食谱列单
	public List<FoodMenuVo> getFoodList(String name, int row);

}
