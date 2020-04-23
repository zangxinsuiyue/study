package com.lhz.blog.blog.controller;

import com.lhz.blog.blog.dto.AccessTokenDTO;
import com.lhz.blog.blog.dto.GithubUser;
import com.lhz.blog.blog.mapper.UserMapper;
import com.lhz.blog.blog.pojo.User;
import com.lhz.blog.blog.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


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
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    /**
     * 此类是在第一次向github发送请求后，接受返回的授权码，并将授权码再发送给github来接收令牌
     * 再将令牌发送给GitHub来获取用户信息的
     * 里面调用了getAccessToken()方法来获取令牌
     * 再用getUser(令牌)的方法获取用户信息
     * @param code 授权码,
     * @param state 在github认证后返回页面时会将之前前端发过去的state参数一并返回
     *
     * */
    public String callback(@RequestParam(name = "code")String code, @RequestParam(name = "state")String state, HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO(clientID,clientSecret,code, redirectUri,state);
        //这个方法可以获得令牌，有了令牌后就可以真正的向github请求用户信息了
        access_token = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(access_token);
        //如果得到从github那里得到用户数据，则将其传入数据库
        if (githubUser != null){
            User user = new User();
            user.setName(githubUser.getLogin());
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            request.getSession().setAttribute("user",githubUser);
        }
        return "redirect:/";
    }

}
