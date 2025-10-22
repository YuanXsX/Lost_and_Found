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
import java.util.List;

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

    /**
     * 完全绕过认证的测试删除接口
     * 访问示例: http://localhost:8080/laf/user/lost/no-auth-delete/1
     */
    @GetMapping("/lost/no-auth-delete/{id}")
    public Result<String> noAuthDeleteLostItem(@PathVariable Long id) {
        log.info("=== 绕过认证测试删除接口 ===");
        log.info("删除失物信息，ID: {}", id);

        try {
            // 直接调用删除方法，不进行任何认证检查
            boolean success = lostItemService.deleteLostItem(id);

            if (success) {
                log.info("✅ 删除成功，ID: {}", id);
                return Result.success("删除成功！被删除的ID: " + id);
            } else {
                log.warn("❌ 删除失败，ID: {} 可能不存在", id);
                return Result.error("删除失败，信息不存在，ID: " + id);
            }
        } catch (Exception e) {
            log.error("💥 删除异常", e);
            return Result.error("删除失败: " + e.getMessage());
        }
    }

    /**
     * 修改失物信息 - GET 方法（测试用）
     * GET http://localhost:8080/laf/user/lost/update-get?id=1&itemName=新名称&description=新描述
     * 或者 GET http://localhost:8080/laf/user/lost/update-get?id=1&itemName=新名称&description=新描述&LostLocation=新地点
     */
    @GetMapping("/lost/update-get")
    public Result<String> updateLostItemGet(
            @RequestParam Long id,
            @RequestParam String itemName,
            @RequestParam String description,
            @RequestParam(required = false) String LostLocation,  // 注意：这里是大写L，与实体类字段一致
            @RequestParam(required = false) String imageUrl,
            @RequestParam(required = false) String itemType) {

        log.info("GET方式修改失物信息，ID: {}", id);

        LostItem lostItem = new LostItem();
        lostItem.setId(id);
        lostItem.setItemName(itemName);
        lostItem.setDescription(description);

        // 只有提供了参数才设置值
        if (LostLocation != null) {
            lostItem.setLostLocation(LostLocation);  // 注意：这里是大写L
        }
        if (imageUrl != null) {
            lostItem.setImageUrl(imageUrl);
        } else {
            lostItem.setImageUrl("/images/updated.jpg");  // 默认值
        }
        if (itemType != null) {
            lostItem.setItemType(itemType);
        } else {
            lostItem.setItemType("其他");  // 默认值
        }

        try {
            boolean success = lostItemService.updateLostItem(lostItem);
            if (success) {
                return Result.success("GET修改失物信息成功！ID: " + id);
            } else {
                return Result.error("GET修改失败，信息不存在");
            }
        } catch (Exception e) {
            return Result.error("GET修改失败: " + e.getMessage());
        }
    }

    /**
     * 根据ID查询单个失物信息
     * GET http://localhost:8080/laf/user/lost/get-by-id/1
     */
    @GetMapping("/lost/get-by-id/{id}")
    public Result<LostItem> getLostItemById(@PathVariable Long id) {
        log.info("根据ID查询失物信息，ID: {}", id);
        try {
            LostItem lostItem = lostItemService.getLostItemById(id);
            if (lostItem != null) {
                return Result.success(lostItem);
            } else {
                return Result.error("失物信息不存在，ID: " + id);
            }
        } catch (Exception e) {
            log.error("查询失物信息异常", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询所有失物信息
     * GET http://localhost:8080/laf/user/lost/get-all
     */
    @GetMapping("/lost/get-all")
    public Result<List<LostItem>> getAllLostItems() {
        log.info("查询所有失物信息");
        try {
            List<LostItem> lostItems = lostItemService.getAllLostItems();
            return Result.success(lostItems);
        } catch (Exception e) {
            log.error("查询所有失物信息异常", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 根据物品名称模糊查询失物信息
     * GET http://localhost:8080/laf/user/lost/get-by-name?itemName=手机
     */
    @GetMapping("/lost/get-by-name")
    public Result<List<LostItem>> getLostItemsByName(@RequestParam String itemName) {
        log.info("根据名称查询失物信息，名称: {}", itemName);
        try {
            List<LostItem> lostItems = lostItemService.getLostItemsByName(itemName);
            return Result.success(lostItems);
        } catch (Exception e) {
            log.error("根据名称查询失物信息异常", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 根据地点查询失物信息
     * GET http://localhost:8080/laf/user/lost/get-by-location?lostLocation=图书馆
     *
     */
    @GetMapping("/lost/get-by-location")
    public Result<List<LostItem>> getLostItemsByLocation(@RequestParam String lostLocation) {
        log.info("根据地点查询失物信息，地点: {}", lostLocation);
        try {
            List<LostItem> lostItems = lostItemService.getLostItemsByLocation(lostLocation);
            return Result.success(lostItems);
        } catch (Exception e) {
            log.error("根据地点查询失物信息异常", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }



}