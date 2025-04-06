package com.example.backend.service.impl;

import com.example.backend.dto.LoginDTO;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.AuthService;
import com.example.backend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserVO login(LoginDTO dto) {
        // 根据用户名查询用户
        User user = userMapper.selectByUsername(dto.getUsername());
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 校验密码
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 构造并返回 UserVO
        return new UserVO(
                user.getUsername(),
                user.getAvatar(),
                user.getBio(),
                user.getEmail()
        );
    }
}
