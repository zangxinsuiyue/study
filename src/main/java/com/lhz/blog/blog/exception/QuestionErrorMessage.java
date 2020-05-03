package com.lhz.blog.blog.exception;

/**
 * 此类实现自CustomErrorMessage接口，是用来存储当获取question时出现问题时的错误提示信息
 * @author Administrator
 */
public enum QuestionErrorMessage implements CustomErrorMessage {
    /**
     * 后台查询不到数据库中具体的question时的提示
     * 举例：
     *      QuestionErrorMessage.QUESTION_NOT_FOUNT 与 new QuestionErrorMessage("问题未找到")等价
     * */
    QUESTION_NOT_FOUNT("问题未找到");

    private final String message;

    QuestionErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
