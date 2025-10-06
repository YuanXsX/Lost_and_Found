package com.laf.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 聊天消息数据传输对象
 */
@Data
public class ChatMessageDTO {
    private Long claimId;
    private Long senderId;
    private String content;
    private LocalDateTime sendTime;
}