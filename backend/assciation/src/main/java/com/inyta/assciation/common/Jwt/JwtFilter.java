package com.inyta.assciation.common.Jwt;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhangwei
 * @Date: 2020/8/14 13:28
 */
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter {


//    @Override
//    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
//        // 获取 token
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String jwt = request.getHeader("Authorization");
//        return new JwtToken(jwt);
//    }

//    @Override
//    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String token = request.getHeader("Authorization");
//        if (StringUtils.isBlank(token)) {
////            throw new Exception("请求拒绝，请检查请求头");
//            return false;
//        }
//        // 执行自动登录
//        return executeLogin(servletRequest, servletResponse);
//    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            return false;
        } else {
            JwtUtils jwtUtils = new JwtUtils();
            // 判断是否已过期
            Claims claim = jwtUtils.getClaimByToken(token);
            if (claim == null || jwtUtils.isTokenExpired(claim.getExpiration())) {
                return false;
            }
            SecurityUtils.getSubject().login(new JwtToken(token));
            return true;
        }
    }
}
