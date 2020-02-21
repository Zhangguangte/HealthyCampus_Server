package com.muyou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.muyou.pojo.TbUser;
import com.muyou.pojo.TbUserExample;

public interface TbUserMapper {
	int countByExample(TbUserExample example);

	int deleteByExample(TbUserExample example);

	int deleteByPrimaryKey(Integer no);

	int insert(TbUser record);

	int insertSelective(TbUser record);

	List<TbUser> selectByExample(TbUserExample example);

	TbUser selectByPrimaryKey(Integer no);

	// ******************************************

	// 用户是否存在:账号、电话
	TbUser findUserByAccountOrPhone(@Param("account") String account);

	// 获得所有好友，通讯录
	List<TbUser> allFriend(@Param("user_id") String userId);

	// 用户是否存在:账号
	public TbUser findByAccount(String account);

	// ******************************************

	int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserExample example);

	int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

	int updateByPrimaryKeySelective(TbUser record);

	int updateByPrimaryKey(TbUser record);
}