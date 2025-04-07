package com.example.backend.service;

import com.example.backend.dto.CommentDTO;
import com.example.backend.dto.admin.CommentQueryDTO;
import com.example.backend.vo.admin.CommentListVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 评论管理服务接口
 */
public interface CommentManageService {
    /**
     * 分页查询评论列表
     */
    IPage<CommentListVO> listComments(CommentQueryDTO queryDTO, Page<CommentListVO> page);

    /**
     * 删除单个评论
     */
    boolean deleteComment(Integer id);

    /**
     * 批量删除评论
     */
    boolean batchDeleteComments(List<Integer> ids);
}
