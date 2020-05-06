package com.lhz.blog.blog.message.impl;

import com.lhz.blog.blog.message.CustomErrorMessage;

/**
 * @author Administrator
 */
public enum  ServerErrorMessage implements CustomErrorMessage {
    /**
     * 当发生其他错误时，显示此信息
     * */
    SYSTEM_ERROR(2005,"系统出错了");

    private final Integer code;
    private final String message;

    ServerErrorMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
