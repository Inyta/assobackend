package com.inyta.assciation.common.Realm;

import com.inyta.assciation.common.Jwt.JwtToken;
import com.inyta.assciation.common.Jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: zhangwei
 * @Date: 2020/8/17 19:59
 */
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        Claims claimByToken = jwtUtils.getClaimByToken(jwtToken.getPrincipal().toString());
        String subject = claimByToken.getSubject();
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(subject, jwtToken.getCredentials(), getName());
        return simpleAuthenticationInfo;
    }
}
