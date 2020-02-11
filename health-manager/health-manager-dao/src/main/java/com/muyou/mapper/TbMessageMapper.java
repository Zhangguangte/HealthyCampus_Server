package com.muyou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muyou.common.form.ChatForm;
import com.muyou.pojo.TbMessage;
import com.muyou.pojo.TbMessageExample;

public interface TbMessageMapper {
	int countByExample(TbMessageExample example);

	int deleteByExample(TbMessageExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbMessage record);

	int insertSelective(TbMessage record);

	List<TbMessage> selectByExampleWithBLOBs(TbMessageExample example);

	List<TbMessage> selectByExample(TbMessageExample example);

	TbMessage selectByPrimaryKey(Integer id);

	// ************************************

	// 新好友
	void newfriend(@Param("time") String time, @Param("roomid") String roomid);

	// 现存最后一条消息
	List<TbMessage> lastMessage(@Param("user_id") String user_id);

	// 所有消息根据房间号
	List<TbMessage> allChatByRoomId(@Param("room_id") String room_id, @Param("start") int start);

	// 添加消息（文字、录音、图片）
	public void insertContent(@Param("chatForm") ChatForm chatForm, @Param("userId") String userId);

	// 添加消息（名片）
	public void insertCard(@Param("content") String content, @Param("chatForm") ChatForm chatForm,
			@Param("userId") String userId);

	// ************************************

	int updateByExampleSelective(@Param("record") TbMessage record, @Param("example") TbMessageExample example);

	int updateByExampleWithBLOBs(@Param("record") TbMessage record, @Param("example") TbMessageExample example);

	int updateByExample(@Param("record") TbMessage record, @Param("example") TbMessageExample example);

	int updateByPrimaryKeySelective(TbMessage record);

	int updateByPrimaryKeyWithBLOBs(TbMessage record);

	int updateByPrimaryKey(TbMessage record);
}