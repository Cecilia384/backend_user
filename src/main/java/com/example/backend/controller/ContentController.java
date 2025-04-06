package com.example.backend.controller;

import com.example.backend.dto.ContentDTO;
import com.example.backend.service.ContentService;
import com.example.backend.vo.ContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody ContentDTO dto) {
        contentService.createContent(dto);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "内容创建成功");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody ContentDTO dto) {
        contentService.updateContent(id, dto);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "内容更新成功");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        contentService.deleteContent(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "内容删除成功");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentVO> getById(@PathVariable Integer id) {
        ContentVO content = contentService.getContentById(id);
        return ResponseEntity.ok(content);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ContentVO>> list(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        List<ContentVO> contents = contentService.listContents(category, tag, page, size);
        return ResponseEntity.ok(contents);
    }
}
