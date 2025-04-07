package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.dto.ContentDTO;
import com.example.backend.dto.admin.ContentQueryDTO;
import com.example.backend.entity.Content;
import com.example.backend.mapper.ContentAdminMapper;
import com.example.backend.service.ContentManageService;
import com.example.backend.vo.admin.ContentDetailVO;
import com.example.backend.vo.admin.ContentListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentManageServiceImpl implements ContentManageService {

    @Autowired
    private ContentAdminMapper contentAdminMapper;

    @Override
    public void addContent(ContentDTO dto, Integer userId) {
        Content content = new Content();
        content.setTitle(dto.getTitle());
        content.setBody(dto.getContent());
        content.setCategory(dto.getCategory());
        content.setTags(String.join(",", dto.getTags()));
        content.setFormat(dto.getFormat().name());
        content.setUserId(userId);
        content.setCreatedAt(LocalDateTime.now());
        content.setUpdatedAt(LocalDateTime.now());

        int rows = contentAdminMapper.insert(content);
        if (rows == 0) {
            throw new RuntimeException("Failed to create content");
        }
    }

    @Override
    public Page<ContentListVO> listContents(ContentQueryDTO dto, Page<ContentListVO> page) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        if (dto.getKeyword() != null && !dto.getKeyword().isEmpty()) {
            queryWrapper.like("title", dto.getKeyword());
        }
        if (dto.getCategory() != null && !dto.getCategory().isEmpty()) {
            queryWrapper.eq("category", dto.getCategory());
        }
        if (dto.getStartTime() != null) {
            queryWrapper.ge("created_at", dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            queryWrapper.le("created_at", dto.getEndTime());
        }

        Page<Content> contentPage = contentAdminMapper.selectPage(new Page<>(page.getCurrent(), page.getSize()), queryWrapper);

        Page<ContentListVO> resultPage = new Page<>();
        resultPage.setCurrent(contentPage.getCurrent());
        resultPage.setSize(contentPage.getSize());
        resultPage.setTotal(contentPage.getTotal());
        resultPage.setRecords(contentPage.getRecords().stream().map(content -> {
            ContentListVO vo = new ContentListVO();
            vo.setId(content.getId());
            vo.setTitle(content.getTitle());
            vo.setUsername(content.getUserId().toString());
            vo.setCategory(content.getCategory());
            vo.setTags(content.getTags());
            vo.setViews(content.getViews());
            vo.setCreatedAt(content.getCreatedAt());
            return vo;
        }).collect(Collectors.toList()));

        return resultPage;
    }

    @Override
    public ContentDetailVO getContentDetail(Integer id) {
        Content content = contentAdminMapper.selectById(id);
        if (content == null) {
            throw new IllegalArgumentException("Content not found");
        }
        ContentDetailVO vo = new ContentDetailVO();
        vo.setId(content.getId());
        vo.setTitle(content.getTitle());
        vo.setBody(content.getBody());
        vo.setFormat(content.getFormat());
        vo.setTags(content.getTags());
        vo.setCategory(content.getCategory());
        vo.setViews(content.getViews());
        vo.setCreatedAt(content.getCreatedAt());
        vo.setUpdatedAt(content.getUpdatedAt());
        return vo;
    }

    @Override
    public void deleteContent(Integer id) {
        int rows = contentAdminMapper.deleteById(id);
        if (rows == 0) {
            throw new IllegalArgumentException("Content not found");
        }
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        int rows = contentAdminMapper.deleteBatchIds(ids);
        if (rows == 0) {
            throw new IllegalArgumentException("No content found for deletion");
        }
    }

    @Override
    public Page<ContentListVO> listAllContents(Page<ContentListVO> page) {
        // 无条件分页查询所有文章
        Page<Content> contentPage = contentAdminMapper.selectPage(new Page<>(page.getCurrent(), page.getSize()), new QueryWrapper<>());

        Page<ContentListVO> resultPage = new Page<>();
        resultPage.setCurrent(contentPage.getCurrent());
        resultPage.setSize(contentPage.getSize());
        resultPage.setTotal(contentPage.getTotal());
        resultPage.setRecords(contentPage.getRecords().stream().map(content -> {
            ContentListVO vo = new ContentListVO();
            vo.setId(content.getId());
            vo.setTitle(content.getTitle());
            vo.setUsername(content.getUserId().toString());
            vo.setCategory(content.getCategory());
            vo.setTags(content.getTags());
            vo.setViews(content.getViews());
            vo.setCreatedAt(content.getCreatedAt());
            return vo;
        }).collect(Collectors.toList()));

        return resultPage;
    }
}