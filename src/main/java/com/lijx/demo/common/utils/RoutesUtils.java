package com.lijx.demo.common.utils;

import com.lijx.demo.meun.bean.Menu;
import com.lijx.demo.system.bean.Route;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RoutesUtils {

    /**
     * menu 数据转 routers
     *
     * @param menus
     * @return
     */
    public static List<Route> menus2Routes(List<Menu> menus) {

        parseMenuChildren(menus, 0);
        return null;
    }

    /**
     * 转换菜单处理children节点
     *
     * @param menus
     * @return
     */
    public static List<Menu> parseMenuChildren(List<Menu> menus, int headParentId) {

        List<Menu> parentMenu = new LinkedList<>();
        List<Route> parentRoute = new LinkedList<>();

        for (Menu menu : menus) {

            // 0为一级菜单
            if (headParentId == menu.getParentId()) {
                parentMenu.add(menu);
            }
        }

        return null;
    }
}
