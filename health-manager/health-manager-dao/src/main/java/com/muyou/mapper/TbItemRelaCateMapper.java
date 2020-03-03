package com.muyou.mapper;

import com.muyou.pojo.TbItemRelaCate;
import com.muyou.pojo.TbItemRelaCateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemRelaCateMapper {
    int countByExample(TbItemRelaCateExample example);

    int deleteByExample(TbItemRelaCateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbItemRelaCate record);

    int insertSelective(TbItemRelaCate record);

    List<TbItemRelaCate> selectByExample(TbItemRelaCateExample example);

    TbItemRelaCate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbItemRelaCate record, @Param("example") TbItemRelaCateExample example);

    int updateByExample(@Param("record") TbItemRelaCate record, @Param("example") TbItemRelaCateExample example);

    int updateByPrimaryKeySelective(TbItemRelaCate record);

    int updateByPrimaryKey(TbItemRelaCate record);
}