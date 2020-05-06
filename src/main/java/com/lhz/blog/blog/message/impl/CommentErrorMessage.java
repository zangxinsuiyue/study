package com.lhz.blog.blog.message.impl;

import com.lhz.blog.blog.message.CustomErrorMessage;

/**
 * @author Administrator
 */

public enum CommentErrorMessage implements CustomErrorMessage {
    /**
     *
     * */
    TARGET_NOT_FOUNT(2002,"未选择问题"),
    NO_LOGIN(2003,"用户未登录"),
    REPLY_COMMENT_DOES_NOT_EXIST(2004,"您所回复的评论不存在"),
    REPLY_QUESTION_DOES_NOT_EXIST(2006,"您所回复的问题不存在"),
    REPLY_OBJECT_DOES_NOT_EXIST(2007,"您所回复的目标不存在");

    private final Integer code;
    private final String message;
    CommentErrorMessage(Integer code, String message) {
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
