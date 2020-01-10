package com.muyou.mapper;

import com.muyou.pojo.TbRecipesClassify;
import com.muyou.pojo.TbRecipesClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRecipesClassifyMapper {
    int countByExample(TbRecipesClassifyExample example);

    int deleteByExample(TbRecipesClassifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbRecipesClassify record);

    int insertSelective(TbRecipesClassify record);

    List<TbRecipesClassify> selectByExample(TbRecipesClassifyExample example);

    TbRecipesClassify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbRecipesClassify record, @Param("example") TbRecipesClassifyExample example);

    int updateByExample(@Param("record") TbRecipesClassify record, @Param("example") TbRecipesClassifyExample example);

    int updateByPrimaryKeySelective(TbRecipesClassify record);

    int updateByPrimaryKey(TbRecipesClassify record);
}