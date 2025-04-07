package com.example.backend.vo.admin.stat;

import lombok.Data;

@Data
public class DailyUserStatVO {
    private String date;       // 日期
    private Long newUsers;     // 新增用户数
    private Long activeUsers;  // 活跃用户数
}
