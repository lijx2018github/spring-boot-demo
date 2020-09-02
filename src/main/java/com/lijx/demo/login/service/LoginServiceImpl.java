package com.lijx.demo.login.service;

import com.lijx.demo.common.utils.RoutesUtils;
import com.lijx.demo.meun.bean.Menu;
import com.lijx.demo.meun.service.IMenuService;
import com.lijx.demo.system.bean.Route;
import com.lijx.demo.user.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loginService")
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private IMenuService menuService;

    @Override
    public List<Route> getRouters(User user) {
        // 根据 username 查询菜单
        List<Menu> menus = menuService.selectMenuList(new Menu());


        List<Route> routes = RoutesUtils.menus2Routes(menus);

        return routes;
    }
}
