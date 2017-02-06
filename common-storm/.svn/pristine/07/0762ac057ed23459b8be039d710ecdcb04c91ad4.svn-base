package com.storm.function;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.crawler.storm.def.SerializedAllIn;
import com.crawler.zhengxin.login.ZhengxinWebClient;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.WebCrawler;
import com.module.ocr.utils.OCRConnectUtils;
import com.storm.def.StormTopologyConfig;
import com.storm.domian.WebParam;

public class QiyezhengxinApiFunction {
	private static final Logger LOGGER = LoggerFactory.getLogger(QiyezhengxinApiFunction.class);
	
	public String search(WebParam webParam) throws Exception {
		LOGGER.info("==================QiyezhengxinApiFunction.search start!======================");
		//请求参数
		 Map<String, String> params = webParam.getParams();
	        boolean jsEnable=false;
	        boolean frequently=false;
	        if(!params.get("jsEnable").isEmpty() && params.get("jsEnable").equals("1")){
	        	jsEnable=true;
	        }
	    WebPageResponse searchPage = getPage(webParam.getUrl(),jsEnable);
	    if(cheakFrequently(searchPage.getHtml())){
	    	frequently=true;
	    }
	    if(cheakCode(searchPage.getHtml())){
			getCheakCode(webParam.getUrl());
			searchPage= getPage(webParam.getUrl(),jsEnable);
		}
	    
		List<Object> resutList= new ArrayList<Object>();
		int w=0;//符合关键词列表的前5条
		
		LOGGER.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Elements eles = Jsoup.parseBodyFragment(searchPage.getHtml()).select("body").select("div#main").select("div.content").select("div.innerBox").select("p").select("a");
		LOGGER.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$eles="+eles);
		if(eles==null || eles.isEmpty()) {
			return "0";
		}else{
		//循环列表数据
	 if(!params.get("keyword").isEmpty()){
		for(int i=0;i<eles.size();i++){
			//符合关键词的前5列
			String aEle=getElementText(eles.get(i)).trim().replace(" ","");
			if(aEle.indexOf(params.get("keyword").trim())!=-1 && w<1){//包含关键词
				w=w+1;
				String elesUrl=eles.get(i).attr("href");
				WebPageResponse aPage = getPage(elesUrl,jsEnable);
				if(cheakFrequently(aPage.getHtml())){
				    	frequently=true;
				 }
				if(cheakCode(aPage.getHtml())){
					getCheakCode(elesUrl);
					aPage= getPage(elesUrl,jsEnable);
				}
				Elements aeles = Jsoup.parseBodyFragment(aPage.getHtml()).select("div#main").select("div.content").select("div.cont_l").select("div.cont_ltit").select("div.cont_lcen").select("h4.v1_title");
				if(aeles!=null && !aeles.isEmpty()) {

					//行政奖罚信息 列表页
					if(!(aeles.get(3).select("a").get(0).attr("href")).equals("javascript:void(0);")){
						WebPageResponse rewardPage = getPage(elesUrl+aeles.get(3).select("a").get(0).attr("href"),jsEnable);
						if(cheakFrequently(rewardPage.getHtml())){
					    	frequently=true;
					     }
						if(cheakCode(rewardPage.getHtml())){
							getCheakCode(elesUrl+aeles.get(3).select("a").get(0).attr("href"));
							rewardPage = getPage(elesUrl+aeles.get(3).select("a").get(0).attr("href"),jsEnable);
						}
						resutList.add(rewardPage.getHtml());
						/*Elements rewardeles = Jsoup.parseBodyFragment(rewardPage.getHtml()).select("div#main").select("div.content").select("div.cont_l").select("div.cont_ltit").select("div.cont_lcen").select("div.tabJs").select("div.tab01").select("table").select("tr").select("td").select("a");
						if(rewardeles!=null && !rewardeles.isEmpty()) {
							for(int j=0;j<rewardeles.size();j++){
								WebPageResponse rewardAPage = getPage(elesUrl+rewardeles.get(j).attr("href"),jsEnable);
								if(cheakFrequently(rewardAPage.getHtml())){
							    	frequently=true;
							     }
								if(cheakCode(rewardAPage.getHtml())){
									getCheakCode(elesUrl+rewardeles.get(j).attr("href"));
									rewardAPage = getPage(elesUrl+rewardeles.get(j).attr("href"),jsEnable);
								}
								resutList.add(rewardAPage.getHtml());
							}
						}*/
					}
					
					
					//行业协会评价 列表页
					if(!(aeles.get(9).select("a").get(0).attr("href")).equals("javascript:void(0);")){
						WebPageResponse associationPage = getPage(elesUrl+aeles.get(9).select("a").get(0).attr("href"),jsEnable);
						if(cheakFrequently(associationPage.getHtml())){
					    	frequently=true;
					     }
						if(cheakCode(associationPage.getHtml())){
							getCheakCode(elesUrl+aeles.get(9).select("a").get(0).attr("href"));
							associationPage = getPage(elesUrl+aeles.get(9).select("a").get(0).attr("href"),jsEnable);
						}
						resutList.add(associationPage.getHtml());
					/*	Elements associationeles = Jsoup.parseBodyFragment(associationPage.getHtml()).select("div#main").select("div.content").select("div.cont_l").select("div.cont_ltit").select("div.cont_lcen").select("div.tabJs").select("div.tab01").select("table").select("tr").select("td").select("a");
						if(associationeles!=null && !associationeles.isEmpty()) {
							for(int x=0;x<associationeles.size();x++){
								WebPageResponse associationAPage =getPage(elesUrl+associationeles.get(x).attr("href"),jsEnable);
								if(cheakFrequently(associationAPage.getHtml())){
							    	frequently=true;
							     }
								if(cheakCode(associationAPage.getHtml())){
									getCheakCode(elesUrl+associationeles.get(x).attr("href"));
									associationAPage = getPage(elesUrl+associationeles.get(x).attr("href"),jsEnable);
								}
								resutList.add(associationAPage.getHtml());
							}
						}*/
					}
				}
			 }
		   }
		 }
	 if(frequently==true){
		 return "frequently";	
	 }else{
		 return new GsonBuilder().setPrettyPrinting().create().toJson(resutList);	 
	 }
	}
	 
	 
	}
	
	//判断是否是验证码页面
	public boolean cheakCode(String html){
		boolean flag=false;//true表示是验证码页面，false表示不是验证码页面
		Elements aeles = Jsoup.parseBodyFragment(html).select("body").select("span").select("a#csshowcode");	
		if(aeles!=null && !aeles.isEmpty()) {
			flag=true;
		}
		return flag;
	}
	
	//解析验证码页面
	public void getCheakCode(String url) throws Exception{
			WebParam webParam = new WebParam();
			webParam.setSearchPage(url);
			getSerializedAllIn(webParam);
	}
	
	//判断是否是访问的太快
	public boolean cheakFrequently(String html){
		boolean flag=false;//true表示是访问的太快，false表示不是访问的太快
		Elements aeles = Jsoup.parseBodyFragment(html).select("body").select("span");	
		if(aeles!=null && !aeles.isEmpty()) {
			Element aele = aeles.get(0);
			flag=getElementText(aele).contains("你可能访问的太快了");
		}
		return flag;
	}
	
	//初始化验证码
		public void getSerializedAllIn(WebParam webParam) throws Exception {
			LOGGER.info("==================QiyezhengxinApiFunction.getSerializedAllIn start!======================");
			SerializedAllIn serializedAllIn = new SerializedAllIn(); 
			WebClient webClient = WebCrawler.getInstance().getWebClient();
			webClient.getOptions().setJavaScriptEnabled(true);//
			Gson gson = new GsonBuilder().create();
			
			String searchPageUrl = webParam.getSearchPage();
			if (StringUtils.isEmpty(searchPageUrl)) {
				LOGGER.error("The searchPageUrl is not defined!");
//				return null;
			}
			WebRequest webRequest = new WebRequest(new URL(searchPageUrl), HttpMethod.GET);
			HtmlPage searchPage = webClient.getPage(webRequest);
			//触发点击事件，获取图片验证码
			HtmlElement codeButton = (HtmlElement) searchPage.getFirstByXPath("//body/span/a[@id='csshowcode']"); 
			HtmlPage codePage = codeButton.click();
			HtmlImage image = codePage.getFirstByXPath("//img[@id='safecode']");
			WebResponse webResponse = codePage.getWebResponse();
			if (image==null) {
				LOGGER.error("The image element is not found!");
//				return null;
			}
			
			File parentDirFile = new File(StormTopologyConfig.getNfs_filepath());
			parentDirFile.setReadable(true); //
			parentDirFile.setWritable(true); //
			if (!parentDirFile.exists()) {
				parentDirFile.mkdirs();
			}
			String imageName = UUID.randomUUID() + ".jpg";
			File codeImageFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + imageName);
			codeImageFile.setReadable(true); //
			codeImageFile.setWritable(true); //
			
			LOGGER.info("The codeImageFile of QiyezhengxinApiFunction.getSerializedAllIn is:{}", codeImageFile.getAbsolutePath());
			image.saveAs(codeImageFile);
			LOGGER.info("----codeImageFile saved!----");
			
			//imageUrl
			serializedAllIn.setImageUrl("http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName);
			LOGGER.info("The serializedAllIn.imageUrl is: {}", serializedAllIn.getImageUrl());
			
			//cookies
			Set<Cookie> cookies = webClient.getCookieManager().getCookies();
			serializedAllIn.setCookies(cookies);
			
			//webResponse
			serializedAllIn.setWebResponse(webResponse);
			
			//序列化操作
			String serializedAllInFileName = UUID.randomUUID().toString();
			File serializedAllInFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + serializedAllInFileName);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializedAllInFile));
			oos.writeObject(serializedAllIn);
			oos.close();
			LOGGER.info("The serializedAllInFileName is: {}", serializedAllInFileName);
			
			//返回 序列化文件名 和 验证码图片的URL
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("codeImageUrl", serializedAllIn.getImageUrl());
			resultMap.put("serializedFileName", serializedAllInFileName);
			//获取验证码
			String verifycode =OCRConnectUtils.getVerifycode("/zhengxin/getVerifycode", serializedAllIn.getImageUrl());
			LOGGER.info("11315获取验证码: {}", verifycode);
		   //提交验证码页面
			HtmlPage firstInfoPage = null;
			HtmlInput inputKeyword = (HtmlInput)searchPage.getFirstByXPath("//input[@id='random']");
			inputKeyword.reset();
			inputKeyword.setAttribute("value", verifycode);
			
			HtmlInput complaintSubmit = (HtmlInput)searchPage.getFirstByXPath("//input[@id='complaintSubmit']");
			firstInfoPage =complaintSubmit.click();
		}
	public String getElementText(Element e){
		String text = null;
		if(e!=null){
			text = e.text();
		}
		return text;
	}
	
	public WebPageResponse getPage(String url,boolean jsEnable) throws Exception{
		LOGGER.info("getPage:{},jsEnable:{}",url,jsEnable);
		WebPageResponse response = ZhengxinWebClient.getInstance().getPageInlogin(url,jsEnable);   
		return response;
	}
	
}
