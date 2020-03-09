package com.muyou.mapper;

import com.muyou.pojo.TbLibrary;
import com.muyou.pojo.TbLibraryExample;
import com.muyou.pojo.TbRecipes;

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

	// 后台
	List<TbLibrary> selectItemByCondition(@Param("cid") int cid, @Param("orderCol") String orderCol,
			@Param("orderDir") String orderDir);

	List<TbLibrary> selectItemByMultiCondition(@Param("cid") int cid, @Param("search") String search,
			@Param("minDate") String minDate, @Param("maxDate") String maxDate, @Param("orderCol") String orderCol,
			@Param("orderDir") String orderDir);

}