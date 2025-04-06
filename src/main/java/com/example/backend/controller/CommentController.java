package com.example.backend.controller;

import com.example.backend.dto.CommentDTO;
import com.example.backend.service.CommentService;
import com.example.backend.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论控制器
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public String addComment(@RequestBody CommentDTO commentDTO) {
        commentService.addComment(commentDTO);
        return "Comment added successfully!";
    }

    @GetMapping("/{contentId}")
    public List<CommentVO> getComments(@PathVariable Integer contentId) {
        return commentService.getCommentsByContentId(contentId);
    }
}
/**
 * 该类是一个 REST 控制器，用于处理与评论相关的 HTTP 请求。
 * - `addComment` 方法用于添加评论，接收一个 `CommentDTO` 对象作为请求体。
 * - `getComments` 方法根据内容 ID 获取评论列表，并返回一个 `List<CommentVO>` 对象。
 *
 * 注意：在实际应用中，可能还需要添加异常处理、权限验证等功能。
 */