package com.lijx.demo.system.config;

import com.lijx.demo.system.shiro.filter.CustFormAuthenticationFilter;
import com.lijx.demo.system.shiro.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 注入 自定义  UserRealm  否则会影响 MyShiroRealm类 中其他类的依赖注入
     *
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm realm = new UserRealm();
        return realm;
    }

    /**
     * 过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean

    public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        // URL过滤链
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        // 注册自定义过滤器
        Map<String, Filter> formAuthFilter = new LinkedHashMap<>();
        formAuthFilter.put("authc", new CustFormAuthenticationFilter());
        factoryBean.setFilters(formAuthFilter);

        return factoryBean;
    }

    /**
     * 注入 SecurityManager
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.userRealm());
        return securityManager;
    }
}
