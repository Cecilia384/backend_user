package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("content") // 指定表名
public class Content {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;

    private Integer userId;          // 发布者用户 ID
    private String title;            // 内容标题
    private String body;             // 内容正文
    private String format;           // 内容格式类型（markdown/html）
    private String tags;             // 标签（以逗号分隔）
    private String category;         // 分类
    private Integer views;           // 浏览量
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间


}
