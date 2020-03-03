package com.muyou.front.service.impl;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.StringUtil;
import com.muyou.front.service.RecipesService;
import com.muyou.front.vo.FoodMenuVo;
import com.muyou.front.vo.FoodRecommendVo;
import com.muyou.front.vo.FoodVo;
import com.muyou.front.vo.IngredientResultVo;
import com.muyou.front.vo.RecipesListVo;
import com.muyou.mapper.TbIngredientsMapper;
import com.muyou.mapper.TbRecipesClassifyMapper;
import com.muyou.mapper.TbRecipesMapper;
import com.muyou.pojo.TbIngredients;
import com.muyou.pojo.TbIngredientsExample;
import com.muyou.pojo.TbRecipes;
import com.muyou.pojo.TbRecipesClassify;
import com.muyou.pojo.TbRecipesExample;

@Service
public class RecipesServiceImpl implements RecipesService {

	private final static String[] titles = { "早餐", "午餐", "晚餐" };
	private DecimalFormat decimalFormat = new DecimalFormat("0.00");

	@Autowired
	private TbRecipesMapper recipesMapper;

	@Autowired
	private TbIngredientsMapper ingredientsMapper;

	@Autowired
	private TbRecipesClassifyMapper recipesClassifyMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${RECIPES_MEALS}")
	private String RECIPES_MEALS;

	@Value("${RECIPES_DETAIL}")
	private String RECIPES_DETAIL;

	@Value("${INGREDIENT_DETAIL}")
	private String INGREDIENT_DETAIL;

	@Value("${RECIPES_FUNCTION}")
	private String RECIPES_FUNCTION;

	@Value("${RECIPES_LIST}")
	private String RECIPES_LIST;

	@Value("${RECIPES_EXPIRE}")
	private Integer RECIPES_EXPIRE;

	@Value("${INGREDIENT_EXPIRE}")
	private Integer INGREDIENT_EXPIRE;

	// 三餐
	@Override
	public List<FoodMenuVo> getRecipesByThreeMeals(RequestForm requestForm) {

		try {
			String json = jedisClient.hget(
					RECIPES_MEALS + ":" + requestForm.getContent() + ":" + requestForm.getQuest_id(),
					requestForm.getContent());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, FoodMenuVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<TbRecipes> recipes = recipesMapper.getRecipesByThreeMeals(requestForm.getQuest_id(),
				requestForm.getContent());
		if (null == recipes)
			return null;
		float sum = 0;
		List<FoodMenuVo> list = new LinkedList<>();

		// -1代表标题;-2代表菜肴项
		FoodMenuVo foodMenuVo = new FoodMenuVo();
		foodMenuVo.setDishName(titles[requestForm.getRow()]);
		foodMenuVo.setMold(-1);

		for (TbRecipes recipe : recipes) {
			sum += Float.parseFloat(recipe.getCalorie());
			list.add(new FoodMenuVo(recipe));
		}

		foodMenuVo.setCalorie(decimalFormat.format(sum));
		list.add(0, foodMenuVo);

		try {
			jedisClient.hset(RECIPES_MEALS + ":" + requestForm.getContent() + ":" + requestForm.getQuest_id(),
					requestForm.getContent(), JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 获得菜肴细节
	@Override
	public FoodVo getRecipeDetail(RequestForm requestForm) {

		try {
			String json = jedisClient
					.get(RECIPES_DETAIL + ":" + requestForm.getType() + ":" + requestForm.getQuest_id());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, FoodVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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

		FoodVo foodVo = new FoodVo(recipes);

		try {
			jedisClient.set(RECIPES_DETAIL + ":" + requestForm.getType() + ":" + requestForm.getQuest_id(),
					JsonUtils.objectToJson(foodVo));
			jedisClient.expire(RECIPES_DETAIL + ":" + requestForm.getType() + ":" + requestForm.getQuest_id(),
					RECIPES_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return foodVo;
	}

	// 获得推荐菜肴细节
	@Override
	public List<FoodRecommendVo> getRecommendRecipes(RequestForm form) {

		List<FoodRecommendVo> result = new LinkedList<>();
		String phy;
		if (StringUtil.isEmpty(form.getQuest_id()))
			phy = null;
		else
			phy = form.getQuest_id();
		List<TbRecipes> tbRecipes = recipesMapper.getRecommendRecipes(phy);
		for (TbRecipes recipes : tbRecipes) {
			result.add(new FoodRecommendVo(recipes));
		}

		List<TbIngredients> tbIngredients = ingredientsMapper.getIngredientsTips();
		int i = 1;
		for (TbIngredients ingredients : tbIngredients) {
			result.add(3 * i++ - 1, new FoodRecommendVo(ingredients));
		}
		return result;
	}

	// 获得材料细节
	@Override
	public IngredientResultVo getIngredientResult(RequestForm requestForm) {

		try {
			String json = jedisClient.get(INGREDIENT_DETAIL + ":" + requestForm.getContent());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, IngredientResultVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbIngredientsExample ingredientsExample = new TbIngredientsExample();
		ingredientsExample.setRow(1);
		TbIngredientsExample.Criteria criteria = ingredientsExample.createCriteria();
		criteria.andNameLike(requestForm.getContent());
		List<TbIngredients> ingredients = ingredientsMapper.selectByExample(ingredientsExample);
		if (null == ingredients || 0 == ingredients.size())
			return null;
		IngredientResultVo ingredientResultVo = new IngredientResultVo(ingredients.get(0));

		try {
			jedisClient.set(INGREDIENT_DETAIL + ":" + requestForm.getContent(),
					JsonUtils.objectToJson(ingredientResultVo));
			jedisClient.expire(INGREDIENT_DETAIL + ":" + requestForm.getContent(), INGREDIENT_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ingredientResultVo;
	}

	// 功能食谱
	@Override
	public RecipesListVo getRecipesList(RequestForm requestForm) {

		try {
			String json = jedisClient.hget(RECIPES_FUNCTION, requestForm.getType() + "");
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, RecipesListVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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

		try {
			jedisClient.hset(RECIPES_FUNCTION, requestForm.getType() + "", JsonUtils.objectToJson(reListVo));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reListVo;
	}

	// 食谱列单
	@Override
	public List<FoodMenuVo> getFoodList(String name, int row) {

		try {
			String json = jedisClient.hget(RECIPES_LIST, name + ":" + row);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, FoodMenuVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbRecipesExample recipesExample = new TbRecipesExample();
		recipesExample.setRow(row);
		recipesExample.setSize(15);
		TbRecipesExample.Criteria criteria = recipesExample.createCriteria();
		criteria.andClassificationLike("%(" + name + ")%");
		List<TbRecipes> recipes = recipesMapper.selectByExample(recipesExample);
		if (null == recipes || recipes.size() == 0)
			return null;
		List<FoodMenuVo> result = new LinkedList<FoodMenuVo>();
		for (TbRecipes recipe : recipes) {
			result.add(new FoodMenuVo(recipe));
		}

		try {
			jedisClient.hset(RECIPES_LIST, name + ":" + row, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
