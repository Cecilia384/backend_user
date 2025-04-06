package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.UserBehavior;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户行为数据操作层
 */
@Mapper
public interface UserBehaviorMapper extends BaseMapper<UserBehavior> {

    /**
     * 根据用户 ID、内容 ID 和行为类型删除记录
     */
    @Delete("DELETE FROM user_behavior WHERE user_id = #{userId} AND content_id = #{contentId} AND action = #{action}")
    void deleteByUserAndContent(@Param("userId") Integer userId,
                                @Param("contentId") Integer contentId,
                                @Param("action") String action);
}
