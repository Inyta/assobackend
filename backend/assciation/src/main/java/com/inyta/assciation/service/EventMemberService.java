package com.inyta.assciation.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inyta.assciation.entity.po.EventMember;
import com.inyta.assciation.mapper.EventMemberMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhangwei
 * @Date: 2020/11/10 15:05
 */
@Service
public class EventMemberService extends ServiceImpl<EventMemberMapper, EventMember> {

    @Autowired
    private EventMemberMapper eventMemberMapper;

    public synchronized String joinEvent(Long eventId, Long limit) {
        QueryWrapper<EventMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("event_id", eventId);
        Integer num = eventMemberMapper.selectCount(queryWrapper);
        if (limit.intValue() == num) {
            return "人数已满";
        } else {
            try {
                EventMember eventMember = new EventMember();
                eventMember.setUserId(Long.valueOf(SecurityUtils.getSubject().getPrincipal().toString()));
                eventMember.setEventId(eventId);
                this.save(eventMember);
                return "报名成功";
            } catch (Exception e) {
                return "报名失败，请检查报名情况";
            }
        }
    }

    public int cancelJoinEvent(Long eventId) {
        String userId = SecurityUtils.getSubject().getPrincipal().toString();
        QueryWrapper<EventMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("event_id", eventId).eq("user_id", userId);
        return eventMemberMapper.delete(queryWrapper);
    }
}
