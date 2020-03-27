package com.muyou.mapper;

import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbCateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCateMapper {
	Integer countByExample(TbCateExample example);

	Integer deleteByExample(TbCateExample example);

	Integer deleteByPrimaryKey(Integer id);

	Integer insert(TbCate record);

	Integer insertSelective(TbCate record);

	List<TbCate> selectByExample(TbCateExample example);

	TbCate selectByPrimaryKey(Integer id);

	Integer updateByExampleSelective(@Param("record") TbCate record, @Param("example") TbCateExample example);

	Integer updateByExample(@Param("record") TbCate record, @Param("example") TbCateExample example);

	Integer updateByPrimaryKeySelective(TbCate record);

	Integer updateByPrimaryKey(TbCate record);

	/**
	 * 前台
	 */
	List<String> selectCanteenCate(@Param("id") Integer id, @Param("cname1") String cname1,
			@Param("cname2") String cname2);

	/**
	 * 后台
	 * 
	 * @return
	 */

	List<TbCate> selectItemCate(@Param("itemId") Integer itemId, @Param("type") Integer type);

	List<String> selectCateNameByItemIdAndType(@Param("itemId") Integer itemId, @Param("type") Integer type);

	List<String> selectSubCateNameByPIdAndType(@Param("parentId") Integer parentId, @Param("type") Integer type);
}