package com.hancu.bankproject.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_process")
public class Process {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("code")
    private String code;

    @TableField("description")
    private String description;

    @TableField("type")
    private String type;

    @TableField("status")
    private String status;

    @TableField("org_id")
    private Long orgId;

    @TableField("create_user_id")
    private Long createUserId;

    @TableField("sort")
    private Integer sort;

    @TableField("config")
    private String config;

    @TableField("enabled")
    private Boolean enabled;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
