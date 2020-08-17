package com.inyta.assciation.config;

import com.inyta.assciation.common.JwtFilter;
import com.inyta.assciation.common.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: zhangwei
 * @Date: 2020/8/11 21:51
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * 配置自定义的Realm（核心）
     */
    @Bean
    public MyRealm myRealm() {
        MyRealm realm = new MyRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
        shiroFilterFactory.setSecurityManager(securityManager);
        Map<String, Filter> filter = new HashMap<>();
        filter.put("jwt", jwtFilter);
        shiroFilterFactory.setFilters(filter);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/**", "jwt");
        shiroFilterFactory.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactory;
    }

    @Bean
    public SessionsSecurityManager securityManager(MyRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm);
        return securityManager;
    }

    /**
     * 设置用于匹配密码的CredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        hashedCredentialsMatcher.setHashIterations(5);
        return hashedCredentialsMatcher;
    }

    /**
     * 开启注解代理（默认好像已经开启，可以不要）
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
