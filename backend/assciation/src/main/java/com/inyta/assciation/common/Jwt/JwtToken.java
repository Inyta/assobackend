package com.inyta.assciation.common.Jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: zhangwei
 * @Date: 2020/8/14 14:27
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
