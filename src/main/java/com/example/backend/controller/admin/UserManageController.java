//package com.example.backend.controller.admin;
//
//import com.example.backend.entity.User;
//import com.example.backend.entity.admin.SysRole;
//import com.example.backend.service.UserManageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * 用户管理控制器
// * 提供用户的增删改查功能，以及用户与角色的唯一关联管理。
// */
//@RestController
//@RequestMapping("/admin/users")
//public class UserManageController {
//
//    @Autowired
//    private UserManageService userManageService;
//
//    /**
//     * 获取所有用户
//     * @return 用户列表
//     */
//    @GetMapping
//    public List<User> listUsers() {
//        return userManageService.listUsers();
//    }
//
//    /**
//     * 根据用户ID获取用户信息
//     * @param id 用户ID
//     * @return 用户对象
//     */
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Integer id) {
//        return userManageService.getUserById(id);
//    }
//
//    /**
//     * 添加用户及其角色关联
//     * @param user 用户对象
//     * @param roleId 角色ID
//     * @return 是否成功
//     */
//    @PostMapping
//    public boolean addUser(@RequestBody User user, @RequestParam Integer roleId) {
//        return userManageService.addUser(user, roleId);
//    }
//
//    /**
//     * 更新用户及其角色关联
//     * @param id 用户ID
//     * @param user 用户对象
//     * @param roleId 角色ID
//     * @return 是否成功
//     */
//    @PutMapping("/{id}")
//    public boolean updateUser(@PathVariable Integer id, @RequestBody User user, @RequestParam Integer roleId) {
//        user.setId(id);
//        return userManageService.updateUser(user, roleId);
//    }
//
//    /**
//     * 删除用户及其角色关联
//     * @param id 用户ID
//     * @return 是否成功
//     */
//    @DeleteMapping("/{id}")
//    public boolean deleteUser(@PathVariable Integer id) {
//        return userManageService.deleteUser(id);
//    }
//
//    /**
//     * 获取用户的角色
//     * @param id 用户ID
//     * @return 用户角色对象
//     */
//    @GetMapping("/{id}/role")
//    public SysRole getUserRole(@PathVariable Integer id) {
//        return userManageService.getUserRole(id);
//    }
//}