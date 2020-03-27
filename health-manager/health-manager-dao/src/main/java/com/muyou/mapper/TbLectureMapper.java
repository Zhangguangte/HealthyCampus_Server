package com.muyou.mapper;

import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbLecture;
import com.muyou.pojo.TbLectureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbLectureMapper {
	int countByExample(TbLectureExample example);

	int deleteByExample(TbLectureExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(TbLecture record);

	int insertSelective(TbLecture record);

	List<TbLecture> selectByExample(TbLectureExample example);

	TbLecture selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") TbLecture record, @Param("example") TbLectureExample example);

	int updateByExample(@Param("record") TbLecture record, @Param("example") TbLectureExample example);

	int updateByPrimaryKeySelective(TbLecture record);

	int updateByPrimaryKey(TbLecture record);

	//前台
	List<TbLecture> selectItemByClassify(@Param("cid") int cid, @Param("page") int page, @Param("row") int row);

	// 后台
	List<TbLecture> selectItemByCondition(@Param("cid") int cid, @Param("orderCol") String orderCol,
			@Param("orderDir") String orderDir);

	List<TbLecture> selectItemByMultiCondition(@Param("cid") int cid, @Param("search") String search,
			@Param("minDate") String minDate, @Param("maxDate") String maxDate, @Param("orderCol") String orderCol,
			@Param("orderDir") String orderDir);

}