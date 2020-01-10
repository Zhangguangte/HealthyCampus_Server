package com.muyou.mapper;

import com.muyou.pojo.TbTimetable;
import com.muyou.pojo.TbTimetableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbTimetableMapper {
    int countByExample(TbTimetableExample example);

    int deleteByExample(TbTimetableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbTimetable record);

    int insertSelective(TbTimetable record);

    List<TbTimetable> selectByExample(TbTimetableExample example);

    TbTimetable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbTimetable record, @Param("example") TbTimetableExample example);

    int updateByExample(@Param("record") TbTimetable record, @Param("example") TbTimetableExample example);

    int updateByPrimaryKeySelective(TbTimetable record);

    int updateByPrimaryKey(TbTimetable record);
}