package com.lhz.blog.blog.dto;

import com.lhz.blog.blog.pojo.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;

    private String title;

    private String content;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer creator;

    private Integer commentCount;

    private Integer viewCount;

    private Integer likeCount;

    private String tag;

    private User user;
}
