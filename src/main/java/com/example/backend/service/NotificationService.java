package com.example.backend.service;

import com.example.backend.dto.NotificationDTO; // 引入 DTO

import java.util.List;

/**
 * NotificationService 接口
 */
public interface NotificationService {
    void createNotification(Integer userId, String message, String type);

    void markAsRead(Integer notificationId);

    List<NotificationDTO> getUserNotifications(Integer userId); // 返回 DTO
}
