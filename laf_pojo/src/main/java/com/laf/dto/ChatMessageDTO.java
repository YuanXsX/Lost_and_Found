package com.laf.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天消息数据传输对象
 */
@Data
public class ChatMessageDTO implements Serializable {
    private Long fromId;
    private Long toId;
    private String content;
    private LocalDateTime timestamp;
}