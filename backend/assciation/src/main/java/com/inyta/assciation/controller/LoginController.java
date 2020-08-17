package com.inyta.assciation.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.inyta.assciation.common.JwtUtils;
import com.inyta.assciation.entity.dto.UserDTO;
import com.inyta.assciation.entity.model.Result;
import com.inyta.assciation.entity.po.User;
import com.inyta.assciation.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author: zhangwei
 * @Date: 2020/8/10 20:13
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/login")
    public Result<Void> Login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userDTO.getUserId().toString(), userDTO.getPassword());
        try {
            SecurityUtils.getSubject().login(usernamePasswordToken);
            String jwt = jwtUtils.generateToken(userDTO.getUserId());
            return Result.success(jwt);
        } catch (UnknownAccountException e) {
            return Result.failed("用户名或密码错误，请重试");
        } catch (IncorrectCredentialsException e) {
            return Result.failed("用户名或密码错误，请重试");
        }
    }

    @PostMapping("/register")
    public Result<Void> register(@RequestBody UserDTO userDTO) {
        ByteSource salt = ByteSource.Util.bytes(userDTO.getUserId().toString());
        SimpleHash hash = new SimpleHash(Md5Hash.ALGORITHM_NAME, userDTO.getPassword(), salt, 5);
        userDTO.setPassword(hash.toString());
        User user = new User();
        user.setCreateTime(new Date());
        BeanUtil.copyProperties(userDTO, user);
        userService.save(user);
        return Result.success();
    }
    @GetMapping("/test1")
    public Result<Void> test1(HttpServletRequest request){
        String str = jwtUtils.getClaimByToken(request.getHeader("Authorization")).getSubject();
        System.out.println(str);
        return Result.success(str);
    }
}
