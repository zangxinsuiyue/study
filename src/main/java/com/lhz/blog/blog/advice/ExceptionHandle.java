package com.lhz.blog.blog.advice;

import com.alibaba.fastjson.JSON;
import com.lhz.blog.blog.dto.ResultDTO;
import com.lhz.blog.blog.exception.CustomException;
import com.lhz.blog.blog.message.impl.ServerErrorMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @author Administrator
 */
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(Exception.class)
    public ModelAndView errorHandler(Exception e, Model model, HttpServletRequest request, HttpServletResponse response){
        if(Objects.equals(request.getContentType(),"application/json")){
            ResultDTO resultDTO;
            if(e instanceof CustomException){
                resultDTO = ResultDTO.errorOf((CustomException) e);
            }else {
                resultDTO = ResultDTO.errorOf(ServerErrorMessage.SYSTEM_ERROR);
            }
            try {
                response.setContentType("application/json; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(resultDTO));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }else{
            if(e instanceof CustomException){
                e.printStackTrace();
                model.addAttribute("message",e.getMessage());
            }else {
                model.addAttribute("message","未知错误");
            }
            return new ModelAndView("error");
        }
    }


}
