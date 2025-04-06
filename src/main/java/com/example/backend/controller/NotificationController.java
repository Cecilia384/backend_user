package com.example.backend.controller;

import com.example.backend.entity.Notification;
import com.example.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 创建通知
     */
    @PostMapping("/create")
    public String createNotification(@RequestParam Integer userId,
                                     @RequestParam String message,
                                     @RequestParam String type) {
        notificationService.createNotification(userId, message, type);
        return "Notification created successfully";
    }

    /**
     * 标记通知为已读
     */
    @PostMapping("/mark-as-read/{id}")
    public String markAsRead(@PathVariable Integer id) {
        notificationService.markAsRead(id);
        return "Notification marked as read";
    }

    /**
     * 获取用户的所有通知
     */
    @GetMapping("/user/{userId}")
    public List<Notification> getUserNotifications(@PathVariable Integer userId) {
        return notificationService.getUserNotifications(userId);
    }
}
