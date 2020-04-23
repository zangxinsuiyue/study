package com.lhz.blog.blog.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * 数据库自增id
     * */
    private Integer id;
    /**
     * 使用UUID生成的
     * */
    private String accountId;

    private String name;

    private String token;

    /**
     * 创建事件
     */
    private Long gmtCreate;

    private Long gmtModified;

    private static final long serialVersionUID = 1L;
}