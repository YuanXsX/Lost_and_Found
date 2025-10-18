package com.laf.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {
    /**
     * 存储头像并返回可访问 URL
     */
    String store(Long userId, MultipartFile file) throws IOException;
}
