package com.inyta.assciation.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * @Author: zhangwei
 * @Date: 2020/10/31 15:26
 */
@Data
@TableName("t_event")
public class Event {

  @TableId
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
