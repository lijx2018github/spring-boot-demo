package com.lijx.demo.login.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lijx.demo.common.enums.ResultEnum;
import com.lijx.demo.common.result.Result;
import com.lijx.demo.common.utils.ShiroUtils;
import com.lijx.demo.login.bean.Login;
import com.lijx.demo.login.service.ILoginService;
import com.lijx.demo.meun.service.IMenuService;
import com.lijx.demo.system.bean.Route;
import com.lijx.demo.user.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private ILoginService loginService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Login login) {
        Subject subject = SecurityUtils.getSubject();
        String username = login.getUsername();
        String password = login.getPassword();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            return new Result(ResultEnum.AUTH_ERR, e.getMessage());
        } catch (UnknownAccountException e) {
            return new Result(ResultEnum.AUTH_ERR, e.getMessage());
        }

        return new Result();
    }


    @RequestMapping(value = "/get/routers", method = RequestMethod.POST)
    public Result getRouters() {
        User u = ShiroUtils.getUser();
//        List<Route> routes = loginService.getRouters(u);
        // 先搞个假的



        JSONArray routes = new JSONArray();
        JSONObject route = new JSONObject();
        JSONArray chil = new JSONArray();
        route.put("path", "/dashboard");
        route.put("name", "dashboard");
        route.put("icon", "dashboard");

        JSONObject chil1 = new JSONObject();
        chil1.put("path", "/dashboard/analysis");
        chil1.put("name", "analysis");
        JSONObject chil2 = new JSONObject();
        chil2.put("path", "/dashboard/monitor");
        chil2.put("name", "monitor");
        JSONObject chil3 = new JSONObject();
        chil3.put("path", "/dashboard/workplace");
        chil3.put("name", "workplace");
        chil.add(chil1);
        chil.add(chil2);
        chil.add(chil3);
        route.put("children", chil);
        routes.add(route);
        return new Result(routes);
    }
}
