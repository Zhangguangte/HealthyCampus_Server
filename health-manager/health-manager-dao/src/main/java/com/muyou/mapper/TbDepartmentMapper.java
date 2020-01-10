package com.muyou.mapper;

import com.muyou.pojo.TbDepartment;
import com.muyou.pojo.TbDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDepartmentMapper {
	int countByExample(TbDepartmentExample example);

	int deleteByExample(TbDepartmentExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbDepartment record);

	int insertSelective(TbDepartment record);

	List<TbDepartment> selectByExample(TbDepartmentExample example);

	List<TbDepartment> selectAll();

	TbDepartment selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbDepartment record, @Param("example") TbDepartmentExample example);

	int updateByExample(@Param("record") TbDepartment record, @Param("example") TbDepartmentExample example);

	int updateByPrimaryKeySelective(TbDepartment record);

	int updateByPrimaryKey(TbDepartment record);
}