package com.example.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class ContentSearchDTO {
    private String keyword;          // 搜索关键字（标题或正文）
    private String category;         // 分类过滤
    private List<String> tags;       // 标签过滤
    private Integer page;            // 当前页码
    private Integer size;            // 每页条数
    private String sortBy;           // 排序字段（如：createTime, viewCount）
    private Boolean isAsc;           // 是否升序
}