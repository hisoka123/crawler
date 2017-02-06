package com.module.drpc.service;

import org.apache.thrift7.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

import com.module.drpc.StormDrpc;

@Component
public class CrawlerEngineService {
	
	@Autowired
	StormDrpc stormDrpc;
	
	@Value("${crawlerenginetopology}")
	public String crawlerenginetopology;
	
	
	public String execute(String param) throws TException, DRPCExecutionException{
		DRPCClient client = stormDrpc.getClient();
		String html = client.execute(crawlerenginetopology, param);
		return html;
	}


	public StormDrpc getStormDrpc() {
		return stormDrpc;
	}


	public void setStormDrpc(StormDrpc stormDrpc) {
		this.stormDrpc = stormDrpc;
	}

}
