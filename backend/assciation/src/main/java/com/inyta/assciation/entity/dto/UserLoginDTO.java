package com.inyta.assciation.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: zhangwei
 * @Date: 2020/8/10 22:08
 */
@Getter
@Setter
@ToString
public class UserLoginDTO {

    private Long userId;

    private String password;

    private String userName;
}
