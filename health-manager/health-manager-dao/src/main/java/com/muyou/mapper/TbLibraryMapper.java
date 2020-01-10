package com.muyou.mapper;

import com.muyou.pojo.TbLibrary;
import com.muyou.pojo.TbLibraryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbLibraryMapper {
    int countByExample(TbLibraryExample example);

    int deleteByExample(TbLibraryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbLibrary record);

    int insertSelective(TbLibrary record);

    List<TbLibrary> selectByExample(TbLibraryExample example);

    TbLibrary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbLibrary record, @Param("example") TbLibraryExample example);

    int updateByExample(@Param("record") TbLibrary record, @Param("example") TbLibraryExample example);

    int updateByPrimaryKeySelective(TbLibrary record);

    int updateByPrimaryKey(TbLibrary record);
}