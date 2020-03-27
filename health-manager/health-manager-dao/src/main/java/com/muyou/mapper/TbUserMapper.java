package com.muyou.mapper;

import com.muyou.pojo.TbUser;
import com.muyou.pojo.TbUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {
	int countByExample(TbUserExample example);

	int deleteByExample(TbUserExample example);

	int deleteByPrimaryKey(Integer no);

	int insert(TbUser record);

	int insertSelective(TbUser record);

	List<TbUser> selectByExample(TbUserExample example);

	TbUser selectByPrimaryKey(Integer no);

	int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserExample example);

	int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

	int updateByPrimaryKeySelective(TbUser record);

	int updateByPrimaryKey(TbUser record);

	TbUser findUserByAccountOrPhone(@Param("account") String account);

	List<TbUser> allFriend(@Param("user_id") String userId);

	public TbUser findByAccount(String account);
	
	void updateUsername(@Param("ids") String ids,@Param("nickname") String nickname);
}