package com.example.backend.service;

import com.example.backend.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {

    /**
     * 创建通知
     * @param userId 用户 ID
     * @param message 通知内容
     */
    void createNotification(Integer userId, String message);

    /**
     * 标记通知为已读
     * @param notificationId 通知 ID
     */
    void markAsRead(Integer notificationId);

    /**
     * 获取用户的所有通知
     * @param userId 用户 ID
     * @return 用户通知的 DTO 列表
     */
    List<NotificationDTO> getUserNotifications(Integer userId);
}
