package com.example.backend.mapper;

import com.example.backend.vo.admin.stat.UserStatVO;
import com.example.backend.vo.admin.stat.ContentStatVO;
import com.example.backend.vo.admin.stat.ContentBriefVO;
import com.example.backend.vo.admin.stat.DailyContentStatVO;
import com.example.backend.vo.admin.stat.InteractionStatVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StatMapper {

    /**
     * 获取用户统计信息
     * - 总用户数
     * - 活跃用户数（最近 7 天有行为的用户）
     * - 新增用户数（最近 7 天注册的用户）
     */
    @Select("SELECT " +
            "  (SELECT COUNT(*) FROM users) AS totalUsers, " +
            "  (SELECT COUNT(DISTINCT user_id) FROM user_behavior WHERE created_at >= NOW() - INTERVAL 7 DAY) AS activeUsers, " +
            "  (SELECT COUNT(*) FROM users WHERE created_at >= NOW() - INTERVAL 7 DAY) AS newUsers")
    UserStatVO getUserStats();

    /**
     * 获取内容统计信息
     * - 总内容数
     */
    @Select("SELECT " +
            "  (SELECT COUNT(*) FROM content) AS totalContents " +
            "FROM DUAL")
    ContentStatVO getContentStats();

    /**
     * 获取热门内容（按浏览量排序，返回前 5 条）
     */
    @Select("SELECT " +
            "  c.id, " +
            "  c.title, " +
            "  c.views, " +
            "  COUNT(l.id) AS likes, " +
            "  COUNT(cm.id) AS comments " +
            "FROM content c " +
                    "LEFT JOIN likes l ON c.id = l.content_id " +
            "LEFT JOIN comments cm ON c.id = cm.content_id " +
            "GROUP BY c.id, c.title, c.views " +
            "ORDER BY c.views DESC " +
            "LIMIT 5")
    List<ContentBriefVO> getTopViewedContents();

    /**
     * 获取每日内容统计信息
     */
    @Select("SELECT " +
            "  DATE(created_at) AS date, " +
            "  id AS contentId, " +
            "  title, " +
            "  SUM(views) AS views, " +
            "  SUM(likes) AS likes, " +
            "  SUM(comments) AS comments " +
            "FROM content " +
            "GROUP BY DATE(created_at), id, title")
    List<DailyContentStatVO> getDailyStats();

    /**
     * 获取互动统计信息
     * - 点赞总数
     * - 收藏总数
     * - 评论总数
     */
    @Select("SELECT " +
            "  (SELECT COUNT(*) FROM likes) AS totalLikes, " +
            "  (SELECT COUNT(*) FROM favorites) AS totalFavorites, " +
            "  (SELECT COUNT(*) FROM comments) AS totalComments")
    InteractionStatVO getInteractionStats();
}