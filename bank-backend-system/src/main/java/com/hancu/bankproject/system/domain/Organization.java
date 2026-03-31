package com.hancu.bankproject.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_organization")
public class Organization {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("code")
    private String code;

    @TableField("parent_id")
    private Long parentId;

    @TableField("address")
    private String address;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("level")
    private Integer level;

    @TableField("enabled")
    private Boolean enabled;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
