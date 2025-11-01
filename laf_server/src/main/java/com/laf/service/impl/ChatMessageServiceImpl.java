package com.laf.service.impl;

import com.laf.dto.ChatMessageDTO;
import com.laf.entity.ChatMessage;
import com.laf.mapper.ChatMessageMapper;
import com.laf.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Override
    public void saveMessage(ChatMessageDTO msg) {
        ChatMessage m = new ChatMessage();
        m.setFromId(msg.getFromId());
        m.setToId(msg.getToId());
        m.setContent(msg.getContent());
        m.setTimestamp(msg.getTimestamp() == null ? LocalDateTime.now() : msg.getTimestamp());
        m.setStatus(0);
        chatMessageMapper.insert(m);
    }


    @Override
    public void pushUndeliveredToUser(Long userId) {
        List<ChatMessage> List = chatMessageMapper.findUndeliveredByToId(userId);
        for (ChatMessage m : List) {
            ChatMessageDTO dto = new ChatMessageDTO();
            dto.setFromId(m.getFromId());
            dto.setToId(m.getToId());
            dto.setContent(m.getContent());
            dto.setTimestamp(m.getTimestamp());
            chatMessageMapper.markDelivered(m.getId());
        }
    }


}
