package com.lhz.blog.blog.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 这个类是当异常不是Java的Exception而是想要处理类似于404或者500错误时的
 * @author Administrator
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error";
    }
    //填上produces = MediaType.TEXT_HTML_VALUE时才会在发生4XX或5XX时进入此方法
    //否则默认直接进入error.html页面了
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView handleError(HttpServletRequest request,Model model){
        //获取状态码对象
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        HttpStatus status = HttpStatus.valueOf(statusCode);
        //当状态码时4xx时
        if(status.is4xxClientError()){
            model.addAttribute("message","客户端错误，请输入正确的");
        }else if (status.is5xxServerError()){
            model.addAttribute("message","服务器错误，请重试");
        }
        //去往error.html页面
        return new ModelAndView("error");
    }

}
