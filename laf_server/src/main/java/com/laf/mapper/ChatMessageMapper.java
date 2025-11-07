package com.laf.mapper;

import com.laf.entity.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChatMessageMapper {

    /**
     * 插入聊天消息
     * @param m
     */
    @Insert("INSERT INTO chat_message (from_id, to_id, content, timestamp, status) " +
            "VALUES (#{fromId}, #{toId}, #{content}, #{timestamp}, #{status})")
    void insert(ChatMessage m);

    /**
     * 查找未送达的消息
     * @param userId
     * @return
     */
    @Select("SELECT * FROM chat_message WHERE to_id = #{userId} AND status = 0")
    List<ChatMessage> findUndeliveredByToId(Long userId);

    /**
     * 标记消息为已送达
     * @param id
     */
    @Insert("UPDATE chat_message SET status = 1 WHERE id = #{id}")
    void markDelivered(Long id);
}
