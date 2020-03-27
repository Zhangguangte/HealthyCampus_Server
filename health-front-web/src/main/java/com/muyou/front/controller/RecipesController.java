package com.muyou.front.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.util.StringUtil;
import com.muyou.front.service.RecipesService;
import com.muyou.front.vo.FoodMenuVo;
import com.muyou.front.vo.FoodRecommendVo;
import com.muyou.front.vo.FoodVo;
import com.muyou.front.vo.IngredientResultVo;
import com.muyou.front.vo.RecipesListVo;
import com.muyou.sso.service.CollectService;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

	@Autowired
	private RecipesService recipesService;

	@Autowired
	private CollectService collectService;

	// 获得三餐
	@RequestMapping("/getRecipesByThreeMeals")
	@ResponseBody
	public List<FoodMenuVo> getRecipesByThreeMeals(@RequestBody RequestForm requestForm) throws ServiceException {
		if (null == requestForm.getMap())
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<FoodMenuVo> result = recipesService.getRecipesByThreeMeals(requestForm);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}

	// 获得菜肴细节
	@RequestMapping("/getRecipeDetail")
	@ResponseBody
	public FoodVo getRecipeDetail(@RequestBody RequestForm requestForm,
			@RequestHeader(value = "User", required = false) String user) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		FoodVo result = recipesService.getRecipeDetail(requestForm);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_RECIPES_NOT_FOUND);

		if (StringUtils.isNotBlank(user))
			result.setCollect(collectService.isCollect("RECIPES", result.getId(), user));

		return result;
	}

	// 获得推荐菜肴
	@RequestMapping("/getRecommendRecipes")
	@ResponseBody
	public List<FoodRecommendVo> getRecommendRecipes(@RequestBody RequestForm form) throws ServiceException {
		List<FoodRecommendVo> result = recipesService.getRecommendRecipes(form);
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
	public RecipesListVo getRecipesList(@RequestBody RequestForm requestForm) throws ServiceException {
		return recipesService.getRecipesList(requestForm);
	}

	// 食谱列单
	@RequestMapping("/getFoodList")
	@ResponseBody
	public List<FoodMenuVo> getFoodList(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<FoodMenuVo> result = recipesService.getFoodList(requestForm.getContent(), requestForm.getRow());
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}
}
