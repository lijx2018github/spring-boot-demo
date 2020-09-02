package com.lijx.demo.login.service;

import com.lijx.demo.system.bean.Route;
import com.lijx.demo.user.bean.User;

import java.util.List;

public interface ILoginService {
    public List<Route> getRouters(User user);
}
