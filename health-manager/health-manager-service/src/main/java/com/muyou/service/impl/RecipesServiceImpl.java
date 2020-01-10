package com.muyou.service.impl;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.FoodMenuVo;
import com.muyou.common.vo.FoodRecommendVo;
import com.muyou.common.vo.FoodVo;
import com.muyou.common.vo.IngredientResultVo;
import com.muyou.common.vo.RecipesListVo;
import com.muyou.mapper.TbIngredientsMapper;
import com.muyou.mapper.TbRecipesClassifyMapper;
import com.muyou.mapper.TbRecipesMapper;
import com.muyou.pojo.TbIngredients;
import com.muyou.pojo.TbIngredientsExample;
import com.muyou.pojo.TbRecipes;
import com.muyou.pojo.TbRecipesClassify;
import com.muyou.pojo.TbRecipesExample;
import com.muyou.service.RecipesService;

@Service("recipesServiceImpl")
public class RecipesServiceImpl implements RecipesService {

	private final static String[] titles = { "早餐", "午餐", "晚餐" };
	private DecimalFormat decimalFormat = new DecimalFormat("0.00");

	@Autowired
	private TbRecipesMapper recipesMapper;

	@Autowired
	private TbIngredientsMapper ingredientsMapper;

	@Autowired
	private TbRecipesClassifyMapper recipesClassifyMapper;

	// 三餐
	@Override
	public List<FoodMenuVo> getRecipesByThreeMeals(RequestForm requestForm) {
		List<TbRecipes> recipes = recipesMapper.getRecipesByThreeMeals(requestForm.getQuest_id(),
				requestForm.getContent());
		if (null == recipes)
			return null;
		float sum = 0;
		List<FoodMenuVo> list = new LinkedList<>();

		FoodMenuVo foodMenuVo = new FoodMenuVo();
		foodMenuVo.setDishName(titles[requestForm.getRow()]);
		foodMenuVo.setMold(-1);

		FoodMenuVo f = null;
		for (TbRecipes recipe : recipes) {
			sum += Float.valueOf(recipe.getCalorie());
			f = new FoodMenuVo();
			f.setId(recipe.getId() + "");
			f.setPictureUrl(recipe.getPictureUrl());
			f.setDishName(recipe.getDishName());
			f.setCalorie(recipe.getCalorie());
			f.setType(recipe.getType());
			f.setMold(-2);
			list.add(f);
		}

		foodMenuVo.setCalorie(decimalFormat.format(sum));
		list.add(0, foodMenuVo);
		return list;
	}

	// 获得菜肴细节
	@Override
	public FoodVo getRecipeDetail(RequestForm requestForm) {
		TbRecipes recipes = null;
		if (0 == requestForm.getType())
			recipes = recipesMapper.selectByPrimaryKey(Integer.parseInt(requestForm.getQuest_id()));
		else {
			List<TbRecipes> tbRecipes = recipesMapper.getRecipeDetailByName(requestForm.getQuest_id());
			if (null == tbRecipes || tbRecipes.size() == 0)
				return null;
			else
				recipes = tbRecipes.get(0);
		}
		FoodVo foodVo = new FoodVo();
		foodVo.setId(recipes.getId() + "");
		foodVo.setDishName(recipes.getDishName());
		foodVo.setPractice(recipes.getPractice());
		foodVo.setIngredients(recipes.getIngredients());
		foodVo.setPictureUrl(recipes.getPictureUrl());
		foodVo.setMainProcess(recipes.getMainProcess());
		foodVo.setCalorie(recipes.getCalorie());
		foodVo.setComponents(recipes.getComponents());
		foodVo.setFlavor(recipes.getFlavor());
		return foodVo;
	}

	// 获得推荐菜肴细节
	@Override
	public List<FoodRecommendVo> getRecommendRecipes() {
		List<FoodRecommendVo> result = new LinkedList<>();
		FoodRecommendVo foodRecommendVo;

		List<TbRecipes> tbRecipes = recipesMapper.getRecommendRecipes();
		for (TbRecipes recipes : tbRecipes) {
			foodRecommendVo = new FoodRecommendVo();
			foodRecommendVo.setId(recipes.getId() + "");
			foodRecommendVo.setPicture(recipes.getPictureUrl());
			foodRecommendVo.setTitle(recipes.getDishName());
			foodRecommendVo.setMold(0);
			result.add(foodRecommendVo);
		}

		List<TbIngredients> tbIngredients = ingredientsMapper.getIngredientsTips();
		int i = 1;
		for (TbIngredients ingredients : tbIngredients) {
			foodRecommendVo = new FoodRecommendVo();
			foodRecommendVo.setId(ingredients.getId() + "");
			foodRecommendVo.setPicture(ingredients.getUrl());
			foodRecommendVo.setTitle(ingredients.getName());
			foodRecommendVo.setMold(1);
			int ra = (int) (Math.random() * 9) % 3;
			switch (ra) {
			case 0:
				foodRecommendVo.setDescription(ingredients.getSynopsis());
				break;
			case 1:
				foodRecommendVo.setDescription(ingredients.getNutritive());
				break;
			case 2:
				foodRecommendVo.setDescription(ingredients.getEffect());
				break;
			}
			result.add(3 * i++ - 1, foodRecommendVo);
		}
		return result;
	}

	// 获得材料细节
	@Override
	public IngredientResultVo getIngredientResult(RequestForm requestForm) {

		TbIngredientsExample ingredientsExample = new TbIngredientsExample();
		ingredientsExample.setRow(1);
		TbIngredientsExample.Criteria criteria = ingredientsExample.createCriteria();
		criteria.andNameLike(requestForm.getContent());
		List<TbIngredients> ingredients = ingredientsMapper.selectByExample(ingredientsExample);
		if (null == ingredients)
			return null;
		IngredientResultVo ingredientResultVo = new IngredientResultVo();

		ingredientResultVo.setCalorie(ingredients.get(0).getCalorie());
		ingredientResultVo.setName(ingredients.get(0).getName());
		ingredientResultVo.setSynopsis(ingredients.get(0).getSynopsis());
		ingredientResultVo.setEffect(ingredients.get(0).getEffect());
		ingredientResultVo.setUrl(ingredients.get(0).getUrl());
		ingredientResultVo.setComponents(ingredients.get(0).getComponents());
		ingredientResultVo.setNutritive(ingredients.get(0).getNutritive());
		return ingredientResultVo;
	}

	// 功能食谱
	@Override
	public RecipesListVo getRecipesList(RequestForm requestForm) {
		TbRecipesClassify reClassify = recipesClassifyMapper.selectByPrimaryKey(requestForm.getType());
		if (null == reClassify)
			return null;
		String[] clas = reClassify.getClassify().split(",");
		if (null == clas || clas.length == 0)
			return null;
		List<FoodMenuVo> result = getFoodList(clas[0], requestForm.getRow());
		if (null == result || result.size() == 0)
			return null;
		RecipesListVo reListVo = new RecipesListVo();
		reListVo.setFoodList(result);
		reListVo.setClassList(java.util.Arrays.asList(clas));
		return reListVo;
	}

	// 食谱列单
	@Override
	public List<FoodMenuVo> getFoodList(String name, int row) {

		TbRecipesExample recipesExample = new TbRecipesExample();
		recipesExample.setRow(row);
		recipesExample.setSize(15);
		TbRecipesExample.Criteria criteria = recipesExample.createCriteria();
		criteria.andClassificationLike("%(" + name + ")%");
		List<TbRecipes> recipes = recipesMapper.selectByExample(recipesExample);
		if (null == recipes || recipes.size() == 0)
			return null;

		List<FoodMenuVo> result = new LinkedList<FoodMenuVo>();
		FoodMenuVo foodMenuVo;
		for (TbRecipes recipe : recipes) {
			foodMenuVo = new FoodMenuVo();
			foodMenuVo.setId(recipe.getId() + "");
			foodMenuVo.setPictureUrl(recipe.getPictureUrl());
			foodMenuVo.setDishName(recipe.getDishName());
			foodMenuVo.setCalorie(recipe.getCalorie());
			foodMenuVo.setType(recipe.getType());
			foodMenuVo.setMold(-2);
			result.add(foodMenuVo);
		}

		return result;
	}

}
