package com.example.backend.service.impl;

import com.example.backend.entity.admin.SysUserRole;
import com.example.backend.mapper.SysUserRoleMapper;
import com.example.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Integer getRoleIdByUserId(Integer userId) {
        System.out.println("查询用户角色，用户ID: " + userId);
        SysUserRole userRole = sysUserRoleMapper.selectByUserId(userId);
        if (userRole == null) {
            System.out.println("未找到用户角色关联，用户ID: " + userId);
            return null; // 用户没有角色
        }
        System.out.println("查询到的角色ID: " + userRole.getRoleId());
        return userRole.getRoleId();
    }

}
