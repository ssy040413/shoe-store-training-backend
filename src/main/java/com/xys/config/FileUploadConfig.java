package com.xys.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * 文件上传配置类
 */
@Configuration
public class FileUploadConfig implements WebMvcConfigurer {

    /**
     * 配置文件上传参数
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        
        // 设置单个文件最大大小 (5MB)
        factory.setMaxFileSize(DataSize.ofMegabytes(5));
        
        // 设置总上传数据最大大小 (50MB)
        factory.setMaxRequestSize(DataSize.ofMegabytes(50));
        
        return factory.createMultipartConfig();
    }

    /**
     * 配置静态资源映射
     * 将 /uploads/** 路径映射到 uploads/ 目录
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:C:/Users/31314/Desktop/Project/test/uploads/");
    }
}