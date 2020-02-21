package com.muyou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muyou.pojo.TbRole;
import com.muyou.pojo.TbRoleExample;

public interface TbRoleMapper {
    int countByExample(TbRoleExample example);

    int deleteByExample(TbRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbRole record);

    int insertSelective(TbRole record);

    List<TbRole> selectByExample(TbRoleExample example);

    TbRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbRole record, @Param("example") TbRoleExample example);

    int updateByExample(@Param("record") TbRole record, @Param("example") TbRoleExample example);

    int updateByPrimaryKeySelective(TbRole record);

    int updateByPrimaryKey(TbRole record);
    
    List<String> getAdminRoles(@Param("id") int id);
    
    
    
}