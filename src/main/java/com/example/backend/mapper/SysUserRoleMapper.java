package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.admin.SysUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 根据用户ID查询用户角色关联
     * @param userId 用户ID
     * @return 用户角色关联
     */
    @Select("SELECT * FROM user_roles WHERE user_id = #{userId}")
    SysUserRole selectByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户ID删除用户角色关联
     * @param userId 用户ID
     */
    @Delete("DELETE FROM user_roles WHERE user_id = #{userId}")
    void deleteByUserId(@Param("userId") Integer userId);

    /**
     * 插入用户角色关联
     *
     * @param userRole 用户角色关联对象
     * @return 插入成功的行数
     */
    @Insert("INSERT INTO user_roles (user_id, role_id) VALUES (#{userId}, #{roleId})")
    int insert(SysUserRole userRole);
}
