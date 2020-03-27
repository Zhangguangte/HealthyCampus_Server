package com.muyou.front.service.impl;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.constant.ItemConstant;
import com.muyou.common.constant.RedisConstant;
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
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbClassifyMapper;
import com.muyou.mapper.TbIngredientsMapper;
import com.muyou.mapper.TbRecipesMapper;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbCateExample;
import com.muyou.pojo.TbIngredients;
import com.muyou.pojo.TbIngredientsExample;
import com.muyou.pojo.TbRecipes;

@Service
public class RecipesServiceImpl implements RecipesService {

	private final static String[] titles = { "早餐", "午餐", "晚餐" };

	private final static String[] weeks = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

	private final static String[] classify = { "人群膳食 ", "疾病调理", "功能调理", "脏腑调理" };

	private DecimalFormat decimalFormat = new DecimalFormat("0.00");

	@Autowired
	private TbRecipesMapper recipesMapper;

	@Autowired
	private TbIngredientsMapper ingredientsMapper;

	@Autowired
	private TbClassifyMapper classifyMapper;

	@Autowired
	private TbCateMapper cateMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${RECIPES_DETAIL}")
	private String RECIPES_DETAIL;

	@Value("${INGREDIENT_DETAIL}")
	private String INGREDIENT_DETAIL;

	@Value("${RECIPES_LIST}")
	private String RECIPES_LIST;

	@Value("${RECIPES_COUNT}")
	private Integer RECIPES_COUNT;

	@Value("${RECIPES_EXPIRE}")
	private Integer RECIPES_EXPIRE;

	@Value("${INGREDIENT_EXPIRE}")
	private Integer INGREDIENT_EXPIRE;

	// 三餐
	@Override
	public List<FoodMenuVo> getRecipesByThreeMeals(RequestForm requestForm) {

		System.out.println(requestForm.getMap().get("place"));

		try {
			String json = jedisClient.hget(ItemConstant.RECIPES_MEALS, requestForm.getMap().get("place") + ":"
					+ requestForm.getMap().get("week") + ":" + requestForm.getMap().get("type"));
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, FoodMenuVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获得食堂目录ID
		TbCateExample example = new TbCateExample();
		TbCateExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(requestForm.getMap().get("place"));
		criteria.andTypeEqualTo(ItemConstant.CATE_CATTEN);
		criteria.andIsParentEqualTo(true);
		List<TbCate> cateList = cateMapper.selectByExample(example);

		if (null == cateList || cateList.size() < 1)
			return null;

		// 获取食谱所有ID节点
		List<String> ids = cateMapper.selectCanteenCate(cateList.get(0).getId(),
				weeks[Integer.valueOf(requestForm.getMap().get("week"))],
				titles[Integer.valueOf(requestForm.getMap().get("type"))]);
		if (null == ids || ids.size() < 1)
			return null;

		List<TbRecipes> recipes = recipesMapper.getRecipesByThreeMeals("(" + String.join(",", ids) + ")");
		if (null == recipes || recipes.size() < 1)
			return null;

		float sum = 0;
		List<FoodMenuVo> result = new LinkedList<>();

		// -1代表标题;-2代表菜肴项
		FoodMenuVo foodMenuVo = new FoodMenuVo();
		foodMenuVo.setDishName(titles[Integer.valueOf(requestForm.getMap().get("type"))]);
		foodMenuVo.setMold(-1);

		for (TbRecipes recipe : recipes) {
			sum += recipe.getCalorie().floatValue();
			result.add(new FoodMenuVo(recipe));
		}

		foodMenuVo.setCalorie(decimalFormat.format(sum));
		result.add(0, foodMenuVo);

		try {
			jedisClient.hset(
					ItemConstant.RECIPES_MEALS, requestForm.getMap().get("place") + ":"
							+ requestForm.getMap().get("week") + ":" + requestForm.getMap().get("type"),
					JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
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

	// 食谱分类
	@Override
	public RecipesListVo getRecipesList(RequestForm requestForm) {

		try {
			String json = jedisClient.hget(RedisConstant.RECIPES_CLASSIFY, requestForm.getType() + "");
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, RecipesListVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获得食谱子目录
		TbCateExample example = new TbCateExample();
		TbCateExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(ItemConstant.CATE_RECIPES);
		criteria.andIsParentEqualTo(true);
		criteria.andStatusEqualTo(ItemConstant.OPEN);
		criteria.andParentIdEqualTo(ItemConstant.CATE_RECIPES_PARENT);
		criteria.andNameEqualTo(classify[requestForm.getType()]);
		List<TbCate> cateList = cateMapper.selectByExample(example);
		List<String> list = cateMapper.selectSubCateNameByPIdAndType(cateList.get(0).getId(),
				ItemConstant.CATE_RECIPES);
		if (null == list || list.size() < 1)
			return null;

		// 获取子目录下的所有节点
		List<FoodMenuVo> result = getFoodList(list.get(0), requestForm.getRow());
		if (null == result || result.size() == 0)
			return null;
		
		RecipesListVo reListVo = new RecipesListVo();
		reListVo.setFoodList(result);
		reListVo.setClassList(list);

		try {
			jedisClient.hset(RedisConstant.RECIPES_CLASSIFY, requestForm.getType() + "", JsonUtils.objectToJson(reListVo));
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

		List<TbRecipes> recipes = recipesMapper.selectItemByClassify(row * RECIPES_COUNT, RECIPES_COUNT,
				"%(" + name + ")%");
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
