package com.example.backend.entity.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role_permissions") // 指定表名
public class SysRolePermission {
    private Integer roleId;       // 角色ID
    private Integer permissionId; // 权限ID
}