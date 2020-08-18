package com.lijx.demo.meun.bean;

import lombok.Data;

@Data
public class Menu {
    Long id;
    String menuCode;
    String menuName;
    String menuLevel;
    long parentId;
    String icon;
    String path;
    String isIndex;
    String remark;
}
