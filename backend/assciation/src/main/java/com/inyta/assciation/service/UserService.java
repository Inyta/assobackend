package com.inyta.assciation.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inyta.assciation.entity.po.User;
import com.inyta.assciation.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: zhangwei
 * @Date: 2020/8/12 9:42
 */
@Service
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService extends ServiceImpl<UserMapper, User> {
}
