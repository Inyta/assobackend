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
    public Result<Void> test1() {
        String s = SecurityUtils.getSubject().getPrincipal().toString();
        return Result.success(s);
    }
}
