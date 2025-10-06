package com.laf.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 聊天消息实体类
 */
@Data
public class ChatMessage {
    private Long id;
    private Long claimId;
    private Long senderId;
    private String content;
    private LocalDateTime sendTime;
    private Boolean isRead;
}
