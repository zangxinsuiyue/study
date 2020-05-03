package com.lhz.blog.blog.service;

import com.lhz.blog.blog.dto.QuestionDTO;
import com.lhz.blog.blog.exception.CustomException;
import com.lhz.blog.blog.exception.QuestionErrorMessage;
import com.lhz.blog.blog.mapper.QuestionMapper;
import com.lhz.blog.blog.mapper.UserMapper;
import com.lhz.blog.blog.pojo.Question;
import com.lhz.blog.blog.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public List<QuestionDTO> getQuestions(){
        List<Question> questions = questionMapper.selectAll();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    public QuestionDTO getQuestionById(Integer id){
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomException(QuestionErrorMessage.QUESTION_NOT_FOUNT);
        }
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }

    public int insertSelective(Question question){
        return questionMapper.insertSelective(question);
    }

    public int updateByIdSelective(Question question){
        int result = questionMapper.updateByPrimaryKeySelective(question);
        if(result == 0){
            throw new CustomException(QuestionErrorMessage.QUESTION_NOT_FOUNT);
        }
        return result;
    }

}
