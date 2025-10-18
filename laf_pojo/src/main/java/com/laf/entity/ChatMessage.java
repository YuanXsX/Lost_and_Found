package com.laf.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天消息实体类
 */
@Data
public class ChatMessage implements Serializable {
    private Long claimId;
    private Long senderId;
    private String content;
    private LocalDateTime sendTime;
    private Boolean isRead;
}
