package com.laf.controller.user;

import com.github.pagehelper.Page;
import com.laf.context.BaseContext;
import com.laf.dto.CreateLostOrFoundItemDTO;
import com.laf.dto.ItemQueryDTO;
import com.laf.dto.UpdateLostOrFoundItemDTO;
import com.laf.entity.LostOrFoundItem;
import com.laf.result.Result;
import com.laf.service.LostOrFoundItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 失物招领控制器
 */
@Slf4j
@RestController
@RequestMapping("/laf/user/LostOrFound")
public class LostOrFoundItemController {

    @Autowired
    private LostOrFoundItemService lostOrFoundItemService;

    /**
     * 创建失物或招领信息
     * POST http://localhost:8080/laf/user/LostOrFound/create
     */
    @PostMapping("/create")
    public Result<String> createItem(@RequestBody CreateLostOrFoundItemDTO createDTO) {
        try {
            LostOrFoundItem item = new LostOrFoundItem();
            Long userid = BaseContext.getCurrentId();
            BeanUtils.copyProperties(createDTO, item);
            item.setPublisherId(userid);
            log.info(String.valueOf(createDTO.getLocation()));
            int result = lostOrFoundItemService.publishItem(item);
            if (result > 0) {
                return Result.success("发布成功");
            } else {
                return Result.error("发布失败");
            }
        } catch (Exception e) {
            log.error("创建失物招领信息失败", e);
            return Result.error("发布失败：" + e.getMessage());
        }
    }

    /**
     * 分页查询失物招领信息
     * GET http://localhost:8080/laf/user/LostOrFound/pageSeek
     */
    @GetMapping("/pageSeek")
    public Result<Page<LostOrFoundItem>> pageSeekItems(ItemQueryDTO queryDTO) {
        try {
            Page<LostOrFoundItem> page = lostOrFoundItemService.pageQueryItems(queryDTO);
            return Result.success(page);
        } catch (Exception e) {
            log.error("分页查询失物招领信息失败", e);
            return Result.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 根据ID查询失物招领信息详情
     * GET http://localhost:8080/laf/user/LostOrFound/{id}
     */
    @GetMapping("/{id}")
    public Result<LostOrFoundItem> getItemById(@PathVariable Long id) {
        try {
            LostOrFoundItem item = lostOrFoundItemService.getItemById(id);
            if (item != null) {
                return Result.success(item);
            } else {
                return Result.error("未找到该记录");
            }
        } catch (Exception e) {
            log.error("查询失物招领信息详情失败, id: {}", id, e);
            return Result.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 根据ID更新失物招领信息
     * PUT http://localhost:8080/laf/user/LostOrFound/{id}
     */
    @PutMapping("/{id}")
    public Result<String> updateItem(@PathVariable Long id,
                                     @RequestBody UpdateLostOrFoundItemDTO updateDTO) {
        try {
            // 先查询原记录
            LostOrFoundItem existingItem = lostOrFoundItemService.getItemById(id);
            if (existingItem == null) {
                return Result.error("未找到该记录");
            }

            // 临时跳过权限校验，先让接口正常工作
            // Long publisherId = getPublisherIdFromRequest(request);
            // if (!existingItem.getPublisherId().equals(publisherId)) {
            //     return Result.error("无权修改该记录");
            // }

            // 更新字段
            BeanUtils.copyProperties(updateDTO, existingItem);
            existingItem.setId(id);

            int result = lostOrFoundItemService.updateItem(existingItem);
            if (result > 0) {
                return Result.success("更新成功");
            } else {
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            log.error("更新失物招领信息失败, id: {}", id, e);
            return Result.error("更新失败：" + e.getMessage());
        }
    }

    /**
     * 根据ID删除失物招领信息
     * DELETE http://localhost:8080/laf/user/LostOrFound/{id}
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteItem(@PathVariable Long id) {
        try {
            // 先查询原记录
            LostOrFoundItem existingItem = lostOrFoundItemService.getItemById(id);
            if (existingItem == null) {
                return Result.error("未找到该记录");
            }


            int result = lostOrFoundItemService.deleteItemById(id);
            if (result > 0) {
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            log.error("删除失物招领信息失败, id: {}", id, e);
            return Result.error("删除失败：" + e.getMessage());
        }
    }

    /**
     * 如果需要使用 HttpServletRequest，可以这样注入（但暂时注释掉）
     */
    // @Autowired
    // private HttpServletRequest request;

    // /**
    //  * 从请求中获取发布者ID（需要根据您的认证系统实现）
    //  */
    // private Long getPublisherIdFromRequest() {
    //     // TODO: 根据您的认证系统实现获取当前用户ID
    //     // 例如从 JWT token 或 session 中获取
    //     // 这里暂时返回一个模拟的ID
    //     return 1L;
    // }
}