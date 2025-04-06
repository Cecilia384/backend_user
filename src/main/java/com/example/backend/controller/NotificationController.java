package com.example.backend.controller;

import com.example.backend.dto.NotificationDTO;
import com.example.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通知相关的控制器
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 创建通知
     * @param userId 用户 ID
     * @param message 通知内容
     * @return 操作结果
     */
    @PostMapping("/create")
    public String createNotification(@RequestParam Integer userId,
                                     @RequestParam String message) {
        notificationService.createNotification(userId, message);
        return "Notification created successfully";
    }

    /**
     * 标记通知为已读
     * @param id 通知 ID
     * @return 操作结果
     */
    @PostMapping("/mark-as-read/{id}")
    public String markAsRead(@PathVariable Integer id) {
        notificationService.markAsRead(id);
        return "Notification marked as read";
    }

    /**
     * 获取用户的所有通知
     * @param userId 用户 ID
     * @return 用户的通知列表
     */
    @GetMapping("/user/{userId}")
    public List<NotificationDTO> getUserNotifications(@PathVariable Integer userId) {
        return notificationService.getUserNotifications(userId);
    }
}
