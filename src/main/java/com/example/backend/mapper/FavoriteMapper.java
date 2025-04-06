package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 收藏数据操作层
 */
@Mapper
public interface FavoriteMapper extends BaseMapper<Favorite> {

    /**
     * 根据用户 ID 和内容 ID 查询收藏记录
     * @param userId 用户 ID
     * @param contentId 内容 ID
     * @return Favorite 收藏记录
     */
    @Select("SELECT * FROM favorites WHERE user_id = #{userId} AND content_id = #{contentId}")
    Favorite findByUserIdAndContentId(@Param("userId") Integer userId, @Param("contentId") Integer contentId);
}
