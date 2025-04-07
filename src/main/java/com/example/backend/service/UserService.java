package com.example.backend.service;

import com.example.backend.dto.RegisterDTO;
import com.example.backend.entity.User;
import com.example.backend.vo.UserVO;

public interface UserService {
    /**
     * 用户注册
     * @param dto 注册数据传输对象
     * @return 用户视图对象
     */
    UserVO register(RegisterDTO dto);

    /**
     * 验证用户的用户名和密码
     * @param username 用户名
     * @param password 密码
     * @return 用户对象（如果验证成功），否则返回 null
     */
    User validateUser(String username, String password);
}
