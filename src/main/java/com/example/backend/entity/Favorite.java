package com.example.backend.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("favorites") // 指定表名
public class Favorite {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;

    private Integer userId;          // 收藏用户 ID
    private Integer contentId;       // 收藏的内容 ID
    private LocalDateTime createdAt; // 收藏时间
}
