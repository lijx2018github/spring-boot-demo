package com.lijx.demo.meun.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Menu {
    Long id;
    String menuCode;
    String menuName;
    String menuLevel;
    long parentId;
    String icon;
    String path;
    String component;
    String isIndex;
    String remark;
    List<Menu> children;
}
