package com.lhz.blog.blog.advice;

import com.lhz.blog.blog.exception.CustomException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 */
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(Exception.class)
    public ModelAndView errorHandler(Exception e, Model model){
        if(e instanceof CustomException){
            model.addAttribute("message",e.getMessage());
        }else {
            model.addAttribute("message","未知错误");
        }
        return new ModelAndView("error");
    }


}
