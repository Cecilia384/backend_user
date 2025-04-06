package com.example.backend.service.impl;

import com.example.backend.dto.NotificationDTO; // 引入 DTO
import com.example.backend.entity.Notification;
import com.example.backend.mapper.NotificationMapper;
import com.example.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        notification.setStatus("unread"); // 设置为未读
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
            notification.setStatus("read"); // 更新状态为已读
            notificationMapper.updateById(notification); // 更新数据库记录
        }
    }

    /**
     * 获取用户的所有通知（返回 DTO）
     */
    @Override
    public List<NotificationDTO> getUserNotifications(Integer userId) {
        // 查询通知实体列表
        List<Notification> notifications = notificationMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Notification>()
                        .eq("user_id", userId)
                        .orderByDesc("created_at")
        );

        // 将实体转换为 DTO
        List<NotificationDTO> notificationDTOs = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO dto = new NotificationDTO(
                    notification.getId(),
                    notification.getUserId(),
                    notification.getMessage(),
                    notification.getStatus(),
                    notification.getCreatedAt()
            );
            notificationDTOs.add(dto);
        }

        return notificationDTOs;
    }
}
