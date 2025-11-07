package com.laf.ws;

import com.alibaba.fastjson2.JSON;
import com.laf.constant.JwtClaimsConstant;
import com.laf.dto.ChatMessageDTO;
import com.laf.entity.ChatMessage;
import com.laf.mapper.ChatMessageMapper;
import com.laf.properties.JwtProperties;
import com.laf.utils.JwtUtil;
import com.laf.utils.MessageUtils;
import io.jsonwebtoken.Claims;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// 1. 修改路径以接收 token，并移除 configurator
@ServerEndpoint(value="/user/chat/{token}")
@Component
public class ChatEndpoint {

    private static ChatMessageMapper chatMessageMapper;
    private static JwtProperties jwtProperties;
    private static final Map<Long, Session> onlineUsers = new ConcurrentHashMap<>();

    // 2. 注入 JwtProperties 用于解析 token
    @Autowired
    public void setJwtProperties(JwtProperties jwtProperties) {
        ChatEndpoint.jwtProperties = jwtProperties;
    }

    @Autowired
    public void setChatMessageMapper(ChatMessageMapper chatMessageMapper) {
        ChatEndpoint.chatMessageMapper = chatMessageMapper;
    }

    /**
     * WebSocket 连接建立时调用
     */
    // 3. 修改 onOpen 方法签名，接收 token 路径参数
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        try {
            // 4. 解析 token 获取用户ID
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());

            // 5. 将 userId 和 session 存入在线用户列表
            onlineUsers.put(userId, session);
            // 将 userId 存入 session 的 userProperties，方便 onClose 时获取
            session.getUserProperties().put(JwtClaimsConstant.USER_ID, userId);

            // 广播消息，将登录的所有用户推送给所有用户
            String message = MessageUtils.getMessage(true, null, getOnlineUserIds());
            broadcastAllUsers(message);
        } catch (Exception e) {
            // Token 无效或解析失败，直接关闭连接
            try {
                session.close();
            } catch (Exception ioException) {
                // ignore
            }
        }
    }

    public Set<Long> getOnlineUserIds(){
        return onlineUsers.keySet();
    }

    private void broadcastAllUsers(String message) {
        try {
            // 遍历所有 session
            for (Session session : onlineUsers.values()) {
                // 增加判断，只有当 session 处于打开状态时才发送消息
                if (session != null && session.isOpen()) {
                    session.getBasicRemote().sendText(message);
                }
            }
        } catch (Exception e){
            // 即使有 isOpen() 判断，在高并发下仍可能出现瞬间关闭的情况，捕获异常防止程序崩溃
            e.printStackTrace();
        }
    }

    /**
     * 接收到消息时调用
     */
    @OnMessage
    public void onMessage(String messageJson, Session session) {
        try {
            // 从 session 中获取发送者ID，这是在 onOpen 方法中存入的
            Long fromId = (Long) session.getUserProperties().get(JwtClaimsConstant.USER_ID);
            if (fromId == null) {
                // 如果无法获取用户ID，则不处理该消息
                return;
            }

            // 解析前端发来的消息，现在它只需要包含 toId 和 content
            ChatMessageDTO message = JSON.parseObject(messageJson, ChatMessageDTO.class);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setFromId(fromId); // 使用从 session 获取的 fromId
            chatMessage.setToId(message.getToId());
            chatMessage.setContent(message.getContent());
            chatMessage.setTimestamp(message.getTimestamp());
            chatMessage.setStatus(0); //未读
            chatMessageMapper.insert(chatMessage);

            Long toId = message.getToId();
            String mess = chatMessage.getContent();

            Session toSession = onlineUsers.get(toId);
            if (toSession != null && toSession.isOpen()) {
                // 将消息转发给目标用户，告诉他们消息来自谁
                String msg1 = MessageUtils.getMessage(false, String.valueOf(fromId), mess);
                toSession.getBasicRemote().sendText(msg1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * WebSocket 连接关闭时调用
     */
    @OnClose
    public void onClose(Session session) {
        // 6. 从 session 的 userProperties 中获取 userId
        Long userId = (Long) session.getUserProperties().get(JwtClaimsConstant.USER_ID);
        if (userId == null) {
            return;
        }

        // 从在线用户中移除
        onlineUsers.remove(userId);

        // 广播消息，通知所有用户有人下线
        String message = MessageUtils.getMessage(true, null, getOnlineUserIds());
        broadcastAllUsers(message);
    }
}