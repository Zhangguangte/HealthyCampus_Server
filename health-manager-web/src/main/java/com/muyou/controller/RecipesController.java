package com.muyou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.util.StringUtil;
import com.muyou.common.vo.FoodMenuVo;
import com.muyou.common.vo.FoodRecommendVo;
import com.muyou.common.vo.FoodVo;
import com.muyou.common.vo.IngredientResultVo;
import com.muyou.common.vo.RecipesListVo;
import com.muyou.common.vo.ResponseBuilder;
import com.muyou.service.RecipesService;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

	@Autowired
	private RecipesService recipesService;

	// 获得三餐
	@RequestMapping("/getRecipesByThreeMeals")
	@ResponseBody
	public List<FoodMenuVo> getRecipesByThreeMeals(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getQuest_id()) || StringUtil.isEmpty(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<FoodMenuVo> result = recipesService.getRecipesByThreeMeals(requestForm);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}

	// 获得菜肴细节
	@RequestMapping("/getRecipeDetail")
	@ResponseBody
	public FoodVo getRecipeDetail(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		FoodVo result = recipesService.getRecipeDetail(requestForm);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_RECIPES_NOT_FOUND);
		return result;
	}

	// 获得推荐菜肴
	@RequestMapping("/getRecommendRecipes")
	@ResponseBody
	public List<FoodRecommendVo> getRecommendRecipes() throws ServiceException {
		List<FoodRecommendVo> result = recipesService.getRecommendRecipes();
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}

	// 获得材料细节
	@RequestMapping("/getIngredientResult")
	@ResponseBody
	public IngredientResultVo getIngredientResult(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		IngredientResultVo result = recipesService.getIngredientResult(requestForm);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_INGREDIENT_NOT_FOUND);
		return result;
	}

	// 功能食谱
	@RequestMapping("/getRecipesList")
	@ResponseBody
	public RecipesListVo getRecipesList(@RequestBody RequestForm requestForm) throws ServiceException{
		RecipesListVo result = recipesService.getRecipesList(requestForm);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}

	// 食谱列单
	@RequestMapping("/getFoodList")
	@ResponseBody
	public List<FoodMenuVo> getFoodList(@RequestBody RequestForm requestForm) throws ServiceException{
		if(StringUtil.isEmpty(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<FoodMenuVo>  result = recipesService.getFoodList(requestForm.getContent(),requestForm.getRow());
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}
}
