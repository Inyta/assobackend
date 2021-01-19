package com.inyta.assciation.entity.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: zhangwei
 * @Date: 2020/10/31 15:34
 */
@Getter
@Setter
public class EventCond {

  private String eventName;

  private Long associationId;

  private Date beginTime;

  private Date endTime;

  private Integer state;

  private Integer pageNum;

  private Integer pageSize;

}
