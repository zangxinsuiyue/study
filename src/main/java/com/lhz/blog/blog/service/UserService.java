package com.lhz.blog.blog.service;

import com.lhz.blog.blog.mapper.UserMapper;
import com.lhz.blog.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 判断用户是否存在
     * @return true 用户存在,false 用户不存在
     * */
    public boolean isExist(String accountId){
        return userMapper.selectByAccountId(accountId) != null;
    }
    public int insert(User user){
       return userMapper.insert(user);
    }
    public int updateTokenByAccountId(User user){
        return userMapper.updateUserTokenByAccountId(user);
    }
}
