package com.example.backend.vo.admin;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContentListVO {
    private Integer id;            // 内容 ID
    private String title;          // 标题
    private String username;       // 发布者用户名
    private String category;       // 分类
    private String tags;           // 标签
    private Integer views;         // 浏览量
    private LocalDateTime createdAt; // 发布时间
}
