package com.muyou.mapper;

import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbDiseaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDiseaseMapper {
	int countByExample(TbDiseaseExample example);

	int deleteByExample(TbDiseaseExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbDisease record);

	int insertSelective(TbDisease record);

	List<TbDisease> selectByExample(TbDiseaseExample example);

	TbDisease selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbDisease record, @Param("example") TbDiseaseExample example);

	int updateByExample(@Param("record") TbDisease record, @Param("example") TbDiseaseExample example);

	int updateByPrimaryKeySelective(TbDisease record);

	int updateByPrimaryKey(TbDisease record);

	List<TbDisease> selectItemByCondition(@Param("cid") int cid, @Param("search") String search,
			@Param("orderCol") String orderCol, @Param("orderDir") String orderDir);

	List<TbDisease> selectItemByMultiCondition(@Param("cid") int cid, @Param("search") String search,
			@Param("minDate") String minDate, @Param("maxDate") String maxDate, @Param("orderCol") String orderCol,
			@Param("orderDir") String orderDir);

}