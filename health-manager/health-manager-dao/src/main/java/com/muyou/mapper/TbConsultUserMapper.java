package com.muyou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.muyou.common.form.PatienInforBean;
import com.muyou.pojo.TbConsultUser;
import com.muyou.pojo.TbConsultUserExample;

public interface TbConsultUserMapper {
	int countByExample(TbConsultUserExample example);

	int deleteByExample(TbConsultUserExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbConsultUser record);

	int insertSelective(TbConsultUser record);

	List<TbConsultUser> selectByExample(TbConsultUserExample example);

	TbConsultUser selectByPrimaryKey(Integer id);

	// ****************************************
	@Insert("insert into tb_consult_user(consult_id, name, card_id, sex, birthday, weight, allergy, history, liver,kidney)"
			+ " values(#{consult_id}, #{bean.name}, #{bean.card_id}, #{bean.sex}, #{bean.birthday}, #{bean.weight}, #{bean.allergy}, #{bean.history}, #{bean.liver}, #{bean.kidney})")
	void insertConsultUser(@Param("consult_id") int consult_id, @Param("bean") PatienInforBean bean);
	// ****************************************
}