/**
 * 
 */
package com.crawlermanage.controller;

import java.net.URLDecoder;

import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.httpclient.HttpUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.module.log.redis.ChannelDef;
import com.module.redis.pubsub.JedisPubSubForLogListener;
import com.module.redis.pubsub.JedisPubThread;

/**
 * @author kingly
 * @date 2015年10月12日
 * 
 */

@Controller
@RequestMapping("/tools")
public class ApiTestController {
	public static final Logger LOGGER = LoggerFactory.getLogger(ApiTestController.class);
	
	
	@RequestMapping("/apitest")
	public String apitest() {
		LOGGER.info("=========================apitest==========================");
		return "tools/apitest";
	}
	
	
	@RequestMapping(value="/apitest/play")
	@ResponseBody
	public String play(@RequestParam("ajaxParam") String ajaxParam, String logback) throws Exception {
		//启动监听jedis日志
		String channel = ChannelDef.LOG_CHANNEL_PREFIX+logback;
		JedisPubThread jedisPubThread = new JedisPubThread(new JedisPubSubForLogListener(), new String[]{channel});
		new Thread(jedisPubThread).start();
		
		ajaxParam = URLDecoder.decode(URLDecoder.decode(ajaxParam, "utf-8"), "utf-8");
		LOGGER.info("ajaxParam: {}", ajaxParam);
		
		//获取API类型和名称 以及请求url的主体部分
		JsonObject jo_root = new JsonParser().parse(ajaxParam).getAsJsonObject();
		String requestPath = jo_root.get("requestPath").getAsString();
		
		//组装urlParamStr
		int paramSize = jo_root.get("param_size").getAsInt();
		StringBuffer requestParamSb = new StringBuffer();
		String requestParamStr = "";
		if (paramSize > 0) {
			for (int i = 0; i < paramSize; i++) {
				JsonElement je_param_key = jo_root.get("key_" + i);
				JsonElement je_param_value = jo_root.get("value_" + i);
				if (je_param_key==null || je_param_value==null) continue;
				String paramKey = je_param_key.getAsString();
				String paramValue = je_param_value.getAsString();
				if (i==0) {
					requestParamSb.append("?" + paramKey + "=" + paramValue);
				} else {
					requestParamSb.append("&" + paramKey + "=" + paramValue);
				}
			}
			requestParamStr = requestParamSb.toString() + "&logback=" + logback;
		} else {
			requestParamStr = requestParamSb.toString() + "?logback=" + logback;
		}
		
		//调用具体API方法
		CloseableHttpClient httpclient = HttpUtils.getHttpclient();
		String resultJson = HttpUtils.get(httpclient, requestPath + requestParamStr);
		
		//结束jedisPubThread
		jedisPubThread.punsubscribe(new String[]{channel});
		return resultJson;
	}
	
}






