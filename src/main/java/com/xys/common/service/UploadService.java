package com.xys.common.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface UploadService {
    /**
     * 上传图片文件
     * @param image MultipartFile 格式的图片文件
     * @return 上传成功后，返回可供前端访问的图片URL；失败则返回null
     * @throws IOException
     */
    String uploadImage(MultipartFile image) throws IOException;
}