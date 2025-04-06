package com.example.backend.service;

import com.example.backend.dto.LoginDTO;
import com.example.backend.vo.UserVO;

public interface AuthService {
    UserVO login(LoginDTO dto);
}
