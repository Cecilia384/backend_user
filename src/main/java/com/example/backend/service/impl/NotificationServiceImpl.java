package com.example.backend.service.impl;

import com.example.backend.dto.NotificationDTO;
import com.example.backend.entity.Notification;
import com.example.backend.mapper.NotificationMapper;
import com.example.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public void createNotification(Integer userId, String message) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setStatus("unread"); // 默认未读状态
        notification.setCreatedAt(LocalDateTime.now());
        notificationMapper.insert(notification);
    }

    @Override
    public void markAsRead(Integer notificationId) {
        Notification notification = notificationMapper.findById(notificationId);
        if (notification != null) {
            notification.setStatus("read");
            notificationMapper.updateById(notification);
        }
    }

    @Override
    public List<NotificationDTO> getUserNotifications(Integer userId) {
        List<Notification> notifications = notificationMapper.findByUserId(userId);
        return notifications.stream()
                .map(notification -> new NotificationDTO(
                        notification.getId(),
                        notification.getUserId(),
                        notification.getMessage(),
                        notification.getStatus(),
                        notification.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
