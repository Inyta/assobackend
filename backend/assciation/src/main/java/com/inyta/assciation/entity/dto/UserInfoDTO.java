package com.inyta.assciation.entity.dto;

import com.inyta.assciation.entity.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: zhangwei
 * @Date: 2020/8/18 16:07
 */
@Getter
@Setter
@ToString
public class UserInfoDTO {

    private String userName;

    private String phone;

    private Gender gender;

}
