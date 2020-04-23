package com.lhz.blog.blog.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String accountId;

    private String name;

    private String token;

    /**
     * 创建事件
     */
    private Long gmtCreate;

    private Long gmtModified;

    private String bio;

    private static final long serialVersionUID = 1L;
}