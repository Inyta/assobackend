package com.inyta.assciation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inyta.assciation.entity.dto.EventCond;
import com.inyta.assciation.entity.po.Event;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: zhangwei
 * @Date: 2020/10/31 15:39
 */
public interface EventMapper extends BaseMapper<Event> {

    IPage<Event> queryEventByCond(IPage<Event> page,@Param("eventCond") EventCond eventCond);

    Event queryEventInfo(@Param("eventId")Long eventId);
}
