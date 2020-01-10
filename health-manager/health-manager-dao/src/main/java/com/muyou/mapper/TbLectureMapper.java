package com.muyou.mapper;

import com.muyou.pojo.TbLecture;
import com.muyou.pojo.TbLectureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbLectureMapper {
    int countByExample(TbLectureExample example);

    int deleteByExample(TbLectureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbLecture record);

    int insertSelective(TbLecture record);

    List<TbLecture> selectByExampleWithBLOBs(TbLectureExample example);

    List<TbLecture> selectByExample(TbLectureExample example);

    TbLecture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbLecture record, @Param("example") TbLectureExample example);

    int updateByExampleWithBLOBs(@Param("record") TbLecture record, @Param("example") TbLectureExample example);

    int updateByExample(@Param("record") TbLecture record, @Param("example") TbLectureExample example);

    int updateByPrimaryKeySelective(TbLecture record);

    int updateByPrimaryKeyWithBLOBs(TbLecture record);

    int updateByPrimaryKey(TbLecture record);
}