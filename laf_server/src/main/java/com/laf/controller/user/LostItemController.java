package com.laf.controller.user;

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
@RequestMapping("/lost")
public class LostItemController {

    @Autowired
    private LostItemService lostItemService;

    public LostItemController() {
        log.info("🎉 LostItemController 初始化成功！");
    }


}