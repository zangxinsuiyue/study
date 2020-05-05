package com.lhz.blog.blog.pojo;

import java.io.Serializable;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 数据库自增id
     * */
    private Long id;

    /**
     * 是从github用户信息得到的，只要是同一个github用户
     * 就是一个accountId,所以可以用来查看用户是否存在
     * */
    private String accountId;

    private String name;
    /**
     * UUID生成的
     * */
    private String token;

    /**
     * 创建事件
     */
    private Long gmtCreate;

    private Long gmtModified;

    private String bio;

    private String headShotUrl;
    private static final long serialVersionUID = 1L;
}