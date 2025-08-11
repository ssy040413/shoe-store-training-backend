package com.xys.controller;

import com.xys.common.service.UploadService;
import com.xys.util.ResponseResult; // 确保导入路径正确
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/upload")
@Api(tags = "文件上传模块")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/image")
    @ApiOperation("上传图片")
    public ResponseResult<String> uploadImage(
            @RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = uploadService.uploadImage(file);
            if (imageUrl != null) {
                // 上传成功，返回包含图片URL的成功响应
                return ResponseResult.success(imageUrl);
            } else {
                return ResponseResult.failure("文件上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.failure("文件上传时发生IO异常: " + e.getMessage());
        }
    }
}