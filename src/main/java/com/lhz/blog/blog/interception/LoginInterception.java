package com.lhz.blog.blog.interception;

import com.lhz.blog.blog.mapper.UserMapper;
import com.lhz.blog.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 这是个登录验证的拦截器，拦截所有请求，他会比controller先执行
 * 思路是每过一个请求都会查找浏览器的cookie
 * 查看其中是否由token，若没有则返回首页，并提示登录
 * 若有，则用此token去向用户表中查看是否还有此用户
 * 同理
 * @author Administrator
 */
@Component
public class LoginInterception implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;
    private User user;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
            if(cookies == null){
                return true;
            }
            for (Cookie cookie : cookies) {
                if (Objects.equals(cookie.getName(), "token")) {
                    user = userMapper.selectByToken(cookie.getValue());
                    request.getSession().setAttribute("user",user);
                }
            }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
