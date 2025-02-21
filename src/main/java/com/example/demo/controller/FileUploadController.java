package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    //等价于
    // @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public  String up(String nickname, MultipartFile photo, HttpServletRequest request) throws IOException {
        System.out.println("nickname:"+nickname);
        System.out.println("photo:"+photo.getOriginalFilename()); //获取图片的原始名称

        System.out.println("type:"+photo.getContentType());//获取图片类型
        System.out.println("path"+System.getProperty("user.dir"));//获取当前项目的路径

        //获取上传路径
        String path = request.getServletContext().getRealPath("/upload");
        System.out.println(path);
        saveFile(photo,path);
        return "上传成功";
    }
    public void saveFile(MultipartFile photo,String path) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();//创建文件夹
        }
        File file=new File(path+photo.getOriginalFilename());
        photo.transferTo(file);//保存文件
    }
}
