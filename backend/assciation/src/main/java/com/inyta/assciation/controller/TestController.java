package com.inyta.assciation.controller;

import com.inyta.assciation.entity.model.Result;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: zhangwei
 * @Date: 2020/8/7 17:22
 */
@RestController
public class TestController {
    @GetMapping("/test1")
    public Result<Long> test1() {
        Long userId = Long.valueOf(SecurityUtils.getSubject().getPrincipal().toString());
        return Result.success(userId);
    }
}
