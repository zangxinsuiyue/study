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
