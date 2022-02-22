package webchat.springboot.websocket.config;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import webchat.springboot.websocket.interceptor.HttpHandshakeInterceptor;
@Configuration
@EnableWebSocketMessageBroker

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Autowired
	private HttpHandshakeInterceptor handshakeInterceptor;
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registery) {
		registery.addEndpoint("/ws").withSockJS().setInterceptors(handshakeInterceptor);
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry  registery) {
		registery.setApplicationDestinationPrefixes("/app");
		registery.enableSimpleBroker("/topic");
	}
	
}
