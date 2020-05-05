package com.lhz.blog.blog.dto;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class CommentDTO {
    /**
     * 被回复评论的id
     */
    private Long parentId;

    /**
     * 判断自身是几级评论
     */
    private Integer type;
    /**
     * 评论内容
     * */
    private String content;
}
