package com.example.backend.service.impl;

import com.example.backend.entity.Notification;
import com.example.backend.enums.NotificationStatus;
import com.example.backend.mapper.NotificationMapper;
import com.example.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 创建通知
     */
    @Override
    public void createNotification(Integer userId, String message, String type) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setStatus(NotificationStatus.UNREAD.name()); // 设置为未读
        notification.setCreatedAt(LocalDateTime.now());
        notificationMapper.insert(notification); // 插入到数据库
    }

    /**
     * 标记通知为已读
     */
    @Override
    public void markAsRead(Integer notificationId) {
        Notification notification = notificationMapper.selectById(notificationId);
        if (notification != null) {
            notification.setStatus(NotificationStatus.READ.name()); // 更新状态为已读
            notificationMapper.updateById(notification); // 更新数据库记录
        }
    }

    /**
     * 获取用户的所有通知
     */
    @Override
    public List<Notification> getUserNotifications(Integer userId) {
        return notificationMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Notification>()
                        .eq("user_id", userId)
                        .orderByDesc("created_at")
        );
    }
}
