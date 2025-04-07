//package com.example.backend.service.impl;
//
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.example.backend.entity.User;
//import com.example.backend.entity.admin.SysRole;
//import com.example.backend.entity.admin.SysUserRole;
//import com.example.backend.mapper.UserMapper;
//import com.example.backend.mapper.SysUserRoleMapper;
//import com.example.backend.mapper.RoleMapper;
//import com.example.backend.service.UserManageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
///**
// * 用户管理服务实现类
// * 提供用户的增删改查功能，并管理用户与角色的唯一关联。
// */
//@Service
//public class UserManageServiceImpl extends ServiceImpl<UserMapper, User> implements UserManageService {
//
//    @Autowired
//    private SysUserRoleMapper sysUserRoleMapper;
//
//    @Autowired
//    private RoleMapper roleMapper;
//
//    /**
//     * 查询所有用户
//     * @return 用户列表
//     */
//    @Override
//    public List<User> listUsers() {
//        return this.list(); // 查询所有用户
//    }
//
//    /**
//     * 根据用户ID获取用户信息
//     * @param userId 用户ID
//     * @return 用户对象
//     */
//    @Override
//    public User getUserById(Integer userId) {
//        return this.getById(userId); // 根据 ID 查询用户
//    }
//
//    /**
//     * 添加用户及其角色关联
//     * @param user 用户对象
//     * @param roleId 角色ID
//     * @return 是否成功
//     */
//    @Transactional
//    @Override
//    public boolean addUser(User user, Integer roleId) {
//        // 保存用户
//        this.save(user);
//
//        // 保存用户角色唯一关联
//        SysUserRole userRole = new SysUserRole();
//        userRole.setUserId(user.getId());
//        userRole.setRoleId(roleId);
//        sysUserRoleMapper.insert(userRole);
//
//        return true;
//    }
//
//    /**
//     * 更新用户及其角色关联
//     * @param user 用户对象
//     * @param roleId 角色ID
//     * @return 是否成功
//     */
//    @Transactional
//    @Override
//    public boolean updateUser(User user, Integer roleId) {
//        // 更新用户信息
//        this.updateById(user);
//
//        // 删除旧的角色关联（确保用户只有一个角色）
//        sysUserRoleMapper.deleteByUserId(user.getId());
//
//        // 添加新的角色关联
//        SysUserRole userRole = new SysUserRole();
//        userRole.setUserId(user.getId());
//        userRole.setRoleId(roleId);
//        sysUserRoleMapper.insert(userRole);
//
//        return true;
//    }
//
//    /**
//     * 删除用户及其角色关联
//     * @param userId 用户ID
//     * @return 是否成功
//     */
//    @Transactional
//    @Override
//    public boolean deleteUser(Integer userId) {
//        // 删除用户
//        this.removeById(userId);
//
//        // 删除角色关联
//        sysUserRoleMapper.deleteByUserId(userId);
//
//        return true;
//    }
//
//    /**
//     * 获取用户的角色
//     * @param userId 用户ID
//     * @return 用户角色
//     */
//    @Override
//    public SysRole getUserRole(Integer userId) {
//        // 查询用户角色关联
//        SysUserRole userRole = sysUserRoleMapper.selectByUserId(userId);
//
//        // 如果用户没有角色，返回 null
//        if (userRole == null) {
//            return null;
//        }
//
//        // 根据角色 ID 查询角色信息
//        return roleMapper.selectById(userRole.getRoleId());
//    }
//}
