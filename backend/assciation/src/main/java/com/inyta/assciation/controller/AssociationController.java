package com.inyta.assciation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.inyta.assciation.entity.model.Result;
import com.inyta.assciation.entity.po.Association;
import com.inyta.assciation.service.AssociationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangwei
 * @Date: 2020/8/18 15:12
 */
@RestController
@RequestMapping("/association")
public class AssociationController {

  @Autowired
  private AssociationService associationService;

  /**
   * 查询社团
   *
   * @return
   */
  @GetMapping("/queryAssociations")
  public Result<List<Association>> queryAssociation() {
    List<Association> list = associationService.list();
    return Result.success(list);
  }

  /**
   * 下拉框
   *
   * @return
   */
  @GetMapping("/select")
  public Result<List<Association>> select() {
    return Result.success(associationService
        .list(new QueryWrapper<Association>().select("id", "association_name").eq("status", 1)));

  }
}
