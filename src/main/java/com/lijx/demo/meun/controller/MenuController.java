package com.lijx.demo.meun.controller;

import com.lijx.demo.common.result.Result;
import com.lijx.demo.meun.bean.Menu;
import com.lijx.demo.meun.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @PostMapping("/getmenus")
    public Result getMenus(@RequestBody Menu menu) {

        return new Result(menuService.selectMenuList(menu));
    }

}
