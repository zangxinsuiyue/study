package com.lhz.blog.blog.controller;

import com.lhz.blog.blog.dto.QuestionDTO;
import com.lhz.blog.blog.mapper.QuestionMapper;
import com.lhz.blog.blog.mapper.UserMapper;
import com.lhz.blog.blog.pojo.Question;
import com.lhz.blog.blog.pojo.User;
import com.lhz.blog.blog.service.QuestionService;
import com.lhz.blog.blog.utils.GetUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * 具体可以看https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
 * @author Administrator
 */
@Controller
public class DemoController {
    @Autowired
    private UserMapper userMapper;
    private User user;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private GetUserUtil getUserUtil;
    @RequestMapping("/")
    public String hello(HttpServletRequest request,Model model) {
        Cookie[] cookies = request.getCookies();
        //访问主页的时候后台会得到cookie，再获取cookie里的token值，之后向数据库中查找token值，有则获取user信息
        //有了user信息再将其传入session中
        user = getUserUtil.getUserByCookies(cookies);
        if (user != null){
            request.getSession().setAttribute("user",user);
        }
        List<QuestionDTO> questions = questionService.getQuestions();
        model.addAttribute("questions",questions);
        return "index";
    }
}
