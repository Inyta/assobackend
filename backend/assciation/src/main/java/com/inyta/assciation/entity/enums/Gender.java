package com.inyta.assciation.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @Author: zhangwei
 * @Date: 2020/8/18 15:34
 */
public enum Gender {
    //男性
    MALE(0),
    //女性
    FEMALE(1);


    @EnumValue
    private final Integer code;

    Gender(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
