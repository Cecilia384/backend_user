package com.example.backend.vo;

import lombok.Data;
import java.util.Arrays;
import java.util.List;

@Data
public class ContentVO {
    private Integer id;              // 内容 ID
    private Integer userId;          // 发布者用户 ID
    private String title;            // 内容标题
    private String body;             // 内容正文
    private String format;           // 内容格式类型（markdown/html）
    private List<String> tags;       // 标签列表（转换后）
    private String category;         // 分类
    private Integer views;           // 浏览量
    private String createdAt;        // 创建时间（格式化为字符串）
    private String updatedAt;        // 更新时间（格式化为字符串）

    // 可选：添加作者信息
    private String authorName;       // 作者名称，需要关联查询

    // 将逗号分隔的标签字符串转换为List
    public void setTagsFromString(String tagsStr) {
        if (tagsStr != null && !tagsStr.isEmpty()) {
            this.tags = Arrays.asList(tagsStr.split(","));
        }
    }
}
