package com.muyou.mapper;

import com.muyou.pojo.TbMedicine;
import com.muyou.pojo.TbMedicineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbMedicineMapper {
	int countByExample(TbMedicineExample example);

	int deleteByExample(TbMedicineExample example);

	int deleteByPrimaryKey(Integer goodsId);

	int insert(TbMedicine record);

	int insertSelective(TbMedicine record);

	List<TbMedicine> selectByExample(TbMedicineExample example);

	// **********************************************

	List<TbMedicine> getAllMedicine(@Param("classifyName") String classifyName, @Param("start") int start);

	List<TbMedicine> getAllMedicineByKey(@Param("scope") String scope, @Param("content") String content,
			@Param("start") int start);

	TbMedicine getMedicineDetailByName(@Param("content") String content);

	TbMedicine getMedicineDetailByCode(@Param("bar_code") String bar_code);

	// **********************************************
	TbMedicine selectByPrimaryKey(Integer goodsId);

	int updateByExampleSelective(@Param("record") TbMedicine record, @Param("example") TbMedicineExample example);

	int updateByExample(@Param("record") TbMedicine record, @Param("example") TbMedicineExample example);

	int updateByPrimaryKeySelective(TbMedicine record);

	int updateByPrimaryKey(TbMedicine record);
}