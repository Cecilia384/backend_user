package com.example.backend.service;

import com.example.backend.vo.admin.stat.UserStatVO;
import com.example.backend.vo.admin.stat.ContentStatVO;
import com.example.backend.vo.admin.stat.InteractionStatVO;

public interface StatService {
    /**
     * 获取用户统计信息
     */
    UserStatVO getUserStats();

    /**
     * 获取内容统计信息
     */
    ContentStatVO getContentStats();

    /**
     * 获取互动统计信息
     */
    InteractionStatVO getInteractionStats();
}