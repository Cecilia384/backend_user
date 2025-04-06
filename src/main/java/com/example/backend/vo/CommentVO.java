package com.example.backend.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 评论视图对象，用于返回给前端
 */
@Data
public class CommentVO {
    private Integer id;              // 评论 ID
    private Integer userId;          // 用户 ID
    private String username;         // 用户名
    private String avatar;           // 用户头像
    private String commentText;      // 评论内容
    private LocalDateTime createdAt; // 评论时间
    private List<CommentVO> replies; // 回复列表
}
