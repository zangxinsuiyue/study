package com.lhz.blog.blog.mapper;

import com.lhz.blog.blog.dto.QuestionDTO;
import com.lhz.blog.blog.pojo.Question;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> selectAll();

    int getTotalCount();

    int getOwnTotalCount(Integer creator);

    List<QuestionDTO> getCurrentPageQuestions(Integer index,Integer size);

    List<QuestionDTO> getCurrentUserQuestions(Integer creator,Integer index,Integer size);
}