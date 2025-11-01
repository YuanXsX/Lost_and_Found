package com.laf.config;

import com.laf.config.websocket.JwtHandshakeInterceptor;
import com.laf.config.websocket.UserHandshakeHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // 客户端连接端点
                .setAllowedOriginPatterns("*")
                .addInterceptors(new JwtHandshakeInterceptor())
                .setHandshakeHandler(new UserHandshakeHandler())
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); // 客户端发送到服务端的前缀
        registry.setUserDestinationPrefix("/user"); // 私聊前缀
        registry.enableSimpleBroker("/queue", "/topic"); // 简单内存 broker（可替换为 Redis）
    }
}
