package com.lijx.demo.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lijx.demo.common.enums.ResultEnum;
import com.lijx.demo.common.result.Result;
import com.lijx.demo.login.bean.Login;
import com.lijx.demo.system.util.CryptUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.net.www.http.HttpClient;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @PostMapping("/login")
    public Result login(@RequestBody Login login) {
        Subject subject = SecurityUtils.getSubject();
        String username = login.getUsername();
        String password = login.getPassword();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            return new Result(ResultEnum.SERVICE_ERR, e.getMessage());
        } catch (UnknownAccountException e) {
            return new Result(ResultEnum.SERVICE_ERR, e.getMessage());
        }

        return new Result();
    }
}
