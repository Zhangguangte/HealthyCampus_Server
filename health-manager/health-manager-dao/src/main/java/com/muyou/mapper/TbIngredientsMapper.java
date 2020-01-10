package com.muyou.mapper;

import com.muyou.pojo.TbIngredients;
import com.muyou.pojo.TbIngredientsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbIngredientsMapper {
	int countByExample(TbIngredientsExample example);

	int deleteByExample(TbIngredientsExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbIngredients record);

	int insertSelective(TbIngredients record);

	List<TbIngredients> selectByExample(TbIngredientsExample example);

	List<TbIngredients> getIngredientsTips();

	TbIngredients selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbIngredients record, @Param("example") TbIngredientsExample example);

	int updateByExample(@Param("record") TbIngredients record, @Param("example") TbIngredientsExample example);

	int updateByPrimaryKeySelective(TbIngredients record);

	int updateByPrimaryKey(TbIngredients record);
}