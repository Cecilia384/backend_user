package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tags") // 指定表名
public class Tag {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;

    private String name;             // 标签名称
    private LocalDateTime createdAt; // 创建时间
}
