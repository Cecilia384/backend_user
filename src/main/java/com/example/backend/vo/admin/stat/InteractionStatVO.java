package com.example.backend.vo.admin.stat;

import lombok.Data;

import java.util.List;

@Data
public class InteractionStatVO {
    private Long totalLikes;                 // 总点赞数
    private Long totalFavorites;             // 总收藏数
    private Long totalComments;              // 总评论数
//    private List<DailyInteractionStatVO> dailyStats; // 每日互动统计
}
