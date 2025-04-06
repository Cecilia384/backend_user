package com.example.backend.service;

import com.example.backend.entity.Notification;

import java.util.List;

public interface NotificationService {
    /**
     * 创建通知
     */
    void createNotification(Integer userId, String message, String type);

    /**
     * 标记通知为已读
     */
    void markAsRead(Integer notificationId);

    /**
     * 获取用户的所有通知
     */
    List<Notification> getUserNotifications(Integer userId);
}
