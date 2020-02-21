package com.muyou.search.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbMedicine;

public interface ItemMapper {

	List<TbMedicine> getMedicineItemList();

	TbMedicine getMedicineItemById(@Param("id") long itemId);

	List<TbDisease> getDiseaseItemList();

	TbDisease getDiseaseItemById(@Param("id") long itemId);

	Integer getDocumentsNum(@Param("type") String type);
}
