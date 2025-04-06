package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

/**
 * NotificationMapper 接口，用于操作通知表
 */
@Mapper
public interface NotificationMapper extends BaseMapper<Notification> {
}
