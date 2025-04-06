package com.example.backend.controller;

import com.example.backend.dto.LoginDTO;
import com.example.backend.service.AuthService;
import com.example.backend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserVO login(@RequestBody @Valid LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }
}
