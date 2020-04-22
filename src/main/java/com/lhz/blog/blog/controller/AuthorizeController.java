package com.lhz.blog.blog.controller;

import com.lhz.blog.blog.dto.AccessTokenDTO;
import com.lhz.blog.blog.dto.GithubUser;
import com.lhz.blog.blog.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 具体可以看https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
 * @author Administrator
 */
@Controller
public class AuthorizeController {
    @Value("${github.client.id}")
    private String clientID;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    /**是用来接受最后那个令牌的*/
    private String access_token;
    @Autowired
    GithubProvider githubProvider;
    @GetMapping("/callback")
    /**
     * 此类是在第一次向github发送请求后，接受返回的授权码的
     * @param code 授权码,
     * @param state 在github认证后返回页面时会将之前前端发过去的state参数一并返回
     *
     * */
    public String callback(@RequestParam(name = "code")String code,@RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO(clientID,clientSecret,code, redirectUri,state);
        //这个方法可以获得令牌，有了令牌后就可以真正的向github请求用户信息了
        access_token = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(access_token);
        return "index";
    }

}
