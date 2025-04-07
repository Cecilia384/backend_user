package com.example.backend.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("logs") // 指定表名
public class SysLog {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;               // 日志ID
    private String action;            // 操作类型
    private String details;           // 操作详情
    private LocalDateTime timestamp;  // 时间戳
}