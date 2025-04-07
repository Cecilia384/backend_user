package com.example.backend.dto.admin;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 评论查询条件 DTO
 */
@Data
public class CommentQueryDTO {
    private Integer contentId;
    private Integer userId;
    private String keyword;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
