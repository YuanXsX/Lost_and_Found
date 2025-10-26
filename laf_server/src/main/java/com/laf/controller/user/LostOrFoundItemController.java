package com.laf.controller.user;

import com.laf.service.LostOrFoundItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/laf/user/lostorfounditem")
public class LostOrFoundItemController {
    @Autowired
    private LostOrFoundItemService lostOrFoundItemService;

    /**
     * 健康检查接口
     *
     */
    @GetMapping("/health")
    public String health() {
        return "失物服务健康运行！时间：" + LocalDateTime.now();
    }


}
