package com.example.backend.controller.admin;

import com.example.backend.common.Result;
import com.example.backend.service.StatService;
import com.example.backend.vo.admin.stat.UserStatVO;
import com.example.backend.vo.admin.stat.ContentStatVO;
import com.example.backend.vo.admin.stat.InteractionStatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/stat")
public class StatController {

    @Autowired
    private StatService statService;

    /**
     * 用户统计
     */
    @GetMapping("/users")
    public Result<UserStatVO> getUserStats() {
        UserStatVO stats = statService.getUserStats();
        return Result.success(stats);
    }

    /**
     * 内容统计
     */
    @GetMapping("/contents")
    public Result<ContentStatVO> getContentStats() {
        ContentStatVO stats = statService.getContentStats();
        return Result.success(stats);
    }

    /**
     * 互动统计
     */
    @GetMapping("/interactions")
    public Result<InteractionStatVO> getInteractionStats() {
        InteractionStatVO stats = statService.getInteractionStats();
        return Result.success(stats);
    }
}