package com.inyta.assciation.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: zhangwei
 * @Date: 2020/8/18 14:05
 */
@Getter
@Setter
@ToString
public class ResetPasswordDTO {

  private String exPassword;

  private String password;
}
