package com.laf.ws;

import com.alibaba.fastjson2.JSON;
import com.laf.config.GetHttpSessionConfig;
import com.laf.dto.ChatMessageDTO;
import com.laf.entity.ChatMessage;
import com.laf.mapper.ChatMessageMapper;
import com.laf.utils.MessageUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value="/user/chat", configurator = GetHttpSessionConfig.class)
public class ChatEndpoint {

    private ChatMessageMapper chatMessageMapper;
    private static final Map<String,Session> onlineUsers = new ConcurrentHashMap<>();
    private HttpSession httpSession;
    /**
     * WebSocket 连接建立时调用
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config) {
        //session保存
        this.httpSession=(HttpSession)config.getUserProperties().put(Session.class.getName(), session);
        String userId=(String)this.httpSession.getAttribute("userId");
        onlineUsers.put(userId,session);
        //广播消息，将登录的所有用户推送给所有用户
        String message=MessageUtils.getMessage(true,null,getOthers());
        broadcastAllUsers(message);
    }

    public Set getOthers(){
        Set<String> set=onlineUsers.keySet();
        return set;
    }

    private void broadcastAllUsers(String message) {
        try {
            Set<Map.Entry<String, Session>> entries = onlineUsers.entrySet();
            for (Map.Entry<String, Session> entry : entries) {
                Session session = entry.getValue();
                session.getBasicRemote().sendText(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 接收到消息时调用
     */
    @OnMessage
    public void onMessage(ChatMessageDTO message) {
        //将消息推送给指定用户
        try {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setFromId(message.getFromId());
            chatMessage.setToId(message.getToId());
            chatMessage.setContent(message.getContent());
            chatMessage.setTimestamp(message.getTimestamp());
            chatMessage.setStatus(0);//未读
            chatMessageMapper.insert(chatMessage);
            String toId = String.valueOf(message.getToId());
            String fromId = String.valueOf(message.getFromId());
            String mess = chatMessage.getContent();
            Session session = onlineUsers.get(toId);
            String msg1 = MessageUtils.getMessage(false, fromId, mess);
            session.getBasicRemote().sendText(msg1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * WebSocket 连接关闭时调用
     */
    @OnClose
    public void onClose(Session session) {
        //从在线用户中移除
        String userId=(String)this.httpSession.getAttribute("userId");
        onlineUsers.remove(userId);
        //广播消息，将用户离线推送给所有用户
        String message=MessageUtils.getMessage(true,null,getOthers());
        broadcastAllUsers(message);

    }
}
