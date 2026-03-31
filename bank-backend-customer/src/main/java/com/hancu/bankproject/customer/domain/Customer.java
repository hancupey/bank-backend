package com.hancu.bankproject.customer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("customer")
public class Customer {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("customer_code")
    private String customerCode;

    @TableField("customer_type")
    private String customerType;

    @TableField("name")
    private String name;

    @TableField("status")
    private String status;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
