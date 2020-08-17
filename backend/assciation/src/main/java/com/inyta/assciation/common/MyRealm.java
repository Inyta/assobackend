package com.inyta.assciation.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.inyta.assciation.entity.po.User;
import com.inyta.assciation.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: zhangwei
 * @Date: 2020/8/10 13:42
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", authenticationToken.getPrincipal());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UnknownAccountException();
        }
        //用户ID作为盐
        ByteSource credentialsSalt = ByteSource.Util.bytes(authenticationToken.getPrincipal());
        //返回数据库取出来的加密密码与经过加密后的登录密码进行对比
        return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), user.getPassword(), credentialsSalt, getName());
    }
}
