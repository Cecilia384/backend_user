package com.example.backend.controller.admin;

import com.example.backend.dto.LoginDTO;
import com.example.backend.service.UserService;
import com.example.backend.service.RoleService;
import com.example.backend.vo.AdminUserVO;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 后台管理员认证控制器
 */
@RestController
@RequestMapping("/admin/auth")
public class AdminAuthController {

    private final UserService userService;
    private final RoleService roleService;

    // 使用构造方法注入
    public AdminAuthController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * 管理员登录接口
     * @param loginDTO 登录数据传输对象
     * @return 管理员用户信息或提示语句
     */
    @PostMapping("/login")
    public Object login(@RequestBody @Valid LoginDTO loginDTO) {
        // 验证用户名和密码
        User user = userService.validateUser(loginDTO.getUsername(), loginDTO.getPassword());
        if (user == null) {
            return "用户名或密码错误";
        }

        // 查询用户的角色
        Integer roleId = roleService.getRoleIdByUserId(user.getId());
        if (roleId == null) {
            return "用户角色信息异常，请联系管理员";
        }

        // 判断角色是否为管理员
        if (roleId != 2) { // 假设 role_id = 2 表示管理员角色
            return "权限不足，无法访问后台管理系统";
        }

        // 返回管理员用户信息
        return new AdminUserVO(
                user.getUsername(),
                user.getAvatar(),
                user.getBio(),
                user.getEmail(),
                "管理员"
        );
    }
}
