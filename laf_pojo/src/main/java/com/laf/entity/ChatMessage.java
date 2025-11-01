package com.laf.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天消息实体类
 */
@Data
public class ChatMessage implements Serializable {
    private Long id;
    private Long fromId;
    private Long toId;
    private String content;
    private LocalDateTime timestamp;
    private Integer status; // 0 未读 1 已读
}
