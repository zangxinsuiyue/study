package com.lhz.blog.blog.provider;

import com.alibaba.fastjson.JSON;
import com.lhz.blog.blog.dto.AccessTokenDTO;
import com.lhz.blog.blog.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 具体可以看https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
 * @author Administrator
 */
@Component
public class GithubProvider {
    /**
     * 从github返回的内容
     * */
    private String responseContent;
    /**
     *从responseContent中获得的令牌
     * */
    private String token;
    /**
     * 目的是通过向github发送授权码，来获取令牌。
     * @return 返回令牌
     * */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
            //设置请求体中的内容
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body).build();
            try (Response response = client.newCall(request).execute()) {
                //这里返回的是一个不是单纯的令牌而是access_token=4b1722d54fd1674d7c8b110874c6db2acb092f90&scope=user&token_type=bearer
                //所以我们需要将这串字符串中的令牌部分提取并return回去
                responseContent = response.body().string();
                System.out.println(responseContent);
                token = responseContent.substring(responseContent.indexOf("=")+1,responseContent.indexOf("&"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return token;
    }

    /**
     * 根据从github传回来的令牌来获取用户信息
     * @return 仅仅是封装了用户id,name,bio三个属性
     * */
    public GithubUser getUser(String access_token){
        OkHttpClient client = new OkHttpClient();
        /*这个连接就是带着令牌访问用户信息的链接*/
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+access_token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return  JSON.parseObject(response.body().string(),GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
