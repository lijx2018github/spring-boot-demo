package com.lijx.demo.common.utils;

import com.lijx.demo.meun.bean.Menu;

import java.util.LinkedList;
import java.util.List;

public class MenuUtils {

    /**
     * @param menus
     * @return
     */
    public static List<Menu> getParseChildren(List<Menu> menus) {
        long initParentId = 0L;
        List<Menu> fList = new LinkedList<>();
        // 循环菜单list
        for (Menu menu : menus) {
            Menu newMenu = menu;
            // 拿外层菜单
            if (initParentId == menu.getParentId()) {

            }
        }

        return null;
    }

    /**
     * 递归获取子菜单
     *
     * @param menuTarget
     * @param parentMenu
     */
    public static void recursionParseChlidren(List<Menu> menuTarget, Menu parentMenu) {


    }
}
