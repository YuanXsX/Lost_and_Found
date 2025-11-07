package com.laf.controller.user;

import com.laf.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/laf/user/LostOrFound")
public class UploadController {

    @Value("${upload.dir:./src/main/resources/static/uploads/}")
    private String uploadDir;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)  // 修正这里
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        log.info("上传请求, fileName={}", file == null ? null : file.getOriginalFilename());
        log.info("上传目录: {}", uploadDir);

        if (file == null || file.isEmpty()) {
            return Result.error("文件为空");
        }

        try {
            // 规范化路径，移除开头的 ./
            String normalizedDir = uploadDir.replaceFirst("^\\./", "");
            Path uploadPath = Paths.get(normalizedDir).toAbsolutePath();

            log.info("规范化路径: {}", uploadPath);

            // 创建目录
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                log.info("创建目录成功: {}", uploadPath);
            }

            // 检查目录是否可写
            File testFile = new File(uploadPath.toFile(), "test.tmp");
            try {
                testFile.createNewFile();
                testFile.delete();
                log.info("目录可写性测试通过");
            } catch (IOException e) {
                log.error("目录不可写: {}", e.getMessage());
                return Result.error("上传目录没有写入权限");
            }

            String originalFilename = file.getOriginalFilename();
            String ext = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                ext = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            }

            // 验证文件类型
            if (!isAllowedFileType(ext)) {
                return Result.error("不支持的文件类型: " + ext);
            }

            String uniqueName = UUID.randomUUID().toString().replace("-", "") + ext;
            Path targetPath = uploadPath.resolve(uniqueName);

            log.info("目标文件路径: {}", targetPath);

            // 保存文件
            file.transferTo(targetPath);

            String fileUrl = "/uploads/" + uniqueName;
            log.info("文件保存成功: {}, 访问URL: {}", targetPath, fileUrl);
            return Result.success(fileUrl);

        } catch (IOException e) {
            log.error("文件保存失败", e);
            return Result.error("文件保存失败：" + e.getMessage());
        } catch (Exception e) {
            log.error("上传异常", e);
            return Result.error("上传失败：" + e.getMessage());
        }
    }

    private boolean isAllowedFileType(String ext) {
        return ext.matches("\\.(jpg|jpeg|png|gif|bmp|webp)$");
    }
}