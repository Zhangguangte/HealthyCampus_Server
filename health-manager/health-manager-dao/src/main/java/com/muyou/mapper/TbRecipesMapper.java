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

	List<TbRecipes> selectByExampleWithBLOBs(TbRecipesExample example);

	List<TbRecipes> selectByExample(TbRecipesExample example);

	List<TbRecipes> getRecommendRecipes();

	List<TbRecipes> getRecipesByThreeMeals(@Param("type") String type, @Param("week") String week);

	List<TbRecipes> getRecipeDetailByName(@Param("name") String name);

	TbRecipes selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbRecipes record, @Param("example") TbRecipesExample example);

	int updateByExampleWithBLOBs(@Param("record") TbRecipes record, @Param("example") TbRecipesExample example);

	int updateByExample(@Param("record") TbRecipes record, @Param("example") TbRecipesExample example);

	int updateByPrimaryKeySelective(TbRecipes record);

	int updateByPrimaryKeyWithBLOBs(TbRecipes record);

	int updateByPrimaryKey(TbRecipes record);
}