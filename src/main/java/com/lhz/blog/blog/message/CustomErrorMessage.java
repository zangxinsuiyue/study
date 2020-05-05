package com.lhz.blog.blog.message;

/**
 * 此接口是用来返回错误信息
 * 具体返回哪种错误信息由具体实现类来决定
 * 此类的实现类必须时枚举类
 * @author Administrator
 */
public interface CustomErrorMessage {
    /**
     * 用来获取具体的错误信息
     * @return 返回错误信息
     * */
    String getMessage();
    /**
     * 用来获取错误码
     * @return 返回错误码
     * */
    Integer getCode();
}
