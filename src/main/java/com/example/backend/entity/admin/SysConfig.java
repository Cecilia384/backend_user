package com.example.backend.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("configs") // 指定表名
public class SysConfig {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;          // 配置ID
    private String configKey;    // 配置键
    private String configValue;  // 配置值
}