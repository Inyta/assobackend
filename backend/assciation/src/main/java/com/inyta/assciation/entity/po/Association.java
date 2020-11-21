package com.inyta.assciation.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zhangwei
 * @Date: 2020/8/18 13:37
 */
@Data
@TableName("t_association")
public class Association {
    private Long id;

    private String associationName;

    private Integer leaderId;

    private String picUrl;

    private Integer status;

    private String desrc;

    private Date createTime;

    private Date updateTime;
}
