package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.dto.CommentDTO;
import com.example.backend.dto.admin.CommentQueryDTO;
import com.example.backend.entity.Comment;
import com.example.backend.vo.admin.CommentListVO;
import com.example.backend.service.CommentManageService;
import com.example.backend.mapper.CommentMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论管理服务实现类
 */
@Service
@Slf4j
public class CommentManageServiceImpl implements CommentManageService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public IPage<CommentListVO> listComments(CommentQueryDTO queryDTO, Page<CommentListVO> page) {
        log.info("查询评论列表，参数：{}", queryDTO);

        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq(queryDTO.getUserId() != null, "c.user_id", queryDTO.getUserId())
                .eq(queryDTO.getContentId() != null, "c.content_id", queryDTO.getContentId())
                .like(StringUtils.hasText(queryDTO.getKeyword()), "c.comment_text", queryDTO.getKeyword())
                .ge(queryDTO.getStartTime() != null, "c.created_at", queryDTO.getStartTime())
                .le(queryDTO.getEndTime() != null, "c.created_at", queryDTO.getEndTime())
                .orderByDesc("c.created_at");

        return commentMapper.selectCommentList(page, wrapper);
    }

    @Override
    @Transactional
    public boolean deleteComment(Integer id) {
        try {
            Comment comment = commentMapper.selectById(id);
            if (comment == null) {
                log.warn("要删除的评论不存在，id：{}", id);
                return false;
            }

            return commentMapper.deleteById(id) > 0;
        } catch (Exception e) {
            log.error("删除评论失败，id：{}，错误信息：{}", id, e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean batchDeleteComments(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            log.warn("批量删除评论传入的id列表为空");
            return false;
        }

        try {
            List<Comment> existingComments = commentMapper.selectBatchIds(ids);
            if (existingComments.size() != ids.size()) {
                log.warn("部分评论不存在，请求删除数量：{}，实际存在数量：{}", ids.size(), existingComments.size());
                return false;
            }

            return commentMapper.deleteBatchIds(ids) == ids.size();
        } catch (Exception e) {
            log.error("批量删除评论失败，ids：{}，错误信息：{}", ids, e.getMessage());
            return false;
        }
    }
}
