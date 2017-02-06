package com.module.log.datapush.websocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Component
public class LogInterceptor extends HttpSessionHandshakeInterceptor {
   
	         @Override
	        public boolean beforeHandshake(ServerHttpRequest arg0,ServerHttpResponse arg1, WebSocketHandler arg2,Map<String, Object> arg3) throws Exception {

	        	 return super.beforeHandshake(arg0, arg1, arg2, arg3);

	        }
	         
	        @Override
	        public void afterHandshake(ServerHttpRequest request,ServerHttpResponse response, WebSocketHandler wsHandler,Exception ex) {
	        
	        	super.afterHandshake(request, response, wsHandler, ex);
	        
	        }
}
