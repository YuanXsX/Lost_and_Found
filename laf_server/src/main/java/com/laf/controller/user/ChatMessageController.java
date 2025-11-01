package com.laf.controller.user;

import com.laf.dto.ChatMessageDTO;
import com.laf.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/chat/private")
    public void handlePrivateMessage(ChatMessageDTO msg) {
        // 持久化（存入 DB，用于离线）
        chatMessageService.saveMessage(msg);
        // 发送给目标用户，用户会收到 /user/queue/messages
        String user = String.valueOf(msg.getToId());
        messagingTemplate.convertAndSendToUser(user, "/queue/messages", msg);
    }
}
