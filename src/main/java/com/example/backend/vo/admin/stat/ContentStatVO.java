package com.example.backend.vo.admin.stat;


import lombok.Data;

import java.util.List;

@Data
public class ContentStatVO {
    private Long totalContents;          // 总内容数
    private Long pendingReview;          // 待审核内容数
    private List<ContentBriefVO> topViewedContents; // 热门内容
    private List<DailyContentStatVO> dailyStats;    // 每日内容统计
}
