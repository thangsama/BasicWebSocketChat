package webchat.springboot.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import webchat.springboot.websocket.model.ChatMessage;

@Controller
public class WebSocketController {
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/publicChatRoom")
	public ChatMessage sendMessage (@Payload ChatMessage chatMessage) {
		return chatMessage;
	}
	@MessageMapping("/chat.addUser")
	@SendTo("/topic/publicChatRoom")
	public ChatMessage addUser(@Payload ChatMessage chatMessage
			, SimpMessageHeaderAccessor headerAccessor) {
		//Add username to web socket
		headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
		return chatMessage;
		
	}

}
