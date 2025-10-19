package com.laf.controller.user;

import com.laf.service.AvatarService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/laf/user/avatar")
@Slf4j
@Api(tags = "用户头像上传接口")
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    @PostMapping("/{userId}/uploadAvatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam Long userId, @RequestParam MultipartFile file) throws Exception {
        String url = avatarService.store(userId, file);
        return ResponseEntity.ok(url);
    }
}
