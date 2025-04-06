package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 点赞数据操作层
 */
@Mapper
public interface LikeMapper extends BaseMapper<Like> {
    // 自定义方法：根据用户 ID 和内容 ID 查询点赞记录
    @Select("SELECT * FROM likes WHERE user_id = #{userId} AND content_id = #{contentId}")
    Like findByUserIdAndContentId(@Param("userId") Integer userId, @Param("contentId") Integer contentId);
}
