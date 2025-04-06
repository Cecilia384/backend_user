package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("notifications") // 指定表名
public class Notification {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;
    private Integer userId;          // 接收通知的用户 ID
    private String message;          // 通知内容
    private String status;           // 通知状态（unread/read）
    private LocalDateTime createdAt; // 通知时间
}
