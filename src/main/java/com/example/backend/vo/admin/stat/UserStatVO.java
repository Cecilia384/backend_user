package com.example.backend.vo.admin.stat;

import lombok.Data;

import java.util.List;

@Data
public class UserStatVO {
    private Long totalUsers;          // 总用户数
    private Long activeUsers;         // 活跃用户数
    private Long newUsers;            // 新增用户数
    private List<DailyUserStatVO> dailyStats; // 每日用户统计
}
