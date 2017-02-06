package com.storm.function;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.GsxtResultData;
import com.crawler.storm.def.WebParam;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.storm.def.StormTopologyConfig;

public class GsxtFunction2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(GsxtFunction2.class);
	
	/**
	 * 查询页处理（包括请求参数预处理、获取验证码图片、获取查询页cookies）
	 * @param searchPageUrl
	 * @return
	 * @throws Exception
	 */
	public static String searchPageHandle(WebParam webParam) throws Exception {
		LOGGER.info("=====================GsxtFunction.searchPageHandle start!========================");
		Gson gson = new GsonBuilder().create();
		GsxtResultData gsxtResultData = new GsxtResultData();
		
		//获取搜索页隐藏域（hidden）参数
		if (StringUtils.isEmpty(webParam.getSearchPage())) {
			throw new RuntimeException("The searchPageUrl is not be defined!");
		}
		Response searchPage = Jsoup.connect(webParam.getSearchPage()).execute();
		Document document = searchPage.parse();
		System.out.println(document.html());
		Elements hiddenInputs = document.select("body").get(0).select("input[type=hidden]");
		if (hiddenInputs!=null && !hiddenInputs.isEmpty()) {
			for (Element hiddenInput : hiddenInputs) {
				String name = hiddenInput.attr("name");
				String value = hiddenInput.attr("value");
				if (StringUtils.isEmpty(value.trim())) {
					continue;
				}
				if (webParam.getParams()==null) {
					webParam.setParams(new HashMap<String, String>());
				}
				webParam.getParams().put(name, value);
			}
		}
		LOGGER.info("webParam: {}", webParam);
		
		//获取页面的图片验证码
		if (StringUtils.isEmpty(webParam.getCodeImageId())) {
			throw new RuntimeException("The codeImageId is not be defined!");
		}
		Elements imgEles = document.select("body").get(0).select(webParam.getCodeImageId());
		if (imgEles==null || imgEles.isEmpty()) {
			throw new RuntimeException("The img Element is not be found!");
		}
		Element imgEle = imgEles.get(0);
		String imgSrc = imgEle.attr("src");
		if (!imgSrc.contains("http://") && !imgSrc.contains("https://")) {
			String targetUrl = webParam.getSearchPage();
			if (targetUrl.length()!=targetUrl.lastIndexOf("/")+1) {
				targetUrl = targetUrl.substring(0, targetUrl.lastIndexOf("/"));
			} else {
				targetUrl = targetUrl.substring(0, targetUrl.lastIndexOf("/"));
				targetUrl = targetUrl.substring(0, targetUrl.lastIndexOf("/"));
			}
			imgSrc = targetUrl + imgSrc;
		}
		File parentDirFile = new File("F:/TCode/gsxt");//new File(StormTopologyConfig.getNfs_filepath());
		if (!parentDirFile.exists()) {
			parentDirFile.mkdirs();
		}
		String imageName = UUID.randomUUID() + ".jpg";
		File codeImageFile = new File(parentDirFile.getAbsolutePath() + "/" + imageName);
		codeImageFile.setReadable(true);
		codeImageFile.setWritable(true); 
		FileUtils.copyURLToFile(new URL(imgSrc), codeImageFile);
		String imageUrl = "http://" + StormTopologyConfig.getNfs_nginx_server() + "/" + imageName;
		LOGGER.info("CodeImageFile saved, the URL is: {}", imageUrl);
		gsxtResultData.setImageUrl(imageUrl);
		
		//获取cookies
		Map<String, String> jsoupCookies = searchPage.cookies();
		webParam.setJsoupCookies(jsoupCookies);
		gsxtResultData.setWebParam(webParam);
		
		gsxtResultData.setStatusCode(StatusCodeDef.SCCCESS);
		gsxtResultData.setMessage("成功！");
		gsxtResultData.setHtml(document.html());
		return gson.toJson(gsxtResultData);
	}
	
	
	/**
	 * 查询获取查询结果
	 * @param cookies
	 * @param webParam
	 * @return
	 * @throws Exception
	 */
	public static String getQyList(WebParam webParam) throws Exception {
		LOGGER.info("=====================GsxtFunction.getQyList start!========================");
		GsxtResultData gsxtResultData = new GsxtResultData();
		Gson gson = new GsonBuilder().create();
		
		//cookies url method params headers
		Map<String, String> jsoupCookies = webParam.getJsoupCookies();
		String url = webParam.getUrl(); //
		Method method = "post".equals(webParam.getMethod().toLowerCase()) ? Method.POST : Method.GET;
		Map<String, String> params = webParam.getParams();  //
		Map<String, String> requestHeaders = webParam.getRequestHeaders();
		
		//请求
		Connection connection = Jsoup.connect(url).cookies(jsoupCookies).method(method);
		Set<Entry<String, String>> requestHeaderSet = requestHeaders.entrySet();
		for (Entry<String, String> requestHeaderEntry : requestHeaderSet) {
			String headerName = requestHeaderEntry.getKey();
			String headerValue = requestHeaderEntry.getValue();
			connection = connection.header(headerName, headerValue);
		}
		Response seachResultPage = connection.data(params).execute();
		
		//结果
		String html = seachResultPage.parse().html();
		LOGGER.info("html: {}", html);
		gsxtResultData.setHtml(html);
		
		if (html.contains("访问过于频繁或非正常访问。")) {
			gsxtResultData.setStatusCode(StatusCodeDef.FREQUENCY_LIMITED);
			gsxtResultData.setMessage("访问过于频繁或非正常访问");
		} else {
			gsxtResultData.setStatusCode(StatusCodeDef.SCCCESS);
			gsxtResultData.setMessage("成功！");
		}
		return gson.toJson(gsxtResultData);
	}
	
	
	public static void main(String[] args) throws Exception {
		//图片验证码 及 查询参数预处理
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		WebParam webParam = new WebParam();
		webParam.setSearchPage("http://218.57.139.24/###");
		webParam.setCodeImageId("#secimg");
		String gsxtResultDataJson = searchPageHandle(webParam);
		GsxtResultData gsxtResultData = gson.fromJson(gsxtResultDataJson, GsxtResultData.class);
		
		//图片验证码处理
		webParam = gsxtResultData.getWebParam();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String imageCode = sc.next(); imageCode = DigestUtils.md5Hex(imageCode);
		webParam.addParam("secode", imageCode); //webParam.addParam("clickType", "1");
		
		//查询请求路径 、请求方法、cookies（webParam已携带）、请求头、关键字
		webParam.setUrl("http://218.57.139.24/pub/indsearch");
		webParam.setMethod("post");
		webParam.addRequestHeader("Host", "218.57.139.24");
		webParam.addRequestHeader("Origin", "http://218.57.139.24");
		webParam.addRequestHeader("Referer", "http://218.57.139.24/");
		webParam.addParam("kw", "和顺");
		
		//执行返回结果 
		gsxtResultDataJson = getQyList(webParam);
		gsxtResultData = gson.fromJson(gsxtResultDataJson, GsxtResultData.class);
		String html = gsxtResultData.getHtml();
		System.out.println(html);
	}
	
}
