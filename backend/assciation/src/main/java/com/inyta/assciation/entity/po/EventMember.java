package com.inyta.assciation.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: zhangwei
 * @Date: 2020/11/10 15:02
 */
@Data
@TableName("t_event_member")
public class EventMember {

  private Long id;

  private Long userId;

  private Long eventId;
}
