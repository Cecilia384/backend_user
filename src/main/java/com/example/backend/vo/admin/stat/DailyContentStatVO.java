package com.example.backend.vo.admin.stat;

import lombok.Data;

@Data
public class DailyContentStatVO {
    private String date;       // 日期，格式如 "2025-04-07"
    private Long contentId;    // 内容ID
    private String title;      // 内容标题
    private Long views;        // 浏览量
    private Long likes;        // 点赞数
    private Long comments;     // 评论数
}
