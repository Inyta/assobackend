package com.inyta.assciation.controller;

import com.inyta.assciation.entity.dto.ResetPasswordDTO;
import com.inyta.assciation.entity.dto.UserInfoDTO;
import com.inyta.assciation.entity.model.Result;
import com.inyta.assciation.entity.po.User;
import com.inyta.assciation.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: zhangwei
 * @Date: 2020/8/18 14:00
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 更改密码
     *
     * @param resetPasswordDTO
     * @return
     */
    @PostMapping("/resetPassword")
    public Result<Void> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        userService.resetPassword(resetPasswordDTO);
        SecurityUtils.getSubject().logout();
        return Result.success("更改成功，请重新登录");
    }

    /**
     * 查询个人资料
     *
     * @return
     */
    @GetMapping("/queryPersonalInfo")
    public Result<User> queryPersonalInfo() {
        String userId = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getById(userId);
        return Result.success(user);
    }

    /**
     * 修改个人资料
     *
     * @param userInfoDTO
     * @return
     */
    @PostMapping("/editPersonalInfo")
    public Result<Void> editPersonalInfo(@RequestParam MultipartFile file,UserInfoDTO userInfoDTO) {
        return null;
    }
}
