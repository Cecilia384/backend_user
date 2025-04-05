package com.example.backend.service;

import com.example.backend.dto.RegisterDTO;
import com.example.backend.vo.UserVO;

public interface UserService {
    UserVO register(RegisterDTO dto);
}
