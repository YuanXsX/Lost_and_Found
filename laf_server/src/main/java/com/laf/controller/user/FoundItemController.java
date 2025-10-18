package com.laf.controller.user;

import com.laf.service.FoundItemService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/laf/user/foundItem")
@Slf4j
@Api(tags = "招领接口")
public class FoundItemController {
    @Autowired
    private FoundItemService foundItemService;

    @PostMapping("/report")
    public String reportFoundItem() {
        log.info("招领物品信息上报");
        foundItemService.reportFoundItem();
        return "招领物品信息上报成功";
    }
}
