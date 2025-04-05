package com.example.backend.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_behavior") // 指定表名
public class UserBehavior {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;

    private Integer userId;          // 用户 ID
    private Integer contentId;       // 内容 ID
    private String action;           // 用户行为类型（view/like/favorite）
    private LocalDateTime createdAt; // 行为时间
}
