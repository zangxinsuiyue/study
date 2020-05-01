package com.lhz.blog.blog.controller;

import com.lhz.blog.blog.dto.PageDTO;
import com.lhz.blog.blog.pojo.User;
import com.lhz.blog.blog.service.PageDtoService;
import com.lhz.blog.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Administrator
 */
@Controller
public class DynamicController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private PageDtoService pageDtoService;
    @GetMapping("dynamic/{action}")
    public String dynamic(@PathVariable("action") String action , Model model, HttpServletRequest request,
    @RequestParam(value = "currentPage",defaultValue = "1") String currentPage,
                          @RequestParam(value = "pageSize",defaultValue = "5") String pageSize){
        if(Objects.equals(action,"questions")){
            model.addAttribute("profile","questions");
            model.addAttribute("tag","我的问题");
        }else if (Objects.equals(action,"replies")){
            model.addAttribute("profile","replies");
            model.addAttribute("tag","最新回复");
        }
        //访问主页的时候后台会得到cookie，再获取cookie里的token值，之后向数据库中查找token值，有则获取user信息
        //有了user信息再将其传入session中
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }
        PageDTO pageDTO = pageDtoService.getOwnPage(user.getId(),currentPage,pageSize);
        model.addAttribute("page",pageDTO);
        return "Personal-profile";
    }
}
