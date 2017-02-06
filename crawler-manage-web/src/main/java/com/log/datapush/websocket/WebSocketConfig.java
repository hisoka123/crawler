package com.log.datapush.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.module.log.datapush.websocket.LogInterceptor;
import com.module.log.datapush.websocket.LogWebSocketHandler;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements
		WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

		  registry.addHandler(logWebSocketHandler(),"/logWebSocket")
                   .addInterceptors(new LogInterceptor());

          registry.addHandler(logWebSocketHandler(),"/sockjs/logWebSocket")
                  .addInterceptors(new LogInterceptor())
                  .withSockJS();
		
		
	}

	@Bean
	public WebSocketHandler logWebSocketHandler(){
		
		   return new LogWebSocketHandler();
	}
	
}
