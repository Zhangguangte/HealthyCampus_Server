package com.muyou.mapper;

import com.muyou.pojo.TbAttend;
import com.muyou.pojo.TbAttendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAttendMapper {
    int countByExample(TbAttendExample example);

    int deleteByExample(TbAttendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbAttend record);

    int insertSelective(TbAttend record);

    List<TbAttend> selectByExample(TbAttendExample example);

    TbAttend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbAttend record, @Param("example") TbAttendExample example);

    int updateByExample(@Param("record") TbAttend record, @Param("example") TbAttendExample example);

    int updateByPrimaryKeySelective(TbAttend record);

    int updateByPrimaryKey(TbAttend record);
}