package com.laf.controller.user;

import com.laf.context.BaseContext;
import com.laf.dto.FoundItemDTO;
import com.laf.entity.FoundItem;
import com.laf.result.Result;
import com.laf.service.FoundItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDateTime;

/**
 * 招领信息控制器
 */
@Slf4j
@RestController
@RequestMapping("/laf/user")
public class FoundItemController {

    @Autowired
    private FoundItemService foundItemService;

    /**
     * 健康检查接口
     * GET http://localhost:8080/found/health
     */
    @GetMapping("/found/health")
    public String health() {
        return "招领服务健康运行！时间：" + LocalDateTime.now();
    }

    /**
     * 创建招领信息
     *
     */
    @PostMapping("/{userid}/found/create")
    public Result<Long> createFoundItem(FoundItemDTO foundItemDTO) {
        log.info("创建招领信息: {}", foundItemDTO.getItemName());
        Long userid = BaseContext.getCurrentId();
        foundItemDTO.setPublisherId(userid);
        try {
            Long id = foundItemService.createFoundItem(foundItemDTO);
            log.info("创建成功，ID: {}", id);
            return Result.success(id);
        } catch (Exception e) {
            log.error("创建招领信息失败", e);
            return Result.error("创建失败: " + e.getMessage());
        }
    }


    /**
     * 完全绕过认证的测试删除接口
     * 访问示例: http://localhost:8080/laf/user/found/no-auth-delete/1
     */
    @GetMapping("/found/no-auth-delete/{id}")
    public Result<String> noAuthDeleteFoundItem(@PathVariable Long id) {
        log.info("=== 绕过认证测试删除接口 ===");
        log.info("删除招领信息，ID: {}", id);

        try {
            // 直接调用删除方法，不进行任何认证检查
            boolean success = foundItemService.deleteFoundItem(id);

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
     * 修改招领信息 - GET 方法（测试用）
     * GET http://localhost:8080/laf/user/found/update-get?id=1&itemName=新名称&description=新描述
     * 或者 GET http://localhost:8080/laf/user/found/update-get?id=1&itemName=新名称&description=新描述&foundLocation=新地点
     */
    @GetMapping("/found/update-get")
    public Result<String> updateFoundItemGet(

            @RequestParam Long id,
            @RequestParam String itemName,
            @RequestParam String description,
            @RequestParam(required = false) String foundLocation,  // 改为可选参数
            @RequestParam(required = false) String imageUrl,
            @RequestParam(required = false) String itemType) {

        log.info("GET方式修改招领信息，ID: {}", id);

        FoundItem foundItem = new FoundItem();
        foundItem.setId(id);
        foundItem.setItemName(itemName);
        foundItem.setDescription(description);

        // 只有提供了参数才设置值
        if (foundLocation != null) {
            foundItem.setFoundLocation(foundLocation);
        }
        if (imageUrl != null) {
            foundItem.setImageUrl(imageUrl);
        } else {
            foundItem.setImageUrl("/images/updated.jpg");  // 默认值
        }
        if (itemType != null) {
            foundItem.setItemType(itemType);
        } else {
            foundItem.setItemType("其他");  // 默认值
        }

        try {
            boolean success = foundItemService.updateFoundItem(foundItem);
            if (success) {
                return Result.success("GET修改招领信息成功！ID: " + id);
            } else {
                return Result.error("GET修改失败，信息不存在");
            }
        } catch (Exception e) {
            return Result.error("GET修改失败: " + e.getMessage());
        }
    }

    /**
     * 根据ID查询单个招领信息
     * GET http://localhost:8080/laf/user/found/get-by-id/1
     */
    @GetMapping("/found/get-by-id/{id}")
    public Result<FoundItem> getFoundItemById(@PathVariable Long id) {
        log.info("根据ID查询招领信息，ID: {}", id);
        try {
            FoundItem foundItem = foundItemService.getFoundItemById(id);
            if (foundItem != null) {
                return Result.success(foundItem);
            } else {
                return Result.error("招领信息不存在，ID: " + id);
            }
        } catch (Exception e) {
            log.error("查询招领信息异常", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询所有招领信息
     * GET http://localhost:8080/laf/user/found/get-all
     */
    @GetMapping("/found/get-all")
    public Result<List<FoundItem>> getAllFoundItems() {
        log.info("查询所有招领信息");
        try {
            List<FoundItem> foundItems = foundItemService.getAllFoundItems();
            return Result.success(foundItems);
        } catch (Exception e) {
            log.error("查询所有招领信息异常", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 根据物品名称模糊查询招领信息
     * GET http://localhost:8080/laf/user/found/get-by-name?itemName=手机
     */
    @GetMapping("/found/get-by-name")
    public Result<List<FoundItem>> getFoundItemsByName(@RequestParam String itemName) {
        log.info("根据名称查询招领信息，名称: {}", itemName);
        try {
            List<FoundItem> foundItems = foundItemService.getFoundItemsByName(itemName);
            return Result.success(foundItems);
        } catch (Exception e) {
            log.error("根据名称查询招领信息异常", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 根据地点查询招领信息
     * GET http://localhost:8080/laf/user/found/get-by-location?foundLocation=图书馆
     */
    @GetMapping("/found/get-by-location")
    public Result<List<FoundItem>> getFoundItemsByLocation(@RequestParam String foundLocation) {
        log.info("根据地点查询招领信息，地点: {}", foundLocation);
        try {
            List<FoundItem> foundItems = foundItemService.getFoundItemsByLocation(foundLocation);
            return Result.success(foundItems);
        } catch (Exception e) {
            log.error("根据地点查询招领信息异常", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }
}
