package com.muyou.mapper;

import com.muyou.pojo.TbFriendship;
import com.muyou.pojo.TbFriendshipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbFriendshipMapper {
	int countByExample(TbFriendshipExample example);

	int deleteByExample(TbFriendshipExample example);

	int deleteByPrimaryKey(String id);

	int insert(TbFriendship record);

	int insertSelective(TbFriendship record);

	//是否是好友
	int isFriendship(@Param("user_id") String userId, @Param("friend_id") String friendId);

	List<TbFriendship> selectByExample(TbFriendshipExample example);

	TbFriendship selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") TbFriendship record, @Param("example") TbFriendshipExample example);

	int updateByExample(@Param("record") TbFriendship record, @Param("example") TbFriendshipExample example);

	int updateByPrimaryKeySelective(TbFriendship record);

	int updateByPrimaryKey(TbFriendship record);
}