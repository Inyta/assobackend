package com.inyta.assciation.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

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

    private Integer gender;

    private Date createTime;

    private Date updateTime;

    private Integer role;

    private Integer status;
}
