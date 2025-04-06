package com.example.backend.service.impl;

import com.example.backend.dto.ContentDTO;
import com.example.backend.entity.Content;
import com.example.backend.mapper.ContentMapper;
import com.example.backend.service.ContentService;
import com.example.backend.vo.ContentVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        content.setCategory(dto.getCategory()); // 设置分类
        content.setViews(0);
        content.setCreatedAt(LocalDateTime.now());
        content.setUpdatedAt(LocalDateTime.now());

        contentMapper.insert(content);
    }

    @Override
    public void updateContent(Integer id, ContentDTO dto) { // 修改为Integer
        Content content = contentMapper.selectById(id);
        if (content == null) {
            throw new RuntimeException("内容不存在");
        }

        content.setTitle(dto.getTitle());
        content.setBody(dto.getContent());
        content.setFormat(dto.getFormat().name());
        content.setTags(String.join(",", dto.getTags()));
        content.setCategory(dto.getCategory()); // 更新分类
        content.setUpdatedAt(LocalDateTime.now());

        contentMapper.updateById(content);
    }

    @Override
    public void deleteContent(Integer id) { // 修改为Integer
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
        // 构建查询条件
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();

        if (category != null && !category.isEmpty()) {
            queryWrapper.eq("category", category);
        }

        if (tag != null && !tag.isEmpty()) {
            queryWrapper.like("tags", tag);
        }

        // 分页查询
        Page<Content> contentPage = new Page<>(page == null ? 1 : page, size == null ? 10 : size);
        Page<Content> resultPage = contentMapper.selectPage(contentPage, queryWrapper);

        // 转换为VO
        return resultPage.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    // 实体转VO方法
    private ContentVO convertToVO(Content content) {
        ContentVO vo = new ContentVO();
        vo.setId(content.getId());
        vo.setUserId(content.getUserId());
        vo.setTitle(content.getTitle());
        vo.setBody(content.getBody());
        vo.setFormat(content.getFormat());
        vo.setTagsFromString(content.getTags()); // 转换标签
        vo.setCategory(content.getCategory());
        vo.setViews(content.getViews());

        // 格式化时间
        if (content.getCreatedAt() != null) {
            vo.setCreatedAt(content.getCreatedAt().format(FORMATTER));
        }
        if (content.getUpdatedAt() != null) {
            vo.setUpdatedAt(content.getUpdatedAt().format(FORMATTER));
        }

        // TODO: 关联查询作者信息
        // vo.setAuthorName(getUsernameById(content.getUserId()));

        return vo;
    }
}
