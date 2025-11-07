package com.laf.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Slf4j
@Configuration
public class FileWebConfig implements WebMvcConfigurer {

    @Value("${upload.dir:./src/main/resources/static/uploads/}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            // 获取绝对路径
            File uploadDirFile = new File(uploadDir);
            String absolutePath = uploadDirFile.getAbsolutePath();


            // 确保路径格式正确
            String location = "file:" + absolutePath;
            if (!absolutePath.endsWith(File.separator)) {
                location += File.separator;
            }

            log.info("配置静态资源映射:");
            log.info("URL模式: /uploads/**");
            log.info("物理路径: {}", location);
            log.info("目录是否存在: {}", uploadDirFile.exists());
            log.info("目录可读: {}", uploadDirFile.canRead());

            // 添加静态资源映射
            registry.addResourceHandler("/uploads/**")
                    .addResourceLocations(location);

        } catch (Exception e) {
            log.error("静态资源映射配置失败", e);
        }
    }
}