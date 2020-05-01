package com.lhz.blog.blog.controller;
import com.lhz.blog.blog.mapper.QuestionMapper;
import com.lhz.blog.blog.pojo.Question;
import com.lhz.blog.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Administrator
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    private User user;


    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("publish")
    public String postQuestion(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model){
        if(Objects.equals(title,"")){
            request.getSession().setAttribute("title-error","请填写标题");
        }if (Objects.equals(content,"")){
            request.getSession().setAttribute("content-error","请填写内容");
        }if (Objects.equals(tag,"")){
            request.getSession().setAttribute("tag-error","请填写标签");
        }
        if(Objects.equals(title,"") || Objects.equals(content,"") || Objects.equals(tag,"")){
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error","用户未登录请先登录");
            return "publish";
        }
        question.setCreator(user.getId());
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        int result = questionMapper.insertSelective(question);
        if(result > 0){
            return "redirect:/";
        }else {
            model.addAttribute("error","提交失败");
            return "publish";
        }
    }
}
