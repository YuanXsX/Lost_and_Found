package com.laf.controller.user;

import com.laf.entity.FoundItem;
import com.laf.result.Result;
import com.laf.service.FoundItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 招领信息控制器
 */
@Slf4j
@RestController
@RequestMapping("/found")
public class FoundItemController {

    @Autowired
    private FoundItemService foundItemService;

    /**
     * 健康检查接口
     * GET http://localhost:8080/found/health
     */
    @GetMapping("/health")
    public String health() {
        return "招领服务健康运行！时间：" + LocalDateTime.now();
    }

    /**
     * 创建招领信息
     * POST http://localhost:8080/found
     */
    @PostMapping
    public Result<Long> createFoundItem(@RequestBody FoundItem foundItem) {
        log.info("创建招领信息: {}", foundItem.getItemName());
        try {
            Long id = foundItemService.createFoundItem(foundItem);
            log.info("创建成功，ID: {}", id);
            return Result.success(id);
        } catch (Exception e) {
            log.error("创建招领信息失败", e);
            return Result.error("创建失败: " + e.getMessage());
        }
    }
}