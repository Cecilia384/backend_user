package com.example.backend.service;

import com.example.backend.dto.ContentDTO;
import com.example.backend.dto.ContentSearchDTO;
import com.example.backend.vo.ContentVO;

import java.util.List;

public interface ContentService {
    void createContent(ContentDTO dto);
    void updateContent(Integer id, ContentDTO dto);
    void deleteContent(Integer id);

    ContentVO getContentById(Integer id);
    List<ContentVO> listContents(String category, String tag, Integer page, Integer size);

    List<ContentVO> searchContents(ContentSearchDTO searchDTO); // 新增搜索方法
}