package com.inyta.assciation.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: zhangwei
 * @Date: 2020/8/18 13:38
 */
@Data
@TableName("t_association_member")
public class AssociationMember {
    private Integer id;

    private Integer assoId;

    private Integer userId;
}
