package com.laf.service.impl;

import com.laf.mapper.UserMapper;
import com.laf.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class AvatarServiceImpl implements AvatarService {

    @Value("${laf.avatar.storage-path:uploads/avatars}")
    private String storagePath;

    @Value("${laf.avatar.base-url:http://localhost:8080}")
    private String baseUrl;

    @Autowired(required = false)
    private UserMapper userMapper; // 可选：用于将 avatarUrl 写回用户表

    @Override
    public String store(Long userId, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("文件为空");
        }

        // 创建目录
        Path dir = Paths.get(storagePath).toAbsolutePath().normalize();
        Files.createDirectories(dir);

        // 生成唯一文件名并保留扩展名
        String original = file.getOriginalFilename();
        String ext = "";
        if (original != null && original.contains(".")) {
            ext = original.substring(original.lastIndexOf('.'));
        }
        String filename = UUID.randomUUID().toString() + ext;
        Path target = dir.resolve(filename);

        // 保存文件（覆盖同名文件）
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        // 构造对外 URL（假设静态资源由 Web 服务器或 Spring 映射到 /uploads/avatars/**）
        String publicUrl = baseUrl.endsWith("/") ? baseUrl + "uploads/avatars/" + filename
                : baseUrl + "/uploads/avatars/" + filename;

        // 可选：将 avatarUrl 写回用户表
        if (userMapper != null && userId != null) {
            try {
                userMapper.updateAvatarUrl(userId, publicUrl);
            } catch (Exception ignored) { }
        }

        return publicUrl;
    }
}
