package com.example.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.dto.ContentDTO;
import com.example.backend.dto.admin.ContentQueryDTO;
import com.example.backend.vo.admin.ContentDetailVO;
import com.example.backend.vo.admin.ContentListVO;

import java.util.List;

public interface ContentManageService {
    // 分页查询文章列表（支持条件查询）
    Page<ContentListVO> listContents(ContentQueryDTO dto, Page<ContentListVO> page);

    // 获取文章详情
    ContentDetailVO getContentDetail(Integer id);

    // 删除单篇文章
    void deleteContent(Integer id);

    // 批量删除文章
    void batchDelete(List<Integer> ids);

    // 新增文章
    void addContent(ContentDTO dto, Integer userId);

    // 分页查询所有文章（无条件查询）
    Page<ContentListVO> listAllContents(Page<ContentListVO> page);
}