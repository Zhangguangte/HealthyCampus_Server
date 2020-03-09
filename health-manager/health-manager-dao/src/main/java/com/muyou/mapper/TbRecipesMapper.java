package com.muyou.mapper;

import com.muyou.pojo.TbRecipes;
import com.muyou.pojo.TbRecipesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRecipesMapper {
	int countByExample(TbRecipesExample example);

	int deleteByExample(TbRecipesExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbRecipes record);

	int insertSelective(TbRecipes record);

	List<TbRecipes> selectByExample(TbRecipesExample example);

	TbRecipes selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbRecipes record, @Param("example") TbRecipesExample example);

	int updateByExample(@Param("record") TbRecipes record, @Param("example") TbRecipesExample example);

	int updateByPrimaryKeySelective(TbRecipes record);

	int updateByPrimaryKey(TbRecipes record);

	// 客户端
	List<TbRecipes> getRecommendRecipes(@Param("physique") String physique);

	List<TbRecipes> getRecipesByThreeMeals(@Param("type") String type, @Param("week") String week);

	List<TbRecipes> getRecipeDetailByName(@Param("name") String name);

	List<TbRecipes> selectItemByClassify(@Param("row") int row, @Param("count") int count,
			@Param("search") String search);

	// 后台
	List<TbRecipes> selectItemByCondition(@Param("cid") int cid, @Param("search") String search,
			@Param("orderCol") String orderCol, @Param("orderDir") String orderDir);

	List<TbRecipes> selectItemByMultiCondition(@Param("cid") int cid, @Param("search") String search,
			@Param("minDate") String minDate, @Param("maxDate") String maxDate, @Param("orderCol") String orderCol,
			@Param("orderDir") String orderDir);

	List<TbRecipes> selectItemByIds(@Param("ids") String ids, @Param("orderCol") String orderCol,
			@Param("orderDir") String orderDir);

}