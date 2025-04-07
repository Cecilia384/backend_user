package com.example.backend.vo.admin.stat;

import lombok.Data;

@Data
public class DailyInteractionStatVO {
    private String date;       // 日期
    private Long likes;        // 点赞数
    private Long favorites;    // 收藏数
    private Long comments;     // 评论数
}
