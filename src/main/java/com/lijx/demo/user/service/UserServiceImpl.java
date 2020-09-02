package com.lijx.demo.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lijx.demo.user.bean.User;
import com.lijx.demo.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("user_status", "1");
        return userMapper.selectOne(queryWrapper);
    }

}
