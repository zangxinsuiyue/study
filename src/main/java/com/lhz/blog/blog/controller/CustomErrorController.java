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
 * @author Administrator
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error";
    }
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView handleError(HttpServletRequest request,Model model){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        HttpStatus status = HttpStatus.valueOf(statusCode);
        if(status.is4xxClientError()){
            model.addAttribute("message","请求错误，请输入正确的");
        }else if (status.is5xxServerError()){
            model.addAttribute("message","服务器错误，请重试");
        }
        return new ModelAndView("error");
    }

}
