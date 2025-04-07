package com.example.backend.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_roles") // 指定表名
public class SysUserRole {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;      // 主键ID
    private Integer userId;  // 用户ID
    private Integer roleId;  // 角色ID
}
