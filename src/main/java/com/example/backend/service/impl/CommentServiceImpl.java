package com.example.backend.service.impl;

import com.example.backend.dto.CommentDTO;
import com.example.backend.entity.Comment;
import com.example.backend.mapper.CommentMapper;
import com.example.backend.service.CommentService;
import com.example.backend.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 评论服务实现类
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContentId(commentDTO.getContentId());
        comment.setUserId(1); // 假设用户 ID 从上下文中获取
        comment.setCommentText(commentDTO.getCommentText());
        comment.setCreatedAt(LocalDateTime.now());
        commentMapper.insert(comment);
    }

    @Override
    public List<CommentVO> getCommentsByContentId(Integer contentId) {
        List<Comment> comments = commentMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Comment>().eq("content_id", contentId)
        );

        List<CommentVO> commentVOList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentVO vo = new CommentVO();
            vo.setId(comment.getId());
            vo.setUserId(comment.getUserId());
            vo.setCommentText(comment.getCommentText());
            vo.setCreatedAt(comment.getCreatedAt());
            vo.setReplies(new ArrayList<>()); // 假设没有回复功能
            commentVOList.add(vo);
        }
        return commentVOList;
    }
}
