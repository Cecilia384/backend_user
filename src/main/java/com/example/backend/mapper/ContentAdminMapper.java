package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Content;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentAdminMapper extends BaseMapper<Content> {
    // 可扩展自定义查询方法
}
