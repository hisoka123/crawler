package com.storm.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.crawler.dailianmeng.domain.json.UserFeedJson;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.SerializedAllIn;
import com.gargoylesoftware.htmlunit.DefaultPageCreator;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.PageCreator;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.htmlunit.WebCrawler;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.module.ocr.utils.OCRConnectUtils;
import com.storm.def.StormTopologyConfig;
import com.storm.domian.WebParam;
import com.storm.util.IPUtil;

public class DaiLianMengFunction {
	private String hostName = IPUtil.getHostName();
	private String ip = IPUtil.getIp();
	
	private void addCookies(WebClient webClient, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			webClient.getCookieManager().addCookie(cookie);
		}
	}
	
	
	public String getSerializedAllIn(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(DaiLianMengFunction.class, webParam.getLogback());
		LOGGER.info("==================DaiLianMengFunction.getSerializedAllIn start!======================");
		SerializedAllIn serializedAllIn = new SerializedAllIn(); //
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		webClient.getCookieManager().clearCookies();
		
		Map<String, String> resultMap = new HashMap<String, String>();
		Gson gson = new GsonBuilder().create();
		
		String searchPageUrl = webParam.getSearchPage();
		if (StringUtils.isEmpty(searchPageUrl)) {
			LOGGER.error("The searchPageUrl is not defined!");
			return null;
		}
		WebRequest webRequest = new WebRequest(new URL(searchPageUrl), HttpMethod.GET);
		HtmlPage searchPage = webClient.getPage(webRequest);
		WebResponse webResponse = searchPage.getWebResponse();
		LOGGER.info("----------searchPage---------:" + searchPage.asXml());
		
		
		HtmlImage image = searchPage.getFirstByXPath(webParam.getCodeImageId());
		if (image==null) {
			LOGGER.error("The image element is not found!");
			resultMap.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			resultMap.put("searchPageHtml", IPUtil.getHostAndIpStr() + searchPage.asXml());
			resultMap.put("isImageNull", "true");
			return gson.toJson(resultMap);
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
		
		LOGGER.info("The codeImageFile of DaiLianMengFunction.getSerializedAllIn is:{}", codeImageFile.getAbsolutePath());
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
		LOGGER.info("The serializedAllInFileName is:"+serializedAllInFileName);
		
		//返回 序列化文件名 和 验证码图片的URL
		resultMap.put("codeImageUrl", serializedAllIn.getImageUrl());
		resultMap.put("serializedFileName", serializedAllInFileName);
		
		LOGGER.returnRedisResource();
		
		return gson.toJson(resultMap);
	}
	
	
	public String search(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(DaiLianMengFunction.class, webParam.getLogback());
		LOGGER.info("==================DaiLianMengFunction.search start!======================");
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		
		String searchPageUrl = webParam.getSearchPage();
		if (StringUtils.isEmpty(searchPageUrl)) {
			LOGGER.error("The searchPageUrl is not defined!");
			return null;
		}
		
		//反序列化操作
		String serializedAllInFileName = webParam.getSerializedFileName();
		File serializedAllInFile = new File(StormTopologyConfig.getNfs_filepath() + "/" + serializedAllInFileName);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializedAllInFile));
		SerializedAllIn serializedAllIn = (SerializedAllIn) ois.readObject();
		ois.close();
		
		//cookies
		Set<Cookie> cookies = serializedAllIn.getCookies();
		addCookies(webClient, cookies);

		//打开上次的会话窗口
		WebResponse webResponse = serializedAllIn.getWebResponse();
		PageCreator pageCreator = new DefaultPageCreator();
		HtmlPage searchPage = (HtmlPage) pageCreator.createPage(webResponse, webClient.openWindow(new URL(searchPageUrl), "windowName"));
		
		//请求参数
		Map<String, String> params = webParam.getParams();
		String imagecode = params.get("imagecode"); //图片验证码
		String imagecodeXpath = params.get("imagecodeXpath");
		HtmlTextInput inputImagecode = (HtmlTextInput)searchPage.getFirstByXPath(imagecodeXpath);
		inputImagecode.setText(imagecode);
	
		//点击提交请求
		String submitBtnXpath = params.get("submitBtnXpath");
		HtmlElement submitButton = (HtmlElement)searchPage.getFirstByXPath(submitBtnXpath); 
		HtmlPage resultPage = submitButton.click();

		LOGGER.returnRedisResource();
		
		return resultPage.asXml();
	}
	
	public UserFeedJson parseResultHtml(String html) {
		UserFeedJson userFeedJson = new UserFeedJson();
		
		List<String> theadList = new ArrayList<String>();
		
		List<Map<String, String>> tbodyList = new ArrayList<Map<String, String>>();
		
		Elements eles = Jsoup.parseBodyFragment(html).select("table");
		if(eles==null || eles.isEmpty()) {
        	userFeedJson.setTipMessage("此名称暂时没有找到相关的信用记录!");
        } else {
        	//解析table中的数据
        	Elements theadElement = Jsoup.parseBodyFragment(html).select("thead");
        	Elements tbodyElement = Jsoup.parseBodyFragment(html).select("tbody");
        	
        	for (Element thead : theadElement) {
        		List<Node> trNodes = thead.childNodes();
        		for (Node trNode : trNodes) {
        			List<Node> thNodes = trNode.childNodes();
        			for (Node node : thNodes) {
						theadList.add(node.childNode(0).toString());
					}
				}
			}
        	
        	for (Element tbody : tbodyElement) {
				List<Node> trNodes = tbody.childNodes();
				for (Node trNode : trNodes) {
					List<Node> tdNodes = trNode.childNodes();
					for (Node tdNode : tdNodes) {
						List<Node> nodes = tdNode.childNodes();
						Map<String, String> map = new HashMap<String, String>();
						for (int i = 0; i < nodes.size(); i++) {
							map.put("node"+i, nodes.get(i).toString());
						}
						tbodyList.add(map);
					}
				}
			}
        }
		
		userFeedJson.setData(tbodyList);
		
		return userFeedJson;
	}
	
	public String getDetailHtml(WebParam webParam) throws Exception {
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		WebRequest webRequest = new WebRequest(new URL(webParam.getUrl()), HttpMethod.GET);
		HtmlPage detailPage = webClient.getPage(webRequest);
		
		return detailPage.asXml();
	}
	
	private Map<String, Object> parseListHtml(String listHtml) throws Exception {
		Map<String, Object> resultHtmlMap = new HashMap<String, Object>();
		
		if (listHtml.contains("身份证号有误") || listHtml.contains("此名称暂时没有找到相关的信用记录") || listHtml.contains("此身份证号暂时没有找到相关的信用记录")) {
			resultHtmlMap.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
			return resultHtmlMap;
		}
		
		Document doc = Jsoup.parse(listHtml);
		Element div = doc.getElementById("yw1");
		if (div != null) {
			Elements tables = div.select("table");
			if (tables != null && !tables.isEmpty()) {
				Element table = tables.get(0);
				if (table != null) {
					Elements tbodys = table.select("tbody");
					if (tbodys != null && !tbodys.isEmpty()) {
						Element tbody = tbodys.get(0);
						if (tbody != null) {
							Elements trs = tbody.select("tr");
							List<String> detailPages = new ArrayList<String>();
							int count = 0;
							for (Element tr : trs) {
								Elements anchors = tr.select("a");
								if (anchors != null && !anchors.isEmpty()) {
									if (count == 5) break;
									Element anchor = anchors.get(0);
									String href = anchor.attr("href");
									String detailUrl = "http://www.dailianmeng.com" + href;
									HtmlPage detailPage = new WebClient().getPage(detailUrl);
									detailPages.add(detailPage.asXml());
									count++;
								}
							}
							resultHtmlMap.put("detailPages", detailPages);
							resultHtmlMap.put("statusCodeDef", StatusCodeDef.SCCCESS);
						}
					}
				}
			}
		}
		
		return resultHtmlMap;
	}
	
	public String searchWitchOCR(WebParam webParam) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("ip", ip);
		result.put("hostName", hostName);
		
		webParam.setSearchPage("http://www.dailianmeng.com/xinyong/q/"+webParam.getParams().get("keyword")+".html");
		String serializedAllIn = getSerializedAllIn(webParam);
		
		Gson gson = new GsonBuilder().create();
		Type mapType = new TypeToken<HashMap<String,String>>(){}.getType();
		Map<String, String> data = gson.fromJson(serializedAllIn, mapType);
		
		if (data.get("isImageNull") != null) {
			result.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			result.put("searchPageHtml", data.get("searchPageHtml"));
			result.put("isImageNull", "true");
			return new GsonBuilder().setPrettyPrinting().create().toJson(result);
		}
		
		String serializedFileName = data.get("serializedFileName");
		String codeImageUrl = data.get("codeImageUrl");
		String verifycode = "";
		try {
			verifycode = OCRConnectUtils.getVerifycode("/cjy/getVerifycode", codeImageUrl, "4005");
			//verifycode = new Scanner(System.in).next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result.put("imageUrl", codeImageUrl);
		result.put("imagecode", verifycode);
		
		webParam.setSearchPage("http://www.dailianmeng.com/");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("imagecode", verifycode);
		String listHtml = search(webParam);
		
		if (listHtml.contains("验证码不正确")) {
			result.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
			result.put("isImageNull", "false");
		} else {
			result = parseListHtml(listHtml);
		}
		
		return new GsonBuilder().setPrettyPrinting().create().toJson(result);
	}
	
/*	public static void main(String[] args) throws Exception {
		WebParam webParam = new WebParam();
		webParam.setCodeImageId("//img[@id='yw0']");
		webParam.addParam("imagecodeXpath", "//input[@id='SearchForm_verifyCode']");
		webParam.addParam("submitBtnXpath", "//button[@id='mingdan_button']");
		webParam.addParam("keyword", "福安市信和融资担保有限公司");
		
		String result = new DaiLianMengFunction().searchWitchOCR(webParam);
		
		FileUtils.writeStringToFile(new File("C:\\TCode\\dailianmeng\\result.json"), result);
		FileUtils.writeStringToFile(new File("C:\\TCode\\dailianmeng\\result.jsontostring"), new GsonBuilder().create().fromJson(result, Map.class).toString());
	}*/
	
}
