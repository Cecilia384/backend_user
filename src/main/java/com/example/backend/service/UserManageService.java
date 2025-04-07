package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.entity.admin.SysRole;

import java.util.List;

/**
 * 用户管理服务接口
 * 定义用户的增删改查功能，以及用户与角色的唯一关联管理。
 */
public interface UserManageService {

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> listUsers();

    /**
     * 根据用户ID查询用户信息
     * @param userId 用户ID
     * @return 用户对象
     */
    User getUserById(Integer userId);

    /**
     * 添加用户及其角色关联
     * @param user 用户对象
     * @param roleId 角色ID
     * @return 是否成功
     */
    boolean addUser(User user, Integer roleId);

    /**
     * 更新用户及其角色关联
     * @param user 用户对象
     * @param roleId 角色ID
     * @return 是否成功
     */
    boolean updateUser(User user, Integer roleId);

    /**
     * 删除用户及其角色关联
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean deleteUser(Integer userId);

    /**
     * 获取用户的角色
     * @param userId 用户ID
     * @return 用户角色对象
     */
    SysRole getUserRole(Integer userId);
}