package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * NotificationMapper 接口，用于操作通知表
 */
@Mapper
public interface NotificationMapper extends BaseMapper<Notification> {

    /**
     * 根据用户 ID 查询所有通知
     * @param userId 用户 ID
     * @return 通知列表
     */
    @Select("SELECT * FROM notification WHERE user_id = #{userId} ORDER BY created_at DESC")
    List<Notification> findByUserId(@Param("userId") Integer userId);

    /**
     * 根据通知 ID 查询通知
     * @param id 通知 ID
     * @return 通知实体
     */
    @Select("SELECT * FROM notification WHERE id = #{id}")
    Notification findById(@Param("id") Integer id);
}
