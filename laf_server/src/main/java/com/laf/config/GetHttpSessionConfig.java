package com.laf.config;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        //获取HttpSession
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        //将HttpSession存入WebSocket的UserProperties中
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}
