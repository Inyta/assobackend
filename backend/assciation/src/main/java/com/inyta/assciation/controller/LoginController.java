package com.inyta.assciation.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.inyta.assciation.common.Jwt.JwtUtils;
import com.inyta.assciation.entity.dto.RegisterDTO;
import com.inyta.assciation.entity.dto.UserLoginDTO;
import com.inyta.assciation.entity.model.Result;
import com.inyta.assciation.entity.po.User;
import com.inyta.assciation.service.UserService;
import java.util.Date;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
  public Result<JSONObject> login(@RequestBody UserLoginDTO userLoginDTO) {
    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
        userLoginDTO.getUserId().toString(), userLoginDTO.getPassword());
    try {
      SecurityUtils.getSubject().login(usernamePasswordToken);
      User user = (User) SecurityUtils.getSubject().getPrincipal();
      String jwt = jwtUtils.generateToken(userLoginDTO);
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("token", jwt);
      jsonObject.put("userName", user.getUserName());
      return Result.success(jsonObject);
    } catch (UnknownAccountException | IncorrectCredentialsException e) {
      return Result.failed("用户名或密码错误，请重试");
    }
  }

  @PostMapping("/register")
  public Result<Void> register(@RequestBody RegisterDTO registerDTO) {
    ByteSource salt = ByteSource.Util.bytes(registerDTO.getUserId().toString());
    SimpleHash hash = new SimpleHash(Md5Hash.ALGORITHM_NAME, registerDTO.getPassword(), salt, 5);
    registerDTO.setPassword(hash.toString());
    User user = new User();
    user.setCreateTime(new Date());
    user.setStatus(0);
    user.setRole(2);
    BeanUtil.copyProperties(registerDTO, user);
    userService.save(user);
    return Result.success();
  }
}
