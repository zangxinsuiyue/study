package com.lhz.blog.blog.utils;

import com.lhz.blog.blog.mapper.UserMapper;
import com.lhz.blog.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Objects;

/**
 * @author Administrator
 */
@Component
public class GetUserUtil {
    @Autowired
    private UserMapper userMapper;
    private User user = null;
    public  User getUserByCookies(Cookie[] cookies){
        for (Cookie cookie : cookies) {
            if(Objects.equals(cookie.getName(),"token")){
                user = userMapper.selectByToken(cookie.getValue());
            }
        }
        return user;
    }
}
