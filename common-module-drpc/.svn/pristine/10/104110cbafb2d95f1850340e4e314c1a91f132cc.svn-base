package com.module.drpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.utils.DRPCClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StormDrpc {
	
	private static final Logger log = LoggerFactory.getLogger(StormDrpc.class);
	  
	private DRPCClient client = null; 
	
	@Value("${storm.drpc.host}")
	public String host;
	
	@Value("${storm.drpc.port}")
	public String port;
	
	public StormDrpc(){
		
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	public int getInt(String str){
		int i = Integer.parseInt(str);
		return i;
	}

	public DRPCClient getClient() {
		//每次请求需要new 一个DRPCClient  #重要#
		//if(client == null){
			log.info("Storm DRPC Host:"+host+",Port:"+port);
			client = new DRPCClient(host, getInt(port));  
		//}
		return client;
	}

	public void setClient(DRPCClient client) {
		this.client = client;
	}
	
 
	
}
