package com.inyta.assciation.entity.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: zhangwei
 * @Date: 2020/11/11 15:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    private List<T> data;

    private Long total;

    private Long size;

    private Long num;

    public PageDTO(IPage<T> page) {
        this.data = page.getRecords();
        this.total = page.getTotal();
        this.num = page.getCurrent();
        this.size = page.getSize();
    }
}
