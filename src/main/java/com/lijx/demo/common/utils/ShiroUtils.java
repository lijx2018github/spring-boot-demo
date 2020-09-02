package com.lijx.demo.common.utils;

import com.lijx.demo.user.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Object getPrincipal() {
        return getSubject().getPrincipal();
    }

    public static User getUser() {
        User user = null;
        Object o = getPrincipal();
        if (null != o) {
            user = new User();
            BeanUtils.copyBeanProp(user, o);
        }
        return user;
    }
}
