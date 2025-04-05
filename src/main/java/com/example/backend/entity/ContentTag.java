package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("content_tags") // 指定表名
public class ContentTag {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;

    private Integer contentId; // 内容 ID
    private Integer tagId;     // 标签 ID
}
