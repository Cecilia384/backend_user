package com.example.backend.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.dto.ContentDTO;
import com.example.backend.service.ContentManageService;
import com.example.backend.vo.admin.ContentDetailVO;
import com.example.backend.vo.admin.ContentListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/content")
public class ContentManageController {

    private final ContentManageService contentManageService;

    @Autowired
    public ContentManageController(ContentManageService contentManageService) {
        this.contentManageService = contentManageService;
    }

    // 添加内容接口
    @PostMapping("/create")
    public void addContent(@RequestBody ContentDTO dto, @RequestParam Integer userId) {
        contentManageService.addContent(dto, userId);
    }

    // 获取内容详情接口
    @GetMapping("/{id:\\d+}")
    public ContentDetailVO getContentDetail(@PathVariable Integer id) {
        return contentManageService.getContentDetail(id);
    }

    // 删除内容接口
    @DeleteMapping("/{id:\\d+}")
    public void deleteContent(@PathVariable Integer id) {
        contentManageService.deleteContent(id);
    }

    // 批量删除内容接口
    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> ids) {
        contentManageService.batchDelete(ids);
    }

    // 获取所有文章（分页）
    @GetMapping("/all")
    public Page<ContentListVO> listAllContents(@RequestParam int page, @RequestParam int size) {
        Page<ContentListVO> pageRequest = new Page<>(page, size);
        return contentManageService.listAllContents(pageRequest);
    }
}