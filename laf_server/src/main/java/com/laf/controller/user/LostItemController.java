package com.laf.controller.user;

import com.laf.context.BaseContext;
import com.laf.dto.LostItemDTO;
import com.laf.entity.LostItem;
import com.laf.result.Result;
import com.laf.service.LostItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 失物信息控制器
 */
@Slf4j
@RestController
@RequestMapping("/laf/user")
public class LostItemController {

    @Autowired
    private LostItemService lostItemService;

    /**
     * 健康检查接口
     * GET http://localhost:8080/lost/health
     */
    @GetMapping("/lost/health")
    public String health() {
        return "失物服务健康运行！时间：" + LocalDateTime.now();
    }




    /**
     * 创建失物信息
     * POST http://localhost:8080/lost
     */

    @PostMapping("/lost/create")
    public Result<Long> createLostItem(LostItemDTO lostItemDTO) {
        log.info("创建失物信息: {}", lostItemDTO.getItemName());
        Long userId = BaseContext.getCurrentId();
        lostItemDTO.setPublisherId(userId);
        try {
            Long id = lostItemService.createLostItem(lostItemDTO);
            log.info("失物信息创建成功，ID: {}", id);
            return Result.success(id);
        } catch (Exception e) {
            log.error("创建失物信息失败", e);
            return Result.error("创建失败: " + e.getMessage());

        }
    }








}