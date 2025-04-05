package com.example.backend.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("likes") // 指定表名
public class Like {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;

    private Integer userId;          // 点赞用户 ID
    private Integer contentId;       // 点赞的内容 ID
    private String status;           // 点赞状态（liked/unliked）
    private LocalDateTime createdAt; // 点赞时间
}
