package com.muyou.mapper;

import com.muyou.pojo.TbNewsUser;
import com.muyou.pojo.TbNewsUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Update;

public interface TbNewsUserMapper {
	int countByExample(TbNewsUserExample example);

	int deleteByExample(TbNewsUserExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbNewsUser record);

	int insertSelective(TbNewsUser record);

	List<TbNewsUser> selectByExample(TbNewsUserExample example);

	TbNewsUser selectByPrimaryKey(Integer id);

	// ************************************************
	// 清空通知
	void clearNotice(@Param("userId") String userId);

	// 删除通知
	void deleteNotice(@Param("newsId") String newsId, @Param("userId") String userId);

	// 查看通知
	void lookNotice(@Param("id") String id,@Param("time") String time,@Param("userId") String userId);
	
	
	// *************************************************

	int updateByExampleSelective(@Param("record") TbNewsUser record, @Param("example") TbNewsUserExample example);

	int updateByExample(@Param("record") TbNewsUser record, @Param("example") TbNewsUserExample example);

	int updateByPrimaryKeySelective(TbNewsUser record);

	int updateByPrimaryKey(TbNewsUser record);
}