package com.lhz.blog.blog.controller;

import com.lhz.blog.blog.dto.PageDTO;
import com.lhz.blog.blog.pojo.User;
import com.lhz.blog.blog.service.PageDtoService;
import com.lhz.blog.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 具体可以看https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
 * @author Administrator
 */
@Controller
public class DemoController {
    private User user;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private PageDtoService pageDtoService;
    @RequestMapping("/")
    public String hello(HttpServletRequest request,Model model,
                        @RequestParam(value = "currentPage",defaultValue = "1") String currentPage,
                        @RequestParam(value = "pageSize",defaultValue = "5") String pageSize) {
        //访问主页的时候后台会得到cookie，再获取cookie里的token值，之后向数据库中查找token值，有则获取user信息
        //有了user信息再将其传入session中
        PageDTO pageDTO = pageDtoService.getPage(currentPage,pageSize);
        model.addAttribute("page",pageDTO);
        return "index";
    }
}
