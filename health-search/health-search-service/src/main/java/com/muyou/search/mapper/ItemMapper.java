package com.muyou.search.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbMedicine;

public interface ItemMapper {

	
	List<TbMedicine> getMedicineItemList();

	TbMedicine getMedicineItemById(@Param("id") Integer itemId);

	List<String> getMedicineType(@Param("id") Integer itemId);
	
	
	
	List<TbDisease> getDiseaseItemList();

	TbDisease getDiseaseItemById(@Param("id") Integer itemId);
	
	List<String> getDiseaseType(@Param("id") Integer itemId,@Param("type") Integer type);

	Integer getDocumentsNum(@Param("type") String type);
}
