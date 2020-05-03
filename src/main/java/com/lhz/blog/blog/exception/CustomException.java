package com.lhz.blog.blog.exception;

/**
 * @author Administrator
 */
public class CustomException extends RuntimeException{
    private final String message;

    public CustomException(CustomErrorMessage customErrorCode){
        message = customErrorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
