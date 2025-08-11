package com.xys.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传工具类
 */
public class FileUploadUtil {
    
    // 上传文件的根目录
    private static final String UPLOAD_ROOT_PATH = "C:/Users/31314/Desktop/Project/test/uploads";
    
    // 图片文件的子目录
    private static final String IMAGE_PATH = "images";
    
    // 允许的图片文件类型
    private static final String[] ALLOWED_IMAGE_TYPES = {
        "image/jpeg", "image/jpg", "image/png", "image/gif", "image/webp"
    };
    
    // 最大文件大小 (5MB)
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    
    /**
     * 上传图片文件
     * @param file 上传的文件
     * @return 文件访问路径
     * @throws IOException 上传失败异常
     */
    public static String uploadImage(MultipartFile file) throws IOException {
        // 验证文件
        validateImageFile(file);
        
        // 生成文件名
        String fileName = generateFileName(file.getOriginalFilename());
        
        // 创建目录结构：uploads/images/yyyy/MM/dd/
        String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String relativePath = IMAGE_PATH + "/" + datePath;
        String fullPath = UPLOAD_ROOT_PATH + "/" + relativePath;
        
        // 创建目录
        Path uploadPath = Paths.get(fullPath);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // 保存文件
        String fullFileName = fullPath + "/" + fileName;
        Path filePath = Paths.get(fullFileName);
        file.transferTo(filePath.toFile());
        
        // 返回相对路径用于数据库存储，包含/uploads前缀以匹配静态资源映射
        return "/uploads/" + relativePath + "/" + fileName;
    }
    
    /**
     * 验证图片文件
     * @param file 上传的文件
     * @throws IOException 验证失败异常
     */
    private static void validateImageFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IOException("文件不能为空");
        }
        
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IOException("文件大小不能超过5MB");
        }
        
        String contentType = file.getContentType();
        boolean isValidType = false;
        for (String allowedType : ALLOWED_IMAGE_TYPES) {
            if (allowedType.equals(contentType)) {
                isValidType = true;
                break;
            }
        }
        
        if (!isValidType) {
            throw new IOException("不支持的文件类型，只支持 JPEG、PNG、GIF、WebP 格式");
        }
    }
    
    /**
     * 生成唯一文件名
     * @param originalFilename 原始文件名
     * @return 新的文件名
     */
    private static String generateFileName(String originalFilename) {
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return UUID.randomUUID().toString().replace("-", "") + extension;
    }
    
    /**
     * 删除文件
     * @param filePath 文件路径
     * @return 是否删除成功
     */
    public static boolean deleteFile(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            return false;
        }
        
        try {
            // 移除开头的斜杠和uploads前缀
            if (filePath.startsWith("/")) {
                filePath = filePath.substring(1);
            }
            if (filePath.startsWith("uploads/")) {
                filePath = filePath.substring("uploads/".length());
            }
            
            String fullPath = UPLOAD_ROOT_PATH + "/" + filePath;
            Path path = Paths.get(fullPath);
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 获取文件的完整路径
     * @param relativePath 相对路径
     * @return 完整路径
     */
    public static String getFullPath(String relativePath) {
        if (relativePath == null || relativePath.trim().isEmpty()) {
            return null;
        }
        
        // 移除开头的斜杠和uploads前缀
        if (relativePath.startsWith("/")) {
            relativePath = relativePath.substring(1);
        }
        if (relativePath.startsWith("uploads/")) {
            relativePath = relativePath.substring("uploads/".length());
        }
        
        return UPLOAD_ROOT_PATH + "/" + relativePath;
    }
}