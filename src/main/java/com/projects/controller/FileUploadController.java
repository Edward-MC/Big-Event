package com.projects.controller;

import com.projects.pojo.Result;
import com.projects.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        // Store the file content into disk
        String originalFilename = file.getOriginalFilename();
        // Use UUID to keep the filename unique to avoid file overriding with the same name
        String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String filePath = System.getProperty("user.dir") + "/src/main/resources/images/" + filename;
        file.transferTo(new File(filePath));

        String url = AliOssUtil.uploadFile(filename, new FileInputStream(filePath));
        return Result.success(url);
    }
}
