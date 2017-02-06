package com.crawler.zhengxin.htmlparser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crawler.domain.conf.Config;
import com.crawler.htmlparser.AbstractParser;
import com.crawler.zhengxin.domain.json.ZhengxinJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.springframework.stereotype.Component;

@Component
public class ZhengxinParser extends AbstractParser{
	
	private static final Logger log = LoggerFactory.getLogger(ZhengxinParser.class);
	
	public List<ZhengxinJson> qiyezhengxinParser(String html) {
		List<ZhengxinJson> zhengxinJsons=new ArrayList<ZhengxinJson>();
		Gson gson = new GsonBuilder().create();
		Type htmlListType = new TypeToken<List<Object>>(){}.getType();
		List<Object> resultHtmlList = gson.fromJson(html, htmlListType);
		for(int i=0;i<resultHtmlList.size();i++){
			if(resultHtmlList.get(i)!=null){
//			System.out.println(resultHtmlList.get(i).toString());	
			Elements diveles = Jsoup.parseBodyFragment(resultHtmlList.get(i).toString()).select("body").select("div#main").select("div.content").select("div.cont_lcen").select("div.tabJs").select("div.tab01").select("table").select("tbody").select("tr");
			if(diveles==null || diveles.isEmpty()) {
				ZhengxinJson zhengxinJson=new ZhengxinJson();
				zhengxinJson=null;
			}else{
			/*	Element divxx=diveles.get(0);
				Elements dd_results = getElements(divxx, "dd");
				if (dd_results==null || dd_results.isEmpty()) {
					zhengxinJson=null;
				}else{
					zhengxinJson.setCompany(dd_results.get(3).select("img").attr("src"));//发布单位
					zhengxinJson.setDate(getElementText(dd_results.get(4)));//发布时间
					zhengxinJsons.add(zhengxinJson);
				}*/
				//获取主link
				String links="";
				Elements alinks = Jsoup.parseBodyFragment(resultHtmlList.get(i).toString()).select("body").select("div.tit_main").select("div.record_tit").select("div.record_tit_l").select("p.rec_pad").select("span").select("a");			
				if(alinks!=null && !alinks.isEmpty()) {
					links=alinks.get(0).attr("href");
				}
				//获取类型
				String spantype="";
				Elements spantypes=Jsoup.parseBodyFragment(resultHtmlList.get(i).toString()).select("body").select("div#main").select("div.content").select("div.cont_l").select("div.cont_ltit").select("div.top_title").select("span");
				if(spantypes!=null && !spantypes.isEmpty()) {
					spantype=spantypes.get(0).text();
				}
				for(int j=0;j<diveles.size();j++){
					Elements tdsElements=diveles.get(j).select("td.taLeft");
					if(tdsElements!=null && tdsElements.size()==2){
						ZhengxinJson zhengxinJson=new ZhengxinJson();
						zhengxinJson.setTitle(diveles.get(j).select("td").get(0).select("a").attr("title"));//档案标题
						zhengxinJson.setLink(links+diveles.get(j).select("td").get(0).select("a").attr("href"));//档案请求url
						zhengxinJson.setCompany(diveles.get(j).select("td").get(1).attr("title"));//发布单位
						zhengxinJson.setProperty(spantype);
						zhengxinJson.setDate(diveles.get(j).select("td").get(3).text());//发布时间
						String edata = diveles.get(j).select("td").get(2).select("img").attr("src").split(",")[1];
						byte[] decodeBase64 = Base64.decodeBase64(edata);
						String bad = UUID.randomUUID() + ".jpg";
						try {
							FileUtils.writeByteArrayToFile(new File(Config.getNfsFilepath()+"/11315/"
									+bad), decodeBase64);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						zhengxinJson.setProperty(bad);//档案属性
						zhengxinJson.setType(spantype);//类型（行政奖罚信息，行业协会(社会组织)评价信息）
						zhengxinJsons.add(zhengxinJson);
					}
				}
			}
		 }
		}
		
		return zhengxinJsons;
	}

}
