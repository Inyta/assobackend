package com.inyta.assciation.entity.dto;

import com.inyta.assciation.entity.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: zhangwei
 * @Date: 2020/8/18 15:45
 */
@Getter
@Setter
@ToString
public class RegisterDTO {
    private Long userId;

    private String userName;

    private String password;

    private Gender gender;
}
