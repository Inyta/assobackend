package com.inyta.assciation.config;

import com.inyta.assciation.common.Jwt.JwtFilter;
import com.inyta.assciation.common.Realm.ChooseRealm;
import com.inyta.assciation.common.Realm.JwtRealm;
import com.inyta.assciation.common.Realm.LoginRealm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhangwei
 * @Date: 2020/8/11 21:51
 */
@Configuration
public class ShiroConfig {

  /**
   * 配置登录Realm（核心）
   */
  @Bean
  public LoginRealm loginRealm() {
    LoginRealm loginRealm = new LoginRealm();
    loginRealm.setCredentialsMatcher(hashedCredentialsMatcher());
    return loginRealm;
  }

  /**
   * 配置JWTRealm（核心）
   */
  @Bean
  public JwtRealm jwtRealm() {
    return new JwtRealm();
  }

  /**
   * 针对多Realm，使用自定义身份验证器
   *
   * @return
   */
  @Bean
  public ModularRealmAuthenticator modularRealmAuthenticator() {
    ChooseRealm authenticator = new ChooseRealm();
    authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
    return authenticator;
  }


  @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
    shiroFilterFactory.setSecurityManager(securityManager);
    Map<String, Filter> filter = new HashMap<>();
    filter.put("jwt", new JwtFilter());
    shiroFilterFactory.setFilters(filter);
    Map<String, String> filterMap = new LinkedHashMap<>();
    filterMap.put("/login", "anon");
    filterMap.put("/logout", "anon");
    filterMap.put("/**", "jwt");
    shiroFilterFactory.setFilterChainDefinitionMap(filterMap);
    return shiroFilterFactory;
  }

  @Bean
  public DefaultWebSecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setAuthenticator(modularRealmAuthenticator());
    // 设置Realms
    List<Realm> realms = new ArrayList<>(2);
    realms.add(jwtRealm());
    realms.add(loginRealm());
    securityManager.setRealms(realms);
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
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      SecurityManager securityManager) {
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
