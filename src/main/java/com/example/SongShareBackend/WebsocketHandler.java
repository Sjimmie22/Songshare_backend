package com.example.SongShareBackend;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebsocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Extract the received message
        String receivedMessage = message.getPayload();

        // Log the received message (you can add your custom logic here)
        System.out.println("Received message: " + receivedMessage);

        // Send a response back to the client if needed
        String responseMessage = "Hello, client! I received your message: " + receivedMessage;
        session.sendMessage(new TextMessage(responseMessage));
    }
}
