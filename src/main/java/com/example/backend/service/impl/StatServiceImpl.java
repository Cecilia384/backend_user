package com.example.backend.service.impl;

import com.example.backend.mapper.StatMapper;
import com.example.backend.service.StatService;
import com.example.backend.vo.admin.stat.ContentStatVO;
import com.example.backend.vo.admin.stat.UserStatVO;
import com.example.backend.vo.admin.stat.InteractionStatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatMapper statMapper;

    @Override
    public UserStatVO getUserStats() {
        return statMapper.getUserStats();
    }

    @Override
    public ContentStatVO getContentStats() {
        ContentStatVO contentStatVO = statMapper.getContentStats();
        contentStatVO.setTopViewedContents(statMapper.getTopViewedContents()); // 填充热门内容数据
        return contentStatVO;
    }

    @Override
    public InteractionStatVO getInteractionStats() {
        return statMapper.getInteractionStats();
    }
}