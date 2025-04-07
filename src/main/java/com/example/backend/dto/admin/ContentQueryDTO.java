package com.example.backend.dto.admin;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContentQueryDTO {
    private String keyword;       // 搜索关键字
    private String category;      // 分类
    private LocalDateTime startTime; // 创建开始时间
    private LocalDateTime endTime;   // 创建结束时间
}
