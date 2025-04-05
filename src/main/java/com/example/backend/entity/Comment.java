package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("comments") // 指定表名
public class Comment {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;

    private Integer contentId;       // 评论的内容 ID
    private Integer userId;          // 评论用户 ID
    private String commentText;      // 评论内容
    private LocalDateTime createdAt; // 评论时间
}
