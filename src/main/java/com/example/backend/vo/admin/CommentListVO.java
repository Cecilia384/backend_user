package com.example.backend.vo.admin;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 评论列表展示 VO
 */
@Data
public class CommentListVO {
    private Integer id;
    private Integer contentId;
    private String contentTitle;
    private Integer userId;
    private String username;
    private String commentText;    // 修改为commentText
    private LocalDateTime createdAt; // 修改为createdAt
}
