package com.example.backend.service;

/**
 * 点赞和收藏功能服务层接口
 */
public interface InteractionService {

    /**
     * 点赞操作
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    void handleLike(Integer userId, Integer contentId);

    /**
     * 取消点赞操作
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    void handleUnlike(Integer userId, Integer contentId);

    /**
     * 收藏操作
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    void handleFavorite(Integer userId, Integer contentId);

    /**
     * 取消收藏操作
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    void handleUnfavorite(Integer userId, Integer contentId);
}