package com.lhz.blog.blog.mapper;

import com.lhz.blog.blog.dto.QuestionDTO;
import com.lhz.blog.blog.pojo.Question;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> selectAll();

    int getTotalCount();

    int getOwnTotalCount(Long creator);

    List<QuestionDTO> getCurrentPageQuestions(Integer index,Integer size);

    List<QuestionDTO> getCurrentUserQuestions(Long creator, Integer index, Integer size);

    int updateViewCount(Long id);

    int updateCommentCount(Question record);
}