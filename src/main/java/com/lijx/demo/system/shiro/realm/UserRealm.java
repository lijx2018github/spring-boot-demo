package com.lijx.demo.system.shiro.realm;

import com.lijx.demo.common.exception.user.UserNotExistsException;
import com.lijx.demo.user.bean.User;
import com.lijx.demo.user.mapper.UserMapper;
import com.lijx.demo.user.service.IUserService;
import lombok.SneakyThrows;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 * 自定义realm
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 登陆信息获取username
        String username = token.getUsername();
        User user = userService.findUser(username);
        // 用户未找到
        if (null == user) {
            throw new UnknownAccountException("用户名或密码错误");
        }
        // 密码校验
        String password = "";
        if (token.getPassword() != null) {
            password = new String(token.getPassword());
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误");
        }
        // 放入user实体
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, getName());

        return simpleAuthenticationInfo;
    }
}
