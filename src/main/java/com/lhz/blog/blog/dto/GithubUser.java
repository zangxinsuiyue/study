package com.lhz.blog.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这个类是封装了用令牌获取的用户的信息
 * @author Administrator
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubUser {
    private Long id;
    private String name;
    private String bio;
    private String login;
}
