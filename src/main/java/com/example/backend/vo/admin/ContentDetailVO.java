package com.example.backend.vo.admin;

import com.example.backend.vo.CommentVO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ContentDetailVO {
    private Integer id;            // 内容 ID
    private String title;          // 标题
    private String body;           // 正文内容
    private String format;         // 内容格式类型
    private String tags;           // 标签
    private String category;       // 分类
    private Integer views;         // 浏览量
    private LocalDateTime createdAt; // 发布时间
    private LocalDateTime updatedAt; // 最后更新时间
    private AdminUserVO userInfo;   // 发布者信息
    private List<CommentVO> comments; // 评论列表
}
