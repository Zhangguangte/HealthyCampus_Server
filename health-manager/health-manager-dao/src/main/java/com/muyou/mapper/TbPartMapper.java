package com.muyou.mapper;

import com.muyou.pojo.TbPart;
import com.muyou.pojo.TbPartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbPartMapper {
    int countByExample(TbPartExample example);

    int deleteByExample(TbPartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbPart record);

    int insertSelective(TbPart record);

    List<TbPart> selectByExample(TbPartExample example);
    
    List<TbPart> selectAll();

    TbPart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbPart record, @Param("example") TbPartExample example);

    int updateByExample(@Param("record") TbPart record, @Param("example") TbPartExample example);

    int updateByPrimaryKeySelective(TbPart record);

    int updateByPrimaryKey(TbPart record);
}