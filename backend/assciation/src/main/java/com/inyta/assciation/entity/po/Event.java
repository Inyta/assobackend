package com.inyta.assciation.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zhangwei
 * @Date: 2020/10/31 15:26
 */
@Data
public class Event {
    private Long id;

    private String eventName;

    private String eventPic;

    private String eventAddress;

    private Long associationId;

    @TableField(exist = false)
    private String associationName;

    private Integer limit;

    private Date applyTime;

    private Date beginTime;

    private Date endTime;

    @TableField(exist = false)
    private int memberCount;
    @TableField(exist = false)
    private boolean isJoin;

}
