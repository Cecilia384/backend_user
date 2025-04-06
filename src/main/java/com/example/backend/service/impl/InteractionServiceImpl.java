package com.example.backend.service.impl;

import com.example.backend.entity.Like;
import com.example.backend.entity.Favorite;
import com.example.backend.entity.UserBehavior;
import com.example.backend.mapper.LikeMapper;
import com.example.backend.mapper.FavoriteMapper;
import com.example.backend.mapper.UserBehaviorMapper;
import com.example.backend.service.InteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 点赞和收藏功能的服务层实现，防止重复操作
 */
@Service
public class InteractionServiceImpl implements InteractionService {

    private static final Logger logger = LoggerFactory.getLogger(InteractionServiceImpl.class);

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private UserBehaviorMapper userBehaviorMapper;

    /**
     * 点赞操作
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    @Override
    public void handleLike(Integer userId, Integer contentId) {
        Like existingLike = likeMapper.findByUserIdAndContentId(userId, contentId);
        if (existingLike != null && "liked".equals(existingLike.getStatus())) {
            logger.info("User {} already liked content {}", userId, contentId);
            return;
        }

        if (existingLike == null) {
            Like like = new Like();
            like.setUserId(userId);
            like.setContentId(contentId);
            like.setStatus("liked");
            like.setCreatedAt(LocalDateTime.now());
            likeMapper.insert(like);
            recordUserBehavior(userId, contentId, "like");
        } else {
            existingLike.setStatus("liked");
            likeMapper.updateById(existingLike);
            recordUserBehavior(userId, contentId, "like");
        }
    }

    /**
     * 取消点赞操作
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    @Override
    public void handleUnlike(Integer userId, Integer contentId) {
        Like existingLike = likeMapper.findByUserIdAndContentId(userId, contentId);
        if (existingLike == null || "unliked".equals(existingLike.getStatus())) {
            logger.info("User {} has not liked content {} or already unliked it", userId, contentId);
            return;
        }

        existingLike.setStatus("unliked");
        likeMapper.updateById(existingLike);
        userBehaviorMapper.deleteByUserAndContent(userId, contentId, "like");
    }

    /**
     * 收藏操作
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    @Override
    public void handleFavorite(Integer userId, Integer contentId) {
        try {
            logger.info("Starting handleFavorite for userId={}, contentId={}", userId, contentId);

            // 检查是否已经收藏
            Favorite existingFavorite = favoriteMapper.findByUserIdAndContentId(userId, contentId);
            if (existingFavorite != null) {
                logger.info("User {} already favorited content {}", userId, contentId);
                return;
            }

            // 插入收藏记录
            Favorite favorite = new Favorite();
            favorite.setUserId(userId);
            favorite.setContentId(contentId);
            favorite.setCreatedAt(LocalDateTime.now());
            logger.info("Inserting favorite record: {}", favorite);
            favoriteMapper.insert(favorite);

            // 记录用户行为
            logger.info("Recording user behavior for userId={}, contentId={}, action=favorite", userId, contentId);
            recordUserBehavior(userId, contentId, "favorite");

            logger.info("Successfully handled favorite operation for userId={}, contentId={}", userId, contentId);
        } catch (DataIntegrityViolationException e) {
            logger.error("Database constraint violation while handling favorite operation for userId={}, contentId={}", userId, contentId, e);
            throw new RuntimeException("Duplicate favorite entry detected", e);
        } catch (Exception e) {
            logger.error("Error handling favorite operation for userId={}, contentId={}", userId, contentId, e);
            throw new RuntimeException("Failed to handle favorite operation", e);
        }
    }

    /**
     * 取消收藏操作
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    @Override
    public void handleUnfavorite(Integer userId, Integer contentId) {
        try {
            Favorite existingFavorite = favoriteMapper.findByUserIdAndContentId(userId, contentId);
            if (existingFavorite == null) {
                logger.info("User {} has not favorited content {}", userId, contentId);
                return;
            }

            favoriteMapper.deleteById(existingFavorite.getId());
            userBehaviorMapper.deleteByUserAndContent(userId, contentId, "favorite");
        } catch (Exception e) {
            logger.error("Error handling unfavorite operation for userId={}, contentId={}", userId, contentId, e);
            throw new RuntimeException("Failed to handle unfavorite operation");
        }
    }

    /**
     * 记录用户行为到 user_behavior 表
     * @param userId 用户 ID
     * @param contentId 内容 ID
     * @param action 行为类型（like/favorite）
     */
    private void recordUserBehavior(Integer userId, Integer contentId, String action) {
        try {
            UserBehavior userBehavior = new UserBehavior();
            userBehavior.setUserId(userId);
            userBehavior.setContentId(contentId);
            userBehavior.setAction(action);
            userBehavior.setCreatedAt(LocalDateTime.now());
            userBehaviorMapper.insert(userBehavior);
        } catch (Exception e) {
            logger.error("Error recording user behavior for userId={}, contentId={}, action={}", userId, contentId, action, e);
            throw new RuntimeException("Failed to record user behavior");
        }
    }
}