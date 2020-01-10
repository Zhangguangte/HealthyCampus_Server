package com.muyou.mapper;

import com.muyou.pojo.TbFeedback;
import com.muyou.pojo.TbFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbFeedbackMapper {
    int countByExample(TbFeedbackExample example);

    int deleteByExample(TbFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbFeedback record);

    int insertSelective(TbFeedback record);

    List<TbFeedback> selectByExample(TbFeedbackExample example);

    TbFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbFeedback record, @Param("example") TbFeedbackExample example);

    int updateByExample(@Param("record") TbFeedback record, @Param("example") TbFeedbackExample example);

    int updateByPrimaryKeySelective(TbFeedback record);

    int updateByPrimaryKey(TbFeedback record);
}