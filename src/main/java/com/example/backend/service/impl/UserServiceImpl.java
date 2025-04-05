package com.example.backend.service.impl;

import com.example.backend.dto.RegisterDTO;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;
import com.example.backend.utils.PasswordUtils;
import com.example.backend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordUtils passwordUtils;

    @Override
    public UserVO register(RegisterDTO dto) {
        // 检查用户名和邮箱是否已存在
        if (userMapper.selectByUsername(dto.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        if (userMapper.selectByEmail(dto.getEmail()) != null) {
            throw new RuntimeException("邮箱已被注册");
        }

        // 创建用户实体
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordUtils.encrypt(dto.getPassword())); // 加密密码
        user.setEmail(dto.getEmail());
        user.setAvatar(dto.getAvatar());
        user.setBio(dto.getBio());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
//        user.setStatus(1); // 默认状态为正常

        // 保存到数据库
        userMapper.insert(user);

        // 返回用户信息
        return new UserVO(user.getUsername(), user.getAvatar(), user.getBio(), user.getEmail());
    }
}
