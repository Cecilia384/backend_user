package com.example.backend.controller;

import com.example.backend.service.impl.InteractionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 点赞和收藏功能的控制器
 */
@RestController
@RequestMapping("/interaction")
public class InteractionController {

    @Autowired
    private InteractionServiceImpl interactionService;

    /**
     * 点赞接口
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    @PostMapping("/like")
    public String like(@RequestParam Integer userId, @RequestParam Integer contentId) {
        interactionService.handleLike(userId, contentId);
        return "Liked successfully!";
    }

    /**
     * 取消点赞接口
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    @PostMapping("/unlike")
    public String unlike(@RequestParam Integer userId, @RequestParam Integer contentId) {
        interactionService.handleUnlike(userId, contentId);
        return "Unliked successfully!";
    }

    /**
     * 收藏接口
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    @PostMapping("/favorite")
    public String favorite(@RequestParam Integer userId, @RequestParam Integer contentId) {
        interactionService.handleFavorite(userId, contentId);
        return "Favorited successfully!";
    }

    /**
     * 取消收藏接口
     * @param userId 用户 ID
     * @param contentId 内容 ID
     */
    @PostMapping("/unfavorite")
    public String unfavorite(@RequestParam Integer userId, @RequestParam Integer contentId) {
        interactionService.handleUnfavorite(userId, contentId);
        return "Unfavorited successfully!";
    }
}