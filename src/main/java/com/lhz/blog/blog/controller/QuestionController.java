package com.lhz.blog.blog.controller;

import com.lhz.blog.blog.dto.QuestionDTO;
import com.lhz.blog.blog.mapper.UserMapper;
import com.lhz.blog.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Administrator
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id, Model model){
        QuestionDTO question = questionService.getQuestionById(id);
        questionService.updateViewCount(question.getId());
        question.setUser(userMapper.selectByPrimaryKey(question.getCreator()));
        model.addAttribute("question",question);
        return "question";
    }
}
