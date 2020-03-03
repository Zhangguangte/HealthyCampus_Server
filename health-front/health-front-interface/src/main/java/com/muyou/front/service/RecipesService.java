package com.muyou.front.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.front.vo.FoodMenuVo;
import com.muyou.front.vo.FoodRecommendVo;
import com.muyou.front.vo.FoodVo;
import com.muyou.front.vo.IngredientResultVo;
import com.muyou.front.vo.RecipesListVo;

/**
 * 食谱服务
 * @author 木友茶
 *
 */
public interface RecipesService {

	// 获得三餐
	 List<FoodMenuVo> getRecipesByThreeMeals(RequestForm requestForm);

	// 获得菜肴细节
	 FoodVo getRecipeDetail(RequestForm requestForm);

	// 获得推荐菜肴细节
	 List<FoodRecommendVo> getRecommendRecipes(RequestForm form);

	// 获得材料细节
	 IngredientResultVo getIngredientResult(RequestForm requestForm);

	//功能食谱
	 RecipesListVo getRecipesList(RequestForm requestForm);

	//食谱列单
	 List<FoodMenuVo> getFoodList(String name, int row);

}
