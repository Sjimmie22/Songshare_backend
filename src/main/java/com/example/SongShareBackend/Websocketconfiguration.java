package com.example.SongShareBackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class Websocketconfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Register your WebSocket endpoint
        registry.addHandler(myWebSocketHandler(), "/socket")
                .setAllowedOrigins("*");
    }

    @Bean
    public WebsocketHandler myWebSocketHandler() {
        return new WebsocketHandler();
    }
}
