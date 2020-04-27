package com.lhz.blog.blog.service;

import com.lhz.blog.blog.dto.QuestionDTO;
import com.lhz.blog.blog.mapper.QuestionMapper;
import com.lhz.blog.blog.mapper.UserMapper;
import com.lhz.blog.blog.pojo.Question;
import com.lhz.blog.blog.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}