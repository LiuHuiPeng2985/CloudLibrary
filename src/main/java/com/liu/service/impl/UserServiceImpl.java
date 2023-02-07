package com.liu.service.impl;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 11:30
 */

import com.liu.domain.User;
import com.liu.mapper.UserMapper;
import com.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }
}
