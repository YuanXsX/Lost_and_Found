package com.laf.service;

import com.laf.dto.ChatMessageDTO;

public interface ChatMessageService {
    /**
     * 保存聊天消息
     */
    void saveMessage(ChatMessageDTO msg);

    /**
     * 推送未送达消息给用户
     */
    void pushUndeliveredToUser(Long userId);
}
