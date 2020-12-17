package com.inyta.assciation.controller;

import com.inyta.assciation.entity.model.Result;
import com.inyta.assciation.entity.po.Association;
import com.inyta.assciation.service.AssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhangwei
 * @Date: 2020/8/18 15:12
 */
@RestController
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
}
