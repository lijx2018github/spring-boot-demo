package com.lijx.demo.meun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lijx.demo.meun.bean.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {


    public List<Menu> selectMenuList(Menu menu);
}
