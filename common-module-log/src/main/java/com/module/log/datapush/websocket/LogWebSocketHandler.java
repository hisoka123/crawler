package com.module.log.datapush.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.module.redis.pubsub.JedisPubSubDef;
import com.module.redis.util.JedisPoolUtil;

import redis.clients.jedis.Jedis;

@Component
public class LogWebSocketHandler implements WebSocketHandler {

	private static final Logger log=LoggerFactory.getLogger(LogWebSocketHandler.class);
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1)
			throws Exception {
		
            log.info("日志websocket连接已关闭。");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		   log.info("日志websocket连接已建立。");
		   
		   session.sendMessage(new TextMessage("logWebSocketSuccess"));

	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> wsm) throws Exception {

		       Jedis jedis = null;
				
			   int order=1;
			   jedis = JedisPoolUtil.getConnection();
			   jedis.select(JedisPubSubDef.TEMP_LOG_DB_INDEX);

			   while(true){
					    String mes=jedis.hget("log_"+wsm.getPayload(),String.valueOf(order));
					    // System.out.println("******mes:"+order+" : "+mes);
					     if("".equals(mes) || mes==null){
					    	   Thread.sleep(200);
					    	   continue;
					      }else if("@logOver".equals(mes)){
					    	    if (jedis!=null) {
									JedisPoolUtil.closeConnection(jedis);
								}
					    	   session.sendMessage(new TextMessage(""));//结束推送
					    	   break;
					      }else{
					    	   session.sendMessage(new TextMessage(mes));
					           order++;
					       }
				   }
				
			
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e)
			throws Exception {
		    if(session.isOpen()){
		    	  session.close();
		    }    
		
		
		    log.error("记录日志websocke发生错误\n");
		    e.printStackTrace();

	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
