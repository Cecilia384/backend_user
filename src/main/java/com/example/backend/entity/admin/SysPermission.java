package com.example.backend.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
//目前没什么用
@Data
@TableName("permissions") // 指定表名
public class SysPermission {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;            // 权限ID
    private String permissionName; // 权限名称
    private String permissionCode; // 权限代码
}