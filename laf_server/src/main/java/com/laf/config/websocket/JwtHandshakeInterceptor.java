package com.laf.config.websocket;

import com.laf.utils.JwtUtil;
import com.laf.properties.JwtProperties;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

public class JwtHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // 从查询参数或 headers 中读取 token（客户端建议通过 ?token=xxx 或 headers: Authorization: Bearer xxx）
        String token = null;
        if (request.getURI().getQuery() != null) {
            for (String part : request.getURI().getQuery().split("&")) {
                if (part.startsWith("token=")) {
                    token = part.substring("token=".length());
                    break;
                }
            }
        }
        if (token == null) {
            List<String> auth = request.getHeaders().get("Authorization");
            if (auth != null && !auth.isEmpty()) {
                String v = auth.get(0);
                if (v.startsWith("Bearer ")) token = v.substring(7);
            }
        }
        if (token == null) {
            return true; // 不强制拒绝连接，具体可按项目策略改为 false
        }
        Long userId = JwtUtil.getUserIdFromToken(token); // 需实现：解析 token 返回用户 id
        if (userId != null) {
            attributes.put("userId", String.valueOf(userId));
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) { }
}
