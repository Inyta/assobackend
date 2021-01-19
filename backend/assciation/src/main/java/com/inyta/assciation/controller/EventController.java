package com.inyta.assciation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inyta.assciation.entity.dto.EventCond;
import com.inyta.assciation.entity.dto.PageDTO;
import com.inyta.assciation.entity.model.Result;
import com.inyta.assciation.entity.po.Event;
import com.inyta.assciation.mapper.EventMapper;
import com.inyta.assciation.service.EventService;
import java.io.IOException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: zhangwei
 * @Date: 2020/10/31 15:24
 */
@RestController
@RequestMapping("/event")
public class EventController {

  @Autowired
  private EventMapper eventMapper;
  @Autowired
  private EventService eventService;

  /**
   * 按条件查询活动
   *
   * @param eventCond
   * @return
   */
  @PostMapping("/queryEventByCond")
  public Result<PageDTO<Event>> queryEventByCond(@RequestBody EventCond eventCond) {
    IPage<Event> page = eventMapper
        .queryEventByCond(new Page<>(eventCond.getPageNum(), eventCond.getPageSize()), eventCond);
    return Result.success(new PageDTO<>(page));
  }

  /**
   * 查询活动详情
   *
   * @param eventId
   * @return
   */
  @GetMapping("/queryEventInfo")
  public Result<Event> queryEventInfo(@RequestParam("eventId") Long eventId) {
    Event event = eventMapper
        .queryEventInfo(eventId, SecurityUtils.getSubject().getPrincipal().toString());
    return Result.success(event);
  }

  @PostMapping("/applyEvent")
  public Result<Void> applyEvent(@RequestBody MultipartFile file, Event event) throws IOException {
    eventService.applyEvent(file,event);
    return Result.success();
  }
}
