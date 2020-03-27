package com.muyou.mapper;

import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbMedicine;
import com.muyou.pojo.TbMedicineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbMedicineMapper {
    int countByExample(TbMedicineExample example);

    int deleteByExample(TbMedicineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbMedicine record);

    int insertSelective(TbMedicine record);

    List<TbMedicine> selectByExample(TbMedicineExample example);

    TbMedicine selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbMedicine record, @Param("example") TbMedicineExample example);

    int updateByExample(@Param("record") TbMedicine record, @Param("example") TbMedicineExample example);

    int updateByPrimaryKeySelective(TbMedicine record);

    int updateByPrimaryKey(TbMedicine record);
    
    //客户端
	List<TbMedicine> selectItemByClassify(@Param("cid") int cid, @Param("page") int page, @Param("row") int row);

	List<TbMedicine> getAllMedicineByKey(@Param("scope") String scope, @Param("content") String content,
			@Param("start") int start);

	TbMedicine getMedicineDetailByName(@Param("content") String content);

	TbMedicine getMedicineDetailByCode(@Param("bar_code") String bar_code);

	//后台
	List<TbMedicine> selectItemByCondition(@Param("cid") int cid, @Param("search") String search,
			@Param("orderCol") String orderCol, @Param("orderDir") String orderDir);

	List<TbMedicine> selectItemByMultiCondition(@Param("cid") int cid, @Param("search") String search,
			@Param("minDate") String minDate, @Param("maxDate") String maxDate, @Param("orderCol") String orderCol,
			@Param("orderDir") String orderDir);

}