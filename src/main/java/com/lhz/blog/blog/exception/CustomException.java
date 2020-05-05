package com.lhz.blog.blog.exception;

import com.lhz.blog.blog.message.CustomErrorMessage;

/**
 * 这个是自定义的异常类，用到的时候都会throw它
 * @author Administrator
 */
public class CustomException extends RuntimeException{
    private final String message;
    private final Integer code;
    public CustomException(CustomErrorMessage customErrorCode){
        message = customErrorCode.getMessage();
        code = customErrorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode(){return code;}
}
