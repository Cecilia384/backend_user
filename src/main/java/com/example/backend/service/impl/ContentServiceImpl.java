package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.dto.ContentDTO;
import com.example.backend.dto.ContentSearchDTO;
import com.example.backend.entity.Content;
import com.example.backend.mapper.ContentMapper;
import com.example.backend.service.ContentService;
import com.example.backend.vo.ContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public void createContent(ContentDTO dto) {
        Content content = new Content();
        content.setUserId(1); // 假设用户 ID 从上下文获取
        content.setTitle(dto.getTitle());
        content.setBody(dto.getContent());
        content.setFormat(dto.getFormat().name());
        content.setTags(String.join(",", dto.getTags()));
        content.setCategory(dto.getCategory());
        content.setViews(0);
        content.setCreatedAt(LocalDateTime.now());
        content.setUpdatedAt(LocalDateTime.now());

        contentMapper.insert(content);
    }

    @Override
    public void updateContent(Integer id, ContentDTO dto) {
        Content content = contentMapper.selectById(id);
        if (content == null) {
            throw new RuntimeException("内容不存在");
        }

        content.setTitle(dto.getTitle());
        content.setBody(dto.getContent());
        content.setFormat(dto.getFormat().name());
        content.setTags(String.join(",", dto.getTags()));
        content.setCategory(dto.getCategory());
        content.setUpdatedAt(LocalDateTime.now());

        contentMapper.updateById(content);
    }

    @Override
    public void deleteContent(Integer id) {
        int result = contentMapper.deleteById(id);
        if (result == 0) {
            throw new RuntimeException("删除失败，内容不存在");
        }
    }

    @Override
    public ContentVO getContentById(Integer id) {
        Content content = contentMapper.selectById(id);
        if (content == null) {
            throw new RuntimeException("内容不存在");
        }

        return convertToVO(content);
    }

    @Override
    public List<ContentVO> listContents(String category, String tag, Integer page, Integer size) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();

        if (category != null && !category.isEmpty()) {
            queryWrapper.eq("category", category);
        }

        if (tag != null && !tag.isEmpty()) {
            queryWrapper.like("tags", tag);
        }

        Page<Content> contentPage = new Page<>(page == null ? 1 : page, size == null ? 10 : size);
        Page<Content> resultPage = contentMapper.selectPage(contentPage, queryWrapper);

        return resultPage.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContentVO> searchContents(ContentSearchDTO searchDTO) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();

        if (searchDTO.getKeyword() != null && !searchDTO.getKeyword().isEmpty()) {
            queryWrapper.like("title", searchDTO.getKeyword())
                    .or()
                    .like("body", searchDTO.getKeyword());
        }

        if (searchDTO.getCategory() != null && !searchDTO.getCategory().isEmpty()) {
            queryWrapper.eq("category", searchDTO.getCategory());
        }

        if (searchDTO.getTags() != null && !searchDTO.getTags().isEmpty()) {
            for (String tag : searchDTO.getTags()) {
                queryWrapper.like("tags", tag);
            }
        }

        if (searchDTO.getSortBy() != null && !searchDTO.getSortBy().isEmpty()) {
            queryWrapper.orderBy(true, searchDTO.getIsAsc(), searchDTO.getSortBy());
        }

        Page<Content> contentPage = new Page<>(searchDTO.getPage() == null ? 1 : searchDTO.getPage(),
                searchDTO.getSize() == null ? 10 : searchDTO.getSize());
        Page<Content> resultPage = contentMapper.selectPage(contentPage, queryWrapper);

        return resultPage.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    private ContentVO convertToVO(Content content) {
        ContentVO vo = new ContentVO();
        vo.setId(content.getId());
        vo.setUserId(content.getUserId());
        vo.setTitle(content.getTitle());
        vo.setBody(content.getBody());
        vo.setFormat(content.getFormat());
        vo.setTagsFromString(content.getTags());
        vo.setCategory(content.getCategory());
        vo.setViews(content.getViews());

        if (content.getCreatedAt() != null) {
            vo.setCreatedAt(content.getCreatedAt().format(FORMATTER));
        }
        if (content.getUpdatedAt() != null) {
            vo.setUpdatedAt(content.getUpdatedAt().format(FORMATTER));
        }

        return vo;
    }
}