package com.example.backend.dto;

import com.example.backend.enums.ContentFormat;
import lombok.Data;

import java.util.List;

@Data
public class ContentDTO {
    private String title;
    private String content;
    private ContentFormat format; // 枚举类型
    private List<String> tags;
    private String category;

}
