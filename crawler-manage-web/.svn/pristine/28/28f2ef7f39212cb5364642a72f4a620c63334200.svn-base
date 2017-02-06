package com.crawlermanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.httpclient.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.drpc.StormDrpc;

@Controller
@RequestMapping("/sui")
public class StormUIController {
	@Autowired
	StormDrpc stormDrpc;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
    public String index(Model model){
		String hp = "http://" + stormDrpc.getHost() + ":8080";
		CloseableHttpClient httpclient = HttpUtils.getHttpclient();
		
		String cluster = HttpUtils.get(httpclient, hp+"/api/v1/cluster/summary");
		String topology = HttpUtils.get(httpclient, hp+"/api/v1/topology/summary");
		String supervisor = HttpUtils.get(httpclient, hp+"/api/v1/supervisor/summary");
		String config = HttpUtils.get(httpclient, hp+"/api/v1/cluster/configuration");
		
		String data = "{\"cluster\":"+ cluster +", \"topology\":"+ topology +", \"supervisor\":"+ supervisor +", \"config\":"+ config +"}";
		Gson gson = new GsonBuilder().create();
		@SuppressWarnings("unchecked")
		Map<String,Object> dataMap = gson.fromJson(data, Map.class);
		
		//将config中的double数据转换为int数据
		Map<?,?> oriConfigMap = (Map<?,?>)dataMap.get("config");
		Map<Object,Object> configMap = new HashMap<Object,Object>();
		for (Map.Entry<?, ?> entry : oriConfigMap.entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			if (value==null) {
				configMap.put(key, value);
			} else if (value instanceof Double) {
				configMap.put(key, ((Double)value).intValue());
			} else if (value instanceof ArrayList && !((ArrayList<?>)value).isEmpty() && ((ArrayList<?>)value).get(0) instanceof Double) {
				ArrayList<Object> alterValue = new ArrayList<Object>();
				for (int i = 0; i < ((ArrayList<?>)value).size(); i++) {
					alterValue.add(((Double)((ArrayList<?>)value).get(i)).intValue());
				}
				configMap.put(key, alterValue);
			} else {
				configMap.put(key, value);
			}
		}
		dataMap.put("config", configMap);
		
		model.addAttribute("dataMap", dataMap);
		return "storm-ui/index";
    }
	
	
	@RequestMapping(value="/topology", method=RequestMethod.GET)
	public String topology(@RequestParam("id") String id, Model model) {
		String hp = "http://" + stormDrpc.getHost() + ":8080";
		CloseableHttpClient httpclient = HttpUtils.getHttpclient();
		String data = HttpUtils.get(httpclient, hp+"/api/v1/topology/"+ id +"?sys=false");
		Gson gson = new GsonBuilder().create();
		@SuppressWarnings("unchecked")
		Map<String,Object> dataMap = gson.fromJson(data, Map.class);
		
		//将config中的double数据转换为int数据
		Map<?,?> oriConfigMap = (Map<?,?>)dataMap.get("configuration");
		Map<Object,Object> configMap = new HashMap<Object,Object>();
		for (Map.Entry<?, ?> entry : oriConfigMap.entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			if (value==null || "topology.stats.sample.rate".equals(key)) {
				configMap.put(key, value);
			} else if (value instanceof Double) {
				configMap.put(key, ((Double)value).intValue());
			} else if (value instanceof ArrayList && !((ArrayList<?>)value).isEmpty() && ((ArrayList<?>)value).get(0) instanceof Double) {
				ArrayList<Object> alterValue = new ArrayList<Object>();
				for (int i = 0; i < ((ArrayList<?>)value).size(); i++) {
					alterValue.add(((Double)((ArrayList<?>)value).get(i)).intValue());
				}
				configMap.put(key, alterValue);
			} else {
				configMap.put(key, value);
			}
		}
		dataMap.put("configuration", configMap);
		
		model.addAttribute("dataMap", dataMap);
		return "storm-ui/topology";
	}
	
	@RequestMapping(value="/spout", method=RequestMethod.GET)
	public String spout(@RequestParam("id") String id, @RequestParam("topology_id") String topology_id, Model model) {
		String hp = "http://" + stormDrpc.getHost() + ":8080";
		CloseableHttpClient httpclient = HttpUtils.getHttpclient();
		String data = HttpUtils.get(httpclient, hp+"/api/v1/topology/"+ topology_id +"/component/"+ id +"?sys=false");
		Gson gson = new GsonBuilder().create();
		@SuppressWarnings("unchecked")
		Map<String,Object> dataMap = gson.fromJson(data, Map.class);
		
		model.addAttribute("dataMap", dataMap);
		return "storm-ui/spout";
	}
	
	@RequestMapping(value="/bolt", method=RequestMethod.GET)
	public String bolt(@RequestParam("id") String id, @RequestParam("topology_id") String topology_id, Model model) {
		String hp = "http://" + stormDrpc.getHost() + ":8080";
		CloseableHttpClient httpclient = HttpUtils.getHttpclient();
		String data = HttpUtils.get(httpclient, hp+"/api/v1/topology/"+ topology_id +"/component/"+ id +"?sys=false");
		Gson gson = new GsonBuilder().create();
		@SuppressWarnings("unchecked")
		Map<String,Object> dataMap = gson.fromJson(data, Map.class);
		
		model.addAttribute("dataMap", dataMap);	
		return "storm-ui/bolt";
	}
	
}


















