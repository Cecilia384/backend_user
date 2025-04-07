package com.example.backend.controller.admin;

import com.example.backend.common.Result;
import com.example.backend.dto.admin.CommentQueryDTO;
import com.example.backend.vo.admin.CommentListVO;
import com.example.backend.service.CommentManageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 评论管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/comments")
public class CommentManageController {

    @Autowired
    private CommentManageService commentManageService;

    /**
     * 分页查询评论列表
     */
    @GetMapping("/list")
    public Result<IPage<CommentListVO>> listComments(CommentQueryDTO queryDTO,
                                                     @RequestParam(defaultValue = "1") Integer current,
                                                     @RequestParam(defaultValue = "10") Integer size) {
        Page<CommentListVO> page = new Page<>(current, size);
        IPage<CommentListVO> result = commentManageService.listComments(queryDTO, page);
        return Result.success(result);
    }

    /**
     * 删除单个评论
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteComment(@PathVariable Integer id) {
        boolean success = commentManageService.deleteComment(id);
        return success ? Result.success() : Result.error("删除失败");
    }

    /**
     * 批量删除评论
     */
    @DeleteMapping("/batch")
    public Result<Void> batchDeleteComments(@RequestBody List<Integer> ids) {
        boolean success = commentManageService.batchDeleteComments(ids);
        return success ? Result.success() : Result.error("批量删除失败");
    }
}
