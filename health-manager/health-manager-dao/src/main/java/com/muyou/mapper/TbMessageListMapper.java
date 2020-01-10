package com.muyou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Update;

import com.muyou.pojo.TbMessage;
import com.muyou.pojo.TbMessageList;
import com.muyou.pojo.TbMessageListExample;

public interface TbMessageListMapper {
	int countByExample(TbMessageListExample example);

	int deleteByExample(TbMessageListExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbMessageList record);

	int insertSelective(TbMessageList record);

	List<TbMessageList> selectByExample(TbMessageListExample example);

	// ***********************************
	// 创建房间号
	List<TbMessageList> getDoctorRoom(@Param("user_id") String user_id);

	// 删除房间号
	void deleteRoomId(@Param("room_id") String room_id);

	// 查看消息
	void lookmessage(@Param("roomid") String roomid, @Param("user_id") String id);

	// 所有消息根据两个用户ID
	TbMessageList searchRoomId(@Param("user_id1") String user_id1, @Param("user_id2") String user_id2);

	// 未读
	public void addUnread(@Param("roomid") String roomid, @Param("user_id") String id);

	//消息列表
	public void addMessageList(@Param("nickname") String nickname, @Param("roomid") String roomid,
			@Param("userId") String userId);

	// 创建房间号
	void addDoctorMessageList(@Param("nickname") String nickname, @Param("roomid") String roomid,
			@Param("userId") String userId);
	
	// ***********************************

	TbMessageList selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbMessageList record, @Param("example") TbMessageListExample example);

	int updateByExample(@Param("record") TbMessageList record, @Param("example") TbMessageListExample example);

	int updateByPrimaryKeySelective(TbMessageList record);

	int updateByPrimaryKey(TbMessageList record);
}