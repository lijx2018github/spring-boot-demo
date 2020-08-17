package com.lijx.demo.user.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private Integer id;
    private String username;
    private String password;
    private String perms;
    private String role;
}
