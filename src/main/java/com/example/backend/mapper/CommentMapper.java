package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.dto.admin.CommentQueryDTO;
import com.example.backend.entity.Comment;
import com.example.backend.vo.admin.CommentListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

/**
 * 评论数据访问层
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("""
        SELECT c.*, u.username, ct.title as content_title 
        FROM comments c 
        LEFT JOIN users u ON c.user_id = u.id    -- 注意这里是users表
        LEFT JOIN content ct ON c.content_id = ct.id 
        ${ew.customSqlSegment}
    """)
    IPage<CommentListVO> selectCommentList(Page<CommentListVO> page, @Param("ew") QueryWrapper<Comment> wrapper);
}
