package com.muyou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muyou.pojo.TbTimeAttend;
import com.muyou.pojo.TbTimeAttendExample;
import com.muyou.vo.AttendDateVo;
import com.muyou.vo.AttendListVo;
import com.muyou.vo.AttendVo;

public interface TbTimeAttendMapper {
    int countByExample(TbTimeAttendExample example);

    int deleteByExample(TbTimeAttendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbTimeAttend record);

    int insertSelective(TbTimeAttend record);

    List<TbTimeAttend> selectByExample(TbTimeAttendExample example);

    TbTimeAttend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbTimeAttend record, @Param("example") TbTimeAttendExample example);

    int updateByExample(@Param("record") TbTimeAttend record, @Param("example") TbTimeAttendExample example);

    int updateByPrimaryKeySelective(TbTimeAttend record);

    int updateByPrimaryKey(TbTimeAttend record);
    
    List<AttendVo> selectAttendListByTid(@Param("id") Integer id);
    
    List<AttendDateVo> selectDateListByTid(@Param("id") Integer id);
    
    List<AttendListVo> attendListByDate(@Param("date") String date,@Param("tid") Integer tid);
    
    List<String> getAttendIds(@Param("date") String date,@Param("tid") Integer tid);
    
    List<String> getAttendCls(@Param("tid") Integer tid,@Param("type") Integer type);
    
    List<AttendListVo> attendAbsenceByTid(@Param("ids") String ids,@Param("cls") String cls);
}