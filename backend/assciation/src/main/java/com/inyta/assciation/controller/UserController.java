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

import java.io.IOException;

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
     * @param resetPasswordDTO dto
     * @return 空
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
     * @return 空
     */
    @GetMapping("/queryPersonalInfo")
    public Result<User> queryPersonalInfo() {
        String userId = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.queryPersonalInfo(userId);
        return Result.success(user);
    }

    /**
     * 修改个人资料
     *
     * @param userInfoDTO dto
     * @return 返回空
     */
    @PostMapping("/editPersonalInfo")
    public Result<Void> editPersonalInfo(@RequestBody UserInfoDTO userInfoDTO) throws IOException {
        userService.editPersonalInfo(userInfoDTO);
        return Result.success();
    }

    /**
     * 上传头像
     *
     * @param file 文件
     * @return 文件名
     */
    @PostMapping("/uploadAvatar")
    public Result<String> uploadAvatar(@RequestParam MultipartFile file) throws IOException {
        String fileName = userService.uploadAvatar(file);
        return Result.success(null, fileName);
    }
}
