package com.example.demo.controller;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    //使用@Autowired注解，自动注入UserMapper对象
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/user")
    public String query() {
        List<User> users = userMapper.getAll();
        System.out.println(users);
        return "查询用户";
    }
//    @Operation(summary = "获取用户",description = "根据用户ID获取用户信息")
//    @GetMapping("/user/{id}")
//    public String getUserById(@PathVariable int id) {
//        //@PathVariable注解，获取路径中的参数
//        System.out.println("id:"+id);
//        return "根据ID获取用户信息";
//    }
//    @PostMapping("/user")
//    public String save(User user) {
//        return "添加用户";
//    }
//    @PutMapping("/user")
//    public  String update(User user) {
//        return "更新用户";
//    }
//    @DeleteMapping("/user/{id}")
//    public String delete(@PathVariable int id) {
//
//        System.out.println("id:"+id);
//        return "根据ID删除用户";
//    }
}
