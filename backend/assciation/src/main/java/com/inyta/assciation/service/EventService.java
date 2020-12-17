package com.inyta.assciation.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inyta.assciation.entity.po.Event;
import com.inyta.assciation.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhangwei
 * @Date: 2020/11/10 14:53
 */
@Service
public class EventService extends ServiceImpl<EventMapper, Event> {

    @Autowired
    private EventMapper eventMapper;

}
