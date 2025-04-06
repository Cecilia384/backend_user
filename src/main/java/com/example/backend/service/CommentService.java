package com.example.backend.service;

import com.example.backend.dto.CommentDTO;
import com.example.backend.vo.CommentVO;

import java.util.List;

/**
 * 评论服务接口
 */
public interface CommentService {
    void addComment(CommentDTO commentDTO); // 添加评论
    List<CommentVO> getCommentsByContentId(Integer contentId); // 根据内容 ID 获取评论列表
}
