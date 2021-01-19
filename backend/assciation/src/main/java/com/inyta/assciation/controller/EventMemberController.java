package com.inyta.assciation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.inyta.assciation.entity.model.Result;
import com.inyta.assciation.entity.po.EventMember;
import com.inyta.assciation.service.EventMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangwei
 * @Date: 2020/11/10 15:04
 */
@RestController
@RequestMapping("/eventMember")
public class EventMemberController {

  @Autowired
  private EventMemberService eventMemberService;

  @GetMapping("/eventMemberCount")
  public Result<Integer> eventMemberCount(@RequestParam("eventId") Long eventId) {
    QueryWrapper<EventMember> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("event_id", eventId);
    int count = eventMemberService.count(queryWrapper);
    return Result.success(count);
  }

  @GetMapping("/joinEvent")
  public Result<Void> joinEvent(@RequestParam("eventId") Long eventId,
      @RequestParam("limit") Long limit) {
    return Result.success(eventMemberService.joinEvent(eventId, limit));
  }

  @GetMapping("/cancelJoinEvent")
  public Result<Void> cancelJoinEvent(@RequestParam("eventId") Long eventId) {
    return eventMemberService.cancelJoinEvent(eventId) > 0 ? Result.success("取消成功")
        : Result.failed("发生错误");
  }
}
