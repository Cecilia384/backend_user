package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    // 根据用户名查询用户
    @Select("SELECT * FROM users WHERE username = #{username}")
    User selectByUsername(@Param("username") String username);

    // 根据邮箱查询用户
    @Select("SELECT * FROM users WHERE email = #{email}")
    User selectByEmail(@Param("email") String email);

    // 根据用户名和密码查询用户，用于登录验证
    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
