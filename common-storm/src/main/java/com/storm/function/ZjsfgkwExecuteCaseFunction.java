package com.storm.function;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawler.domain.json.StatusCodeDef;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.WebCrawler;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.storm.domian.WebParam;

public class ZjsfgkwExecuteCaseFunction {
	
	public String search(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(ZjsfgkwExecuteCaseFunction.class, webParam.getLogback());
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		Gson gson = new GsonBuilder().create();
		LOGGER.info("==================ZjsfgkwExecuteCaseFunction.search start!======================");
		WebClient webClient = WebCrawler.getInstance().getWebClient();

		HtmlPage searchPage = webClient.getPage(webParam.getUrl());
		System.out.println("test页面数据searchPage.asXml()="+searchPage.asXml());
//		System.out.println("searchPage.getElementsByIdAndOrName('txtBH')="+searchPage.getElementsByIdAndOrName("txtBH"));
		if(searchPage.getElementsByIdAndOrName("txtBH").size()==0){
			LOGGER.returnRedisResource();
			resultMap.put("statusCodeDef", StatusCodeDef.FREQUENCY_LIMITED);
			resultMap.put("searchPageHtml", searchPage.asXml());
			return gson.toJson(resultMap);
		}
		//请求参数
		Map<String, String> params=uRLRequest(webParam.getUrl());
		HtmlTextInput aH_BH = (HtmlTextInput)(searchPage.getElementsByIdAndOrName("txtBH").get(0)); 
		aH_BH.setText(params.get("ah_bh"));
		
		HtmlTextInput dSR = (HtmlTextInput)(searchPage.getElementsByIdAndOrName("txtDSR").get(0)); 
		dSR.setText(params.get("dsr"));
		
		HtmlSelect ah_nh = (HtmlSelect)(searchPage.getElementsByIdAndOrName("AH_NH").get(0)); 
		if((params.get("ah_nh")).equals("")){
			ah_nh.getOptionByText("2016").setSelected(true);
		}else{
			ah_nh.getOptionByText(params.get("ah_nh")).setSelected(true);
		}
	
		//点击提交请求
		HtmlElement loginButton = (HtmlElement)(searchPage.getElementsByIdAndOrName("btnSumbit").get(0)); 
		HtmlPage loggedPage = loginButton.click();
//		System.out.println(loggedPage.asXml());
		
		LOGGER.returnRedisResource();
		
		resultMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
		resultMap.put("searchPageHtml", loggedPage.asXml());
		return gson.toJson(resultMap);
	}
	
	
	/*public static void main(String[] args) throws Exception {
		ZjsfgkwExecuteCaseFunction zjsfgkwExecuteCaseFunction=new ZjsfgkwExecuteCaseFunction();
		String url =  "http://www.zjsfgkw.cn/Execute/ExecuteCaseSearch?aH_BH="+"浙1102执1125号&dSR=&aH_NH=";
		WebParam webParam = new WebParam();
		webParam.setUrl(url);
		String asXml=zjsfgkwExecuteCaseFunction.search(webParam);
		System.out.println(asXml);
	}*/
	
	/**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     * @param URL  url地址
     * @return  url请求参数部分
     */
    public  Map<String, String> uRLRequest(String URL)
    {
    Map<String, String> mapRequest = new HashMap<String, String>();
   
      String[] arrSplit=null;
     
    String strUrlParam=truncateUrlPage(URL);
    if(strUrlParam==null)
    {
        return mapRequest;
    }
      //每个键值为一组 www.2cto.com
    arrSplit=strUrlParam.split("[&]");
    for(String strSplit:arrSplit)
    {
          String[] arrSplitEqual=null;         
          arrSplitEqual= strSplit.split("[=]");
         
          //解析出键值
          if(arrSplitEqual.length>1)
          {
              //正确解析
              mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
             
          }
          else
          {
              if(arrSplitEqual[0]!="")
              {
              //只有参数没有值，不加入
              mapRequest.put(arrSplitEqual[0], "");       
              }
          }
    }   
    return mapRequest;   
    }
    
    /**
     * 去掉url中的路径，留下请求参数部分
     * @param strURL url地址
     * @return url请求参数部分
     */
    private  String truncateUrlPage(String strURL)
    {
    String strAllParam=null;
      String[] arrSplit=null;
     
      strURL=strURL.trim().toLowerCase();
     
      arrSplit=strURL.split("[?]");
      if(strURL.length()>1)
      {
          if(arrSplit.length>1)
          {
                  if(arrSplit[1]!=null)
                  {
                  strAllParam=arrSplit[1];
                  }
          }
      }
     
    return strAllParam;   
    }
	
}
