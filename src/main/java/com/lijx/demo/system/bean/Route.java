package com.lijx.demo.system.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Route {
    String path;
    String name;
    String icon;
    List<Route> children;
}
