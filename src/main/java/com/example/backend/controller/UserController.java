package com.example.backend.controller;

import com.example.backend.dto.RegisterDTO;
import com.example.backend.service.UserService;
import com.example.backend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserVO register(@RequestBody @Valid RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }
}
