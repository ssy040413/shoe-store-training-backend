package com.xys.common.service.impl;

import com.xys.common.service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    // 依然从配置文件注入物理存储路径
    @Value("${spring.web.resources.static-locations[4]}")
    private String physicalPathConfig;

    // 【关键修改】移除了 @Value("${spring.mvc.static-path-pattern}") private String virtualPathPattern;

    @Override
    public String uploadImage(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) {
            throw new IOException("上传的文件不能为空");
        }
        String originalFilename = image.getOriginalFilename();
        if (originalFilename == null || originalFilename.trim().isEmpty()) {
            throw new IOException("上传的文件名不能为空");
        }

        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        String uploadPath = physicalPathConfig.replace("file:", "").trim();
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File dest = new File(uploadDir, uniqueFileName);
        image.transferTo(dest);

        // 【关键修改】直接返回以'/'开头的根路径文件名
        return "/" + uniqueFileName; // 例如: "/xxxxxxxx-xxxx.jpg"
    }
}