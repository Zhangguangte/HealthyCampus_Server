package com.muyou.mapper;

import com.muyou.pojo.TbRequest;
import com.muyou.pojo.TbRequestExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TbRequestMapper {
	int countByExample(TbRequestExample example);

	int deleteByExample(TbRequestExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbRequest record);

	int insertSelective(TbRequest record);

	List<TbRequest> selectByExample(TbRequestExample example);

	// ************************************
	// 获得所有好友请求，好友通知
	List<TbRequest> requestFriends(@Param("user_id") String id);

	// 清空好友通知
	void clearRequestFriends1(@Param("user_id") String userId);

	void clearRequestFriends2(@Param("user_id") String userId);

	// 发送好友请求，添加好友消息
	int isExistRequst(@Param("user_id") String userId, @Param("quest_id") String quest_id);

	void updateRequestTime(@Param("content") String content, @Param("quest_id") String quest_id,
			@Param("createTime") String createTime, @Param("user_id") String userId);

	void sendRequestFriend(@Param("quest_id") String quest_id, @Param("content") String content,
			@Param("createTime") String createTime, @Param("user_id") String userId);

	// 同意好友请求，新好友
	int saveRequestFriend(@Param("user_id") String userId, @Param("quest_id") String quest_id);

	// 拒绝好友请求，新好友
	int refuseRequestFriend(@Param("user_id") String userId, @Param("quest_id") String quest_id);

	// ***********************************************
	TbRequest selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbRequest record, @Param("example") TbRequestExample example);

	int updateByExample(@Param("record") TbRequest record, @Param("example") TbRequestExample example);

	int updateByPrimaryKeySelective(TbRequest record);

	int updateByPrimaryKey(TbRequest record);
}