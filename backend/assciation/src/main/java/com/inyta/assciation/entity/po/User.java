package com.inyta.assciation.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.inyta.assciation.entity.enums.Gender;
import java.util.Date;
import lombok.Data;

/**
 * @Author: zhangwei
 * @Date: 2020/8/7 15:13
 */
@Data
@TableName("t_user")
public class User {

  @TableId
  private Long userId;

  private String userName;

  private String password;

  private String avatarUrl;

  private String phone;

  private Gender gender;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;

  private Integer role;

  private Integer status;
}
