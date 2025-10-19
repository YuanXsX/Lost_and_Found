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
     * 构造函数 - 用于验证控制器是否被创建
     */
    public FoundItemController() {
        log.info("🎉 FoundItemController 初始化成功！");
    }


}