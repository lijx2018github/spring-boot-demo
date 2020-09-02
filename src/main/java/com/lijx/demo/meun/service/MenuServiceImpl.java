package com.lijx.demo.meun.service;

import com.lijx.demo.common.utils.MenuUtils;
import com.lijx.demo.meun.bean.Menu;
import com.lijx.demo.meun.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenuList(Menu menu) {
        // 查询全部菜单
        List<Menu> allMenu = menuMapper.selectMenuList(menu);
        // 处理下级菜单到children
        MenuUtils.getParseChildren(allMenu);

        return null;
    }
}
