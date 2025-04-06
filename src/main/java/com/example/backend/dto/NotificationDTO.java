package com.example.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * NotificationDTO 用于传递通知数据到前端
 */
@Data
public class NotificationDTO {
    private Integer id;              // 通知 ID
    private Integer userId;          // 接收通知的用户 ID
    private String message;          // 通知内容
    private String status;           // 通知状态（unread/read）
    private String formattedCreatedAt; // 格式化后的通知时间

    // 构造方法
    public NotificationDTO(Integer id, Integer userId, String message, String status, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.status = status;
        this.formattedCreatedAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
