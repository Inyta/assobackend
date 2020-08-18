package com.inyta.assciation.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inyta.assciation.entity.dto.ResetPasswordDTO;
import com.inyta.assciation.entity.model.Result;
import com.inyta.assciation.entity.po.User;
import com.inyta.assciation.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * @Author: zhangwei
 * @Date: 2020/8/12 9:42
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public Result<Void> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        String userId = SecurityUtils.getSubject().getPrincipal().toString();
        ByteSource salt = ByteSource.Util.bytes(userId);
        SimpleHash hash = new SimpleHash(Md5Hash.ALGORITHM_NAME, resetPasswordDTO.getExPassword(), salt, 5);
        SimpleHash hashcode = new SimpleHash(Md5Hash.ALGORITHM_NAME, resetPasswordDTO.getPassword(), salt, 5);
        User user = this.getById(userId);
        if (!user.getPassword().equals(hash.toString())) {
            return Result.failed("原密码不正确");
        }
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("password", hashcode.toString());
        userUpdateWrapper.eq("user_id", userId);
        this.update(userUpdateWrapper);
        return Result.success();
    }
}
