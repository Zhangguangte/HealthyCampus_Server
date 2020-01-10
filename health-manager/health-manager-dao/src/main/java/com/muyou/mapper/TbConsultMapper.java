package com.muyou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.muyou.pojo.TbConsult;
import com.muyou.pojo.TbConsultExample;

public interface TbConsultMapper {
    int countByExample(TbConsultExample example);

    int deleteByExample(TbConsultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbConsult record);

    int insertSelective(TbConsult record);

    List<TbConsult> selectByExampleWithBLOBs(TbConsultExample example);

    List<TbConsult> selectByExample(TbConsultExample example);

    TbConsult selectByPrimaryKey(Integer id);
    
	int insertConsult(@Param("describe") String describe, @Param("prescription") String prescription,
			@Param("history") String history, @Param("images") String images, @Param("user_id") String user_id);

    
}