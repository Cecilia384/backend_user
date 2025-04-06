package com.example.backend.dto;

import lombok.Data;

/**
 * 评论数据传输对象，用于接收前端请求
 */
@Data
public class CommentDTO {
    private Integer contentId;  // 评论的内容 ID
    private Integer parentId;   // 父评论 ID
    private String commentText; // 评论内容
}
/**
 * 该类用于接收前端传来的评论数据，包含内容 ID、父评论 ID 和评论文本。
 * 通过使用 @Data 注解，自动生成了 getter 和 setter 方法。
 *
 * 注意：在实际应用中，可能还需要添加其他字段，例如用户 ID、评论时间等。
 */
