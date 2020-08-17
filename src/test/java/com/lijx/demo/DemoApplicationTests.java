package com.lijx.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lijx.demo.user.bean.User;
import com.lijx.demo.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", "lijx");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

}
