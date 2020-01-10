package com.muyou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muyou.pojo.TbNews;
import com.muyou.pojo.TbNewsExample;

public interface TbNewsMapper {
    int countByExample(TbNewsExample example);

    int deleteByExample(TbNewsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbNews record);

    int insertSelective(TbNews record);

    List<TbNews> selectByExampleWithBLOBs(TbNewsExample example);

    List<TbNews> selectByExample(TbNewsExample example);
    
    //***************************************************
    
	 List<TbNews> getAllNotice(@Param("userId") String userId,@Param("time") String time);

    
    //***************************************************
    
    
    
    

    TbNews selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbNews record, @Param("example") TbNewsExample example);

    int updateByExampleWithBLOBs(@Param("record") TbNews record, @Param("example") TbNewsExample example);

    int updateByExample(@Param("record") TbNews record, @Param("example") TbNewsExample example);

    int updateByPrimaryKeySelective(TbNews record);

    int updateByPrimaryKeyWithBLOBs(TbNews record);

    int updateByPrimaryKey(TbNews record);
}