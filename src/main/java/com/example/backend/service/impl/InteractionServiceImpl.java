package com.example.backend.service.impl;

import com.example.backend.entity.Like;
import com.example.backend.entity.Favorite;
import com.example.backend.entity.UserBehavior;
import com.example.backend.mapper.LikeMapper;
import com.example.backend.mapper.FavoriteMapper;
import com.example.backend.mapper.UserBehaviorMapper;
import com.example.backend.service.InteractionService;
import com.example.backend.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Service
public class InteractionServiceImpl implements InteractionService {

    private static final Logger logger = LoggerFactory.getLogger(InteractionServiceImpl.class);

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private UserBehaviorMapper userBehaviorMapper;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void handleLike(Integer userId, Integer contentId) {
        logger.info("Handling like action for userId={}, contentId={}", userId, contentId);

        try {
            // 查询是否已存在点赞记录
            Like existingLike = likeMapper.findByUserIdAndContentId(userId, contentId);
            logger.info("Existing like record: {}", existingLike);

            // 如果已经点赞，直接返回
            if (existingLike != null && "liked".equals(existingLike.getStatus())) {
                logger.info("User {} already liked content {}", userId, contentId);
                return;
            }

            // 插入或更新点赞记录
            if (existingLike == null) {
                existingLike = new Like();
                existingLike.setUserId(userId);
                existingLike.setContentId(contentId);
                existingLike.setCreatedAt(LocalDateTime.now());
                existingLike.setStatus("liked");
                logger.info("Inserting new like record: {}", existingLike);
                likeMapper.insert(existingLike); // 插入新记录
            } else {
                existingLike.setStatus("liked");
                logger.info("Updating like record: {}", existingLike);
                likeMapper.updateById(existingLike); // 更新记录
            }

            // 记录用户行为
            recordUserBehavior(userId, contentId, "like");

            // 发送通知
            String message = "User " + userId + " liked content " + contentId;
            try {
                notificationService.createNotification(userId, message);
                logger.info("Notification sent for userId={}, contentId={}", userId, contentId);
            } catch (Exception e) {
                // 如果通知失败，记录警告日志，但不影响点赞操作
                logger.warn("Failed to send notification for userId={}, contentId={}", userId, contentId, e);
            }

        } catch (Exception e) {
            // 捕获所有异常并记录错误日志
            logger.error("Error handling like action for userId={}, contentId={}", userId, contentId, e);
            throw new RuntimeException("Failed to handle like action", e);
        }
    }
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

    @Override
    public void handleFavorite(Integer userId, Integer contentId) {
        Favorite existingFavorite = favoriteMapper.findByUserIdAndContentId(userId, contentId);

        handleAction(
                userId,
                contentId,
                "favorite",
                existingFavorite,
                () -> {
                    Favorite favorite = new Favorite();
                    favorite.setUserId(userId);
                    favorite.setContentId(contentId);
                    favorite.setCreatedAt(LocalDateTime.now());
                    favoriteMapper.insert(favorite);
                },
                () -> {
                    String message = "User " + userId + " favorited content " + contentId;
                    notificationService.createNotification(userId, message);
                }
        );
    }

    @Override
    public void handleUnfavorite(Integer userId, Integer contentId) {
        Favorite existingFavorite = favoriteMapper.findByUserIdAndContentId(userId, contentId);
        if (existingFavorite == null) {
            logger.info("User {} has not favorited content {}", userId, contentId);
            return;
        }

        favoriteMapper.deleteById(existingFavorite.getId());
        userBehaviorMapper.deleteByUserAndContent(userId, contentId, "favorite");
    }

    private <T> void handleAction(
            Integer userId,
            Integer contentId,
            String action,
            T existingRecord,
            Runnable insertAction,
            Runnable notificationAction
    ) {
        if (existingRecord != null) {
            logger.info("User {} already performed action {} on content {}", userId, action, contentId);
            return;
        }

        insertAction.run();
        recordUserBehavior(userId, contentId, action);
        notificationAction.run();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void recordUserBehavior(Integer userId, Integer contentId, String action) {
        logger.info("Recording user behavior: userId={}, contentId={}, action={}", userId, contentId, action);

        try {
            // 构建用户行为实体
            UserBehavior userBehavior = new UserBehavior();
            userBehavior.setUserId(userId);
            userBehavior.setContentId(contentId);
            userBehavior.setAction(action);
            userBehavior.setCreatedAt(LocalDateTime.now());

            // 插入用户行为记录
            int rowsInserted = userBehaviorMapper.insert(userBehavior);
            if (rowsInserted > 0) {
                logger.info("Successfully recorded user behavior: {}", userBehavior);
            } else {
                logger.warn("No rows were inserted for user behavior: {}", userBehavior);
            }
        } catch (DataIntegrityViolationException e) {
            // 捕获数据库约束冲突异常（如主键冲突或唯一约束冲突）
            String errorMessage = String.format("Data integrity violation while recording user behavior for userId=%d, contentId=%d, action=%s", userId, contentId, action);
            logger.error(errorMessage, e);
            throw new RuntimeException("Duplicate user behavior entry detected", e);
        } catch (Exception e) {
            // 捕获其他异常
            String errorMessage = String.format("Error recording user behavior for userId=%d, contentId=%d, action=%s", userId, contentId, action);
            logger.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }
}
