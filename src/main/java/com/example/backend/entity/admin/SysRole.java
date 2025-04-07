package com.example.backend.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("roles") // 指定表名
public class SysRole {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;          // 角色ID
    private String roleName;     // 角色名称
    private String roleCode;     // 角色代码
}