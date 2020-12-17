package com.inyta.assciation.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inyta.assciation.entity.dto.ResetPasswordDTO;
import com.inyta.assciation.entity.dto.UserInfoDTO;
import com.inyta.assciation.entity.model.Result;
import com.inyta.assciation.entity.po.User;
import com.inyta.assciation.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: zhangwei
 * @Date: 2020/8/12 9:42
 */
@Service
@Slf4j
public class UserService extends ServiceImpl<UserMapper, User> {

    @Value("${association.file.upload.path}")
    private String prifix;

    @Value("${association.file.read.path}")
    private String readPath;

    @Value("${association.application.host}")
    private String applicationHost;

    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        String userId = SecurityUtils.getSubject().getPrincipal().toString();
        ByteSource salt = ByteSource.Util.bytes(userId);
        SimpleHash hash = new SimpleHash(Md5Hash.ALGORITHM_NAME, resetPasswordDTO.getExPassword(), salt, 5);
        SimpleHash hashcode = new SimpleHash(Md5Hash.ALGORITHM_NAME, resetPasswordDTO.getPassword(), salt, 5);
        User user = this.getById(userId);
        if (!user.getPassword().equals(hash.toString())) {
            Result.failed("原密码不正确");
            return;
        }
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("password", hashcode.toString());
        userUpdateWrapper.eq("user_id", userId);
        this.update(userUpdateWrapper);
    }

    public void editPersonalInfo(UserInfoDTO userInfoDTO) throws IOException {
        //目录大概长这样/home/static/res/association/
        //定义目录，不存在就创建
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("avatar_url", userInfoDTO.getAvatarUrl());
        updateWrapper.eq("user_id", SecurityUtils.getSubject().getPrincipal());
        updateWrapper.set("user_name", userInfoDTO.getUserName());
        updateWrapper.set("phone", userInfoDTO.getPhone());
        updateWrapper.set("gender", userInfoDTO.getGender());
        updateWrapper.set("update_time", new Date());
        this.update(updateWrapper);
    }

    public String uploadAvatar(MultipartFile file) throws IOException {
        //目录大概长这样/home/static/res/association/
        //定义目录，不存在就创建
        String filePath = prifix;
//        String filePath = "D:/xxxx1";
        File dest = new File(filePath);
        if (!dest.isDirectory()) {
            dest.mkdirs();
        }
        //生成文件名
        String fileName = UUID.randomUUID().toString() + FilenameUtils.EXTENSION_SEPARATOR
                + FilenameUtils.getExtension(file.getOriginalFilename());
        File dist = new File(filePath + File.separator + fileName);
        file.transferTo(dist);
        log.info("上传成功 fileName= " + fileName);
        log.info(" orginalFileName= " + file.getOriginalFilename());
        return fileName;
    }

    public User queryPersonalInfo(String userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        User user = this.getOne(queryWrapper);
        String filePath = prifix;
        if (ObjectUtil.isNotNull(user.getAvatarUrl())) {
            user.setAvatarUrl(filePath + File.separator + user.getAvatarUrl());
        }
        return user;
    }
}
