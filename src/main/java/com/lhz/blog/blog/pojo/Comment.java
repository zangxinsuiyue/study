package com.lhz.blog.blog.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * comment
 * @author 
 */
@Data
public class Comment implements Serializable {
    /**
     * 评论的自己的id
     */
    private Long id;

    /**
     * 被回复评论的id
     */
    private Long parentId;

    /**
     * 判断自身是几级评论
     */
    private Integer type;

    /**
     * 评论人的id
     */
    private Integer commentator;

    /**
     * 评论时间
     */
    private Long gmtCreate;

    /**
     * 修改时间
     */
    private Long gmtModified;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 评论内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}