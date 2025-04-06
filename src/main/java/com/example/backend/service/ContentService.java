package com.example.backend.service;

import com.example.backend.dto.ContentDTO;
import com.example.backend.vo.ContentVO;

import java.util.List;

public interface ContentService {
    void createContent(ContentDTO dto);
    void updateContent(Integer id, ContentDTO dto);  // 改为Integer
    void deleteContent(Integer id);                  // 改为Integer

    // 添加查询方法
    ContentVO getContentById(Integer id);
    List<ContentVO> listContents(String category, String tag, Integer page, Integer size);
}
