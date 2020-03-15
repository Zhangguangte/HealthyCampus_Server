package com.muyou.mapper;

import com.muyou.pojo.TbAdmin;
import com.muyou.pojo.TbAdminExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface TbAdminMapper {
	int countByExample(TbAdminExample example);

	int deleteByExample(TbAdminExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbAdmin record);

	int insertSelective(TbAdmin record);

	List<TbAdmin> selectByExample(TbAdminExample example);

	TbAdmin selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbAdmin record, @Param("example") TbAdminExample example);

	int updateByExample(@Param("record") TbAdmin record, @Param("example") TbAdminExample example);

	int updateByPrimaryKeySelective(TbAdmin record);

	int updateByPrimaryKey(TbAdmin record);

	List<String> getPermsByRoleId(@Param("id") int id);

	Set<String> getRoles(@Param("name") String name);

    Set<String> getPermissions(@Param("username") String username);

	List<TbAdmin> selectItemByIds(@Param("ids") String ids, @Param("orderCol") String orderCol,
			@Param("orderDir") String orderDir);
}