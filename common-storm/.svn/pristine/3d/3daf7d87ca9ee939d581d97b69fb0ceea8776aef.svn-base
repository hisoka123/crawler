package com.storm.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.crawler.cnca.domain.json.CertificateCompany;
import com.crawler.cnca.domain.json.CertificateCompanyData;
import com.crawler.cnca.domain.json.CncaData;
import com.crawler.cnca.domain.json.CncaResult;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.SerializedAllIn;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.DefaultPageCreator;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.PageCreator;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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

/**
 * 爬取通用数据（包括认证认可业务信息统一查询平台）的核心模块
 */
public class CommonFunction {

	/**
	 * 返回数据公用类
	 */
	public class ResultData {
		public String statusCode;
		public String message;
		public String imageUrl;
		public String serializedFileName;
		public String html;
	}

	// 将获取的数据对象转为json字符串
	private String parseResultDataToJson(Object obj) {
		return new GsonBuilder().create().toJson(obj);
	}

	// 清除上次访问获取到的cookies
	@SuppressWarnings("deprecation")
	private void clearCookies(WebClient webClient, URL url)
			throws MalformedURLException {
		CookieManager manager = webClient.getCookieManager();
		Set<Cookie> cookies = manager.getCookies(url);
		Set<String> cookieNames = new HashSet<String>();
		for (Cookie cookie : cookies) {
			cookieNames.add(cookie.getName());
		}
		for (String cookieName : cookieNames) {
			Cookie cookie = manager.getCookie(cookieName);
			manager.removeCookie(cookie);
		}
	}

	// 添加本次访问获取到的cookies
	private void addCookies(WebClient webClient, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			if (StringUtils.isEmpty(cookie.getName())) {
				continue;
			}
			webClient.getCookieManager().addCookie(cookie);
		}
	}

	/**
	 * 序列化查询页面
	 * 
	 * @return ResultData（包含查询页cookies、验证码图片URL）
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation" })
	public String searchPageHandle(WebParam webParam) throws Exception {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CommonFunction.class, webParam.getLogback());

		LOGGER.info("=====CommonFunction.searchPageHandle is begin=====");

		if (StringUtils.isEmpty(webParam.getSearchPage())) {
			LOGGER.error("The searchPageUrl is not defined!");
			return null;
		}

		ResultData resultData = new ResultData();
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		// webClient.getOptions().setUseInsecureSSL(true);

		// 清除cookies
		clearCookies(webClient, new URL(webParam.getSearchPage()));

		// 重设cookies
		// 初始化webClient，并get请求查询
		webClient.addRequestHeader("Origin",
				webParam.getRequestHeaders().get("Origin"));
		webClient.addRequestHeader("Referer",
				webParam.getRequestHeaders().get("Referer"));
		WebRequest requestSearch = new WebRequest(new URL(
				webParam.getSearchPage()), HttpMethod.GET);

		HtmlPage searchPage = webClient.getPage(requestSearch);

		LOGGER.info("the return html of CommonFunction.searchPageHandle is:"
				+ searchPage.asXml());

		HtmlImage image = (HtmlImage) searchPage.getFirstByXPath(webParam
				.getCodeImageId());

		String resultDataToJson = "";

		if (null == image) {

			resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
			resultData.message = "获取查询页面的验证码失败，请重新查询！";
			LOGGER.info("获取查询页面的验证码图片失败，请重新查询！");

		} else {

			if (image != null
					&& (StringUtils.isEmpty(image.getAttribute("src")) || image
							.getAttribute("src").trim().equals("#"))) {
				image.click();
			}
			File parentDirFile = new File(StormTopologyConfig.getNfs_filepath());
			parentDirFile.setReadable(true);
			parentDirFile.setWritable(true);
			parentDirFile.canRead();
			parentDirFile.canWrite();
			if (!parentDirFile.exists()) {
				parentDirFile.mkdirs();
			}
			String imageName = UUID.randomUUID() + ".jpg";
			File codeImageFile = new File(StormTopologyConfig.getNfs_filepath()
					+ "/" + imageName);
			codeImageFile.setReadable(true);
			codeImageFile.setWritable(true);
			LOGGER.info("The codeImageFile of CommonFunction.searchPageHandle is:"
					+ codeImageFile.getAbsolutePath());
			if (null == image || StringUtils.isEmpty(image.getAttribute("src"))) {
				resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
				resultData.message = "获取查询页面的验证码失败，请重新查询！";
				webClient.removeRequestHeader("Origin");
				webClient.removeRequestHeader("Referer");
				resultDataToJson = parseResultDataToJson(resultData);
				LOGGER.info("获取查询页面的验证码图片失败，请重新查询！");
				return resultDataToJson;
			}
			image.saveAs(codeImageFile);
			LOGGER.info("----codeImageFile saved!----");

			SerializedAllIn serializedAllIn = new SerializedAllIn();
			Set<Cookie> cookies = webClient.getCookieManager().getCookies(
					new URL(webParam.getSearchPage()));
			serializedAllIn.setCookies(cookies);
			// webResponse
			WebResponse webResponse = searchPage.getWebResponse();
			serializedAllIn.setWebResponse(webResponse);
			// 序列化操作
			String serializedAllInFileName = UUID.randomUUID().toString();// StormTopologyConfig.getNfs_filepath()
			File serializedAllInFile = new File(
					StormTopologyConfig.getNfs_filepath() + "/"
							+ serializedAllInFileName);// "C:\\TCode\\gsxt"
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(serializedAllInFile));
			oos.writeObject(serializedAllIn);
			oos.close();
			LOGGER.info("The serializedAllInFileName is: "
					+ serializedAllInFileName);

			// 状态信息
			resultData.statusCode = StatusCodeDef.SCCCESS;
			resultData.message = "成功获取查询页面的cookies及图片验证码";
			resultData.imageUrl = "http://"
					+ StormTopologyConfig.getNfs_nginx_server() + "/"
					+ imageName;
			LOGGER.info("The imageUrl is: " + resultData.imageUrl);
			resultData.serializedFileName = serializedAllInFileName;

		}

		webClient.removeRequestHeader("Origin");
		webClient.removeRequestHeader("Referer");
		resultDataToJson = parseResultDataToJson(resultData);
		LOGGER.info("The return ResultDataJson of CommonFunction.searchPageHandle is:"
				+ resultDataToJson);

		LOGGER.returnRedisResource();

		return resultDataToJson;

	}

	// 根据序列化文件名称获取查询页面
	private HtmlPage getSearchPageBySerialize(WebClient webClient,
			WebParam webParam) throws Exception {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CommonFunction.class, webParam.getLogback());

		String searchPage = webParam.getSearchPage();

		if (StringUtils.isEmpty(searchPage)) {
			LOGGER.error("The searchPage is not defined!");
			return null;
		}

		// 反序列化操作
		String serializedAllInFileName = webParam.getSerializedFileName();// StormTopologyConfig.getNfs_filepath()
		File serializedAllInFile = new File(
				StormTopologyConfig.getNfs_filepath() + "/"
						+ serializedAllInFileName);// "C:\\TCode\\gsxt"
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				serializedAllInFile));
		SerializedAllIn serializedAllIn = (SerializedAllIn) ois.readObject();
		ois.close();

		// cookies
		Set<Cookie> cookies = serializedAllIn.getCookies();
		clearCookies(webClient, new URL(searchPage));
		addCookies(webClient, cookies);

		// 打开上次的会话窗口
		WebResponse webResponse = serializedAllIn.getWebResponse();
		PageCreator pageCreator = new DefaultPageCreator();
		HtmlPage resultPage = (HtmlPage) pageCreator.createPage(webResponse,
				webClient.openWindow(new URL(searchPage), "windowName"));

		LOGGER.returnRedisResource();

		return resultPage;

	}

	// 根据URL地址获取查询结果
	private HtmlPage getFirstInfoPageByUrl(WebParam webParam,
			WebClient webClient) throws Exception {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CommonFunction.class, webParam.getLogback());

		// 请求参数
		Map<String, String> params = webParam.getParams();
		String imagecode = params.get("imagecode"); // 图片验证码
		if (StringUtils.isEmpty(imagecode)) {
			imagecode = "0000";
		}
		String url = webParam.getUrl();
		HtmlPage firstInfoPage = null;
		if (imagecode != null) {
			firstInfoPage = webClient.getPage(url);
			LOGGER.info("The firstInfoPage is:" + firstInfoPage.asXml());
			LOGGER.info("The firstInfoPage Url is:" + firstInfoPage.getUrl());
		}

		LOGGER.returnRedisResource();

		return firstInfoPage;

	}

	/**
	 * 查询结果
	 * 
	 * @param webParam
	 * @return
	 * @throws Exception
	 */
	public String search(WebParam webParam) throws Exception {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CommonFunction.class, webParam.getLogback());

		LOGGER.info("==================CommonFunction.search start!======================");

		ResultData resultData = new ResultData();
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		getSearchPageBySerialize(webClient, webParam);

		// 得到目标信息
		Map<String, String> params = webParam.getParams();
		String webSite = params.get("webSite"); // 网站
		if ("cncaCompanyList".equals(webSite.trim())) {
			HtmlPage firstInfoPage = getFirstInfoPageByUrl(webParam, webClient);
			resultData = getResultForCncaCompany(firstInfoPage, webParam);
		} else if ("companyCncaList".equals(webSite.trim())) {
			resultData = getResultForCompanyCnca(webParam, webClient);
		} else if ("companyCncaDetail".equals(webSite.trim())) {
			resultData = getResultForCncaDetail(webParam, webClient);
		}

		LOGGER.returnRedisResource();

		return new GsonBuilder().setPrettyPrinting().create()
				.toJson(resultData);

	}

	// 认证认可业务信息统一查询平台（公司信息）
	private ResultData getResultForCncaCompany(HtmlPage firstInfoPage,
			WebParam webParam) throws IOException {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CommonFunction.class, webParam.getLogback());

		LOGGER.info("===============CommonFunction.getResultForCnca start!============");

		ResultData resultData = new ResultData();

		if (null == firstInfoPage) {

			resultData.statusCode = StatusCodeDef.FAILURE;
			resultData.message = "查询相关的企业或个人认证信息失败！";

		} else {

			WebResponse webResponse = firstInfoPage.getWebResponse();
			String contentAsString = webResponse.getContentAsString();
			LOGGER.info("CommonFunction.getResultForCncaCompany start!======"
					+ contentAsString);
			if (contentAsString.contains("验证码不正确!")
					|| contentAsString.contains("\"success\":false")) {
				resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
				resultData.message = "验证码输入有误！";
				resultData.html = contentAsString;
			} else {
				resultData.statusCode = StatusCodeDef.SCCCESS;
				resultData.message = "查询相关的企业或个人认证信息成功！";
				resultData.html = contentAsString;
			}

		}

		LOGGER.returnRedisResource();

		return resultData;

	}

	// 认证认可业务信息统一查询平台（认证信息）
	private ResultData getResultForCompanyCnca(WebParam webParam,
			WebClient webClient) throws IOException {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CommonFunction.class, webParam.getLogback());

		LOGGER.info("===============CommonFunction.getResultForCnca start!============");

		ResultData resultData = new ResultData();
		String url = webParam.getUrl();
		HtmlPage resultPage = webClient.getPage(url);
		WebResponse webResponse = resultPage.getWebResponse();
		String contentAsString = webResponse.getContentAsString();
		resultData.html = contentAsString;

		LOGGER.returnRedisResource();

		return resultData;

	}

	// 认证认可业务信息统一查询平台（认证详情）
	private ResultData getResultForCncaDetail(WebParam webParam,
			WebClient webClient) throws IOException {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CommonFunction.class, webParam.getLogback());

		LOGGER.info("===============CommonFunction.getResultForCnca start!============");

		ResultData resultData = new ResultData();
		String url = webParam.getUrl();
		HtmlPage resultPage = webClient.getPage(url);
		// HtmlElement mainFrame =
		// resultPage.getFirstByXPath("//iframe[@id='mainFrame']");
		// HtmlPage resultPage1 =
		// resultPage.getWebClient().getPage(webParam.getParams().get("realUrl"));
		resultData.html = resultPage.asXml();

		LOGGER.returnRedisResource();

		return resultData;

	}

	public String searchWithOCR(WebParam webParam) throws Exception {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CommonFunction.class, webParam.getLogback());
		Map<String, Object> resultHtmlMap = null;
		try {
			LOGGER.info("==================CommonFunction.searchWithOCR start!======================");
			WebClient webClient = WebCrawler.getInstance().getWebClient();
			Map<String, String> params = webParam.getParams();
			String webSite = params.get("webSite"); // 网站
			String keyword = params.get("keyword").trim(); // 关键字
			HtmlPage searchPage = null;
			try {
				webClient.getOptions().setTimeout(120000); // 得到搜索页超时时间均设置为120s
				searchPage = getSearchPageByUrl(webClient, webParam);
			} finally {
				webClient.getOptions().setTimeout(
						WebCrawler.DEFAULT_PAGE_TIME_OUT);
			}
			String statusCodeDefOfGetSearchPageByUrl = webParam.getParams()
					.get("statusCodeDef");
			if (StatusCodeDef.SCCCESS.equals(statusCodeDefOfGetSearchPageByUrl)) {
				String imageUrl = webParam.getParams().get("imageUrl");
				String verifycode = null;
				if (StringUtils.isNotEmpty(webSite)) {
					verifycode = OCRConnectUtils.getVerifycode(
							"/gsxt/gansu/getVerifycode", imageUrl); // OCR
				} else {
					verifycode = OCRConnectUtils.getVerifycode(
							"/cjy/getVerifycode", imageUrl, "6003"); // 默认使用6003识别方式
				}
				webParam.getParams().put("imagecode", verifycode);
				String firstInfoPage = null;
				try {
					webClient.getOptions().setTimeout(120000); // 得到列表页超时时间均设置为120s
					firstInfoPage = getFirstInfoPage(webParam, searchPage);
				} finally {
					webClient.getOptions().setTimeout(
							WebCrawler.DEFAULT_PAGE_TIME_OUT);
				}
				// 得到目标信息
				resultHtmlMap = getHtmlInfoMap(webSite, searchPage,
						firstInfoPage, keyword, LOGGER);
				if (resultHtmlMap == null) {
					resultHtmlMap = new LinkedHashMap<String, Object>();
				}
				String alertMsg = WebCrawler.getAlertMsg();
				if (firstInfoPage.contains("非法字符")
						|| (alertMsg != null && alertMsg.contains("非法字符"))) {
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.ILLEGAL_CHAR);
					resultHtmlMap.put("searchPageHtml",
							IPUtil.getHostAndIpStr() + firstInfoPage);
				}
				if (firstInfoPage.contains("可能访问过于频繁或非正常访问")) {
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.FREQUENCY_LIMITED);
					resultHtmlMap.put("searchPageHtml",
							IPUtil.getHostAndIpStr() + firstInfoPage);
				}
				resultHtmlMap.put("imageUrl", imageUrl);
				resultHtmlMap.put("imagecode", verifycode);
			} else {
				resultHtmlMap = new LinkedHashMap<String, Object>();
				resultHtmlMap.put("statusCodeDef",
						statusCodeDefOfGetSearchPageByUrl);
				String searchPageHtml = webParam.getParams().get(
						"searchPageHtml");
				resultHtmlMap.put("searchPageHtml", searchPageHtml);
				String isImageNull = webParam.getParams().get("isImageNull");
				resultHtmlMap.put("isImageNull", isImageNull);
			}
		} finally {
			LOGGER.returnRedisResource();
		}
		return new GsonBuilder().setPrettyPrinting().create()
				.toJson(resultHtmlMap);

	}

	private HtmlPage getSearchPageByUrl(WebClient webClient, WebParam webParam)
			throws Exception {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CommonFunction.class, webParam.getLogback());
		HtmlPage searchPage = null;
		try {
			String searchPageUrl = webParam.getSearchPage();
			if (StringUtils.isEmpty(searchPageUrl)) {
				LOGGER.error("The searchPageUrl is not defined!");
				return null;
			}
			// 清除之前的cookie
			clearCookies(webClient, new URL(searchPageUrl));
			String searchPageHtml = "";
			WebRequest webRequest = new WebRequest(new URL(searchPageUrl),
					HttpMethod.GET);
			searchPage = webClient.getPage(webRequest);
			searchPageHtml = searchPage.asXml();
			LOGGER.info("searchPageHtml:" + searchPageHtml);
			if (searchPageHtml.contains("可能访问过于频繁或非正常访问")) {
				webParam.getParams().put("statusCodeDef",
						StatusCodeDef.FREQUENCY_LIMITED);
				webParam.getParams().put("searchPageHtml",
						IPUtil.getHostAndIpStr() + searchPageHtml);
				return searchPage;
			}
			HtmlImage image = searchPage.getFirstByXPath(webParam
					.getCodeImageId());
			if (image == null) {
				webParam.getParams().put("statusCodeDef",
						StatusCodeDef.IMAGECODE_ERROR);
				webParam.getParams().put("searchPageHtml",
						IPUtil.getHostAndIpStr() + searchPageHtml);
				webParam.getParams().put("isImageNull", "true");
				return searchPage;
			}
			File parentDirFile = new File(StormTopologyConfig.getNfs_filepath());
			parentDirFile.setReadable(true);
			parentDirFile.setWritable(true);
			if (!parentDirFile.exists()) {
				parentDirFile.mkdirs();
			}
			String imageName = UUID.randomUUID() + ".jpg";
			File codeImageFile = new File(StormTopologyConfig.getNfs_filepath()
					+ "/" + imageName);
			codeImageFile.setReadable(true);
			codeImageFile.setWritable(true);
			if (image != null
					&& (StringUtils.isEmpty(image.getAttribute("src")) || image
							.getAttribute("src").trim().equals("#"))) { // 验证码图片标签不为空，而其属性src为空，验证码特殊处理
				image.click();
			}
			try {
				image.saveAs(codeImageFile);
				LOGGER.info("----codeImageFile saved!----");
				LOGGER.info("The codeImageFile of CommonFunction.getSearchPageByUrl is:"
						+ codeImageFile.getAbsolutePath());
				String imageUrl = "http://"
						+ StormTopologyConfig.getNfs_nginx_server() + "/"
						+ imageName;
				LOGGER.info("The imageUrl is: " + imageUrl);
				webParam.getParams().put("imageUrl", imageUrl);
				webParam.getParams()
						.put("statusCodeDef", StatusCodeDef.SCCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.info("----codeImageFile saveAs fail!----");
				webParam.getParams().put("statusCodeDef",
						StatusCodeDef.IMAGECODE_ERROR);
				webParam.getParams().put("searchPageHtml",
						IPUtil.getHostAndIpStr() + "保存图片（saveAs）方法异常！");
			}
		} finally {
			LOGGER.returnRedisResource();
		}
		return searchPage;

	}

	private String getFirstInfoPage(WebParam webParam, HtmlPage searchPage)
			throws Exception {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CommonFunction.class, webParam.getLogback());
		HtmlPage firstInfoPage = null;
		String contentAsString = "";
		try {
			// 请求参数
			Map<String, String> params = webParam.getParams();
			String keyword = params.get("keyword").trim(); // 关键字
			String imagecode = params.get("imagecode"); // 图片验证码
			if (StringUtils.isEmpty(imagecode)) {
				imagecode = "0000";
			}
			String orgName = URLEncoder.encode(keyword, "utf-8");
			firstInfoPage = searchPage.getWebClient().getPage(
					"http://cx.cnca.cn/rjwcx/web/cert/queryOrg.do?certNumber=&orgName="
							+ orgName + "&queryType=public&checkCode="
							+ imagecode);
			WebResponse webResponse = firstInfoPage.getWebResponse();
			contentAsString = webResponse.getContentAsString();
			LOGGER.info("CommonFunction.getResultForCncaCompany start!======"
					+ contentAsString);
			// if (contentAsString.contains("验证码不正确!")
			// || contentAsString.contains("\"success\":false")) {
			// resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
			// resultData.message = "验证码输入有误！";
			// resultData.html = contentAsString;
			// } else {
			// resultData.statusCode = StatusCodeDef.SCCCESS;
			// resultData.message = "查询相关的企业或个人认证信息成功！";
			// resultData.html = contentAsString;
			// }
			LOGGER.info("The firstInfoPage is:" + firstInfoPage.asXml());
			LOGGER.info("The firstInfoPage Url is:" + firstInfoPage.getUrl());
		} finally {
			LOGGER.returnRedisResource();
		}
		return contentAsString;

	}

	private Map<String, Object> getHtmlInfoMap(String webSite,
			HtmlPage searchPage, String firstInfoPage, String keyword,
			ChannelLogger LOGGER) throws Exception {

		LOGGER.info("=========" + webSite + "=========" + keyword + "=========");

		Map<String, Object> resultHtmlMap = new LinkedHashMap<String, Object>();

		if (StringUtils.isEmpty(firstInfoPage)) {
			resultHtmlMap.put("statusCodeDef", StatusCodeDef.FAILURE);
		} else {
			Gson gson = new GsonBuilder().create();
			CertificateCompanyData prd = gson.fromJson(firstInfoPage,
					new TypeToken<CertificateCompanyData>() {
					}.getType());
			if (null != prd
					&& (prd.getMsg().contains("验证码不正确") || prd.getMsg()
							.contains("\u9A8C\u8BC1\u7801\u4E0D\u6B63\u786E"))) {
				resultHtmlMap.put("statusCodeDef",
						StatusCodeDef.IMAGECODE_ERROR);
				LOGGER.info("验证码输入有误！");
			} else {
				List<CertificateCompany> certificateCompanyList = prd.getData();
				if (null != certificateCompanyList
						&& certificateCompanyList.size() > 0) {
					// List<List<Map<String, Object>>> ttt = new
					// ArrayList<List<Map<String, Object>>>();
					List<Map<String, Object>> wds = new ArrayList<Map<String, Object>>();
					for (CertificateCompany certificateCompany : certificateCompanyList) {
						HtmlPage cncaPage = searchPage
								.getWebClient()
								.getPage(
										"http://cx.cnca.cn/rjwcx/web/cert/list.do?progId=10&orgName="
												+ URLEncoder.encode(
														certificateCompany
																.getOrgName(),
														"utf-8")
												+ "&orgCode="
												+ certificateCompany
														.getOrgCode()
												+ "&method=queryCertByOrg&needCheck=false&checkC="
												+ certificateCompany
														.getCheckC()
												+ "&randomCheckCode="
												+ certificateCompany
														.getRandomCheckCode()
												+ "&queryType=public&page=1&rows=10&checkCode=");
						WebResponse webResponse = cncaPage.getWebResponse();
						String contentAsString = webResponse
								.getContentAsString();
						CncaResult prd1 = gson.fromJson(contentAsString,
								new TypeToken<CncaResult>() {
								}.getType());
						List<CncaData> cncaDatas = prd1.getRows();
						if (null != cncaDatas && cncaDatas.size() > 0) {
							// List<Map<String, Object>> wds = new
							// ArrayList<Map<String, Object>>();
							for (CncaData cncaData : cncaDatas) {
								if (cncaData.getCertiStatusName()
										.contains("有效")) {
									Map<String, Object> wd = new LinkedHashMap<String, Object>();
									String showtemp = cncaData.getShowtemp();
									String rzjgId = cncaData.getRzjgId();
									String certNo = cncaData.getCertNumber();
									String checkC = cncaData.getCheckC();
									String url = "";
									if ("1".equals(showtemp.trim())) {
										url = "http://cx.cnca.cn/rjwcx/web/cert/show.do?rzjgId="
												+ rzjgId
												+ "&certNo="
												+ certNo
												+ "&checkC=" + checkC;
									} else if ("2".equals(showtemp.trim())) {
										url = "http://cx.cnca.cn/rjwcx/web/cert/show3C.do?rzjgId="
												+ rzjgId
												+ "&certNo="
												+ certNo
												+ "&checkC=" + checkC;
									} else if ("3".equals(showtemp.trim())) {
										url = "http://cx.cnca.cn/rjwcx/web/cert/showNs.do?rzjgId="
												+ rzjgId
												+ "&certNo="
												+ certNo
												+ "&checkC=" + checkC;
									} else if ("4".equals(showtemp.trim())) {
										url = "http://cx.cnca.cn/rjwcx/web/cert/show.do.do?rzjgId="
												+ rzjgId
												+ "&certNo="
												+ certNo
												+ "&checkC=" + checkC;
									} else {
										url = "http://cx.cnca.cn/rjwcx/web/cert/showZyxGy.do?rzjgId="
												+ rzjgId
												+ "&certNo="
												+ certNo
												+ "&checkC=" + checkC;
									}
									HtmlPage detailPage = searchPage
											.getWebClient().getPage(url);
									wd.put("rzrkywxx", detailPage.asXml());
									wds.add(wd);
								}
							}
							// ttt.add(wds);
						}
					}
					if (null != wds && wds.size() > 0) {
						// resultHtmlMap.put("allCertificate", ttt);
						resultHtmlMap.put("allCertificate", wds);
						resultHtmlMap.put("statusCodeDef",
								StatusCodeDef.SCCCESS);
					} else {
						resultHtmlMap.put("statusCodeDef",
								StatusCodeDef.NO_DATA_FOUND);
						LOGGER.info("没有该公司的相关有效认证信息！");
					}
				} else {
					resultHtmlMap.put("statusCodeDef",
							StatusCodeDef.NO_DATA_FOUND);
					LOGGER.info("没有找到该公司的相关信息！");
				}
			}
		}

		LOGGER.returnRedisResource();

		return resultHtmlMap;

	}

	// 认证认可业务信息统一查询平台
	// private ResultData getResultForCnca(HtmlPage firstInfoPage,
	// WebParam webParam) throws IOException {
	//
	// ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
	// CommonFunction.class, webParam.getLogback());
	//
	// LOGGER.info("===============CommonFunction.getResultForCnca start!============");
	//
	// ResultData resultData = new ResultData();
	//
	// if (null == firstInfoPage) {
	//
	// resultData.statusCode = StatusCodeDef.FAILURE;
	// resultData.message = "查询相关的企业或个人信息失败！";
	//
	// } else {
	//
	// LOGGER.info("CommonFunction.getResultForCnca start!======" +
	// firstInfoPage.asXml());
	//
	// HtmlElement rzqy_div = (HtmlElement) firstInfoPage
	// .getFirstByXPath("//div[@class='panel window messager-window']/div[@class='messager-body panel-body panel-body-noborder window-body']/div[2]");
	// if (null != rzqy_div) {
	// String textContent = rzqy_div.getTextContent().trim();
	// if (textContent.contains("验证码不正确!")) {
	// resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
	// resultData.message = "验证码输入有误！";
	// resultData.html = firstInfoPage.asXml();
	// } else if (textContent.contains("未查询到符合条件的获证组织信息!")) {
	// resultData.statusCode = StatusCodeDef.NO_DATA_FOUND;
	// resultData.message = "没有查询到相关的企业或个人信息！";
	// resultData.html = firstInfoPage.asXml();
	// }
	// } else {
	// @SuppressWarnings("unchecked")
	// List<HtmlElement> rzqy_tds = (List<HtmlElement>) firstInfoPage
	// .getByXPath("//div[@id='org_data_panel']/table[@class='datagrid-btable']/tbody/tr/td[@field='orgName']");
	// if (null == rzqy_tds || rzqy_tds.isEmpty()) {
	// resultData.statusCode = StatusCodeDef.FAILURE;
	// resultData.message = "查询相关的企业或个人信息失败！";
	// resultData.html = firstInfoPage.asXml();
	// } else {
	// @SuppressWarnings("unchecked")
	// List<HtmlElement> rzqy_divs = (List<HtmlElement>) rzqy_tds
	// .get(0).getByXPath("//div");
	// if (null != rzqy_divs && !rzqy_divs.isEmpty()) {
	// String qy = rzqy_divs.get(0).getTextContent().trim();
	// if (null != qy && qy.length() > 2) {
	// resultData.statusCode = StatusCodeDef.SCCCESS;
	// resultData.message = "成功获取到列表页面！";
	// Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
	// @SuppressWarnings("unchecked")
	// List<HtmlElement> rzqy_onclicks = (List<HtmlElement>) firstInfoPage
	// .getByXPath("//div[@id='org_data_panel']/table[@class='datagrid-btable']/tbody/tr/td[@field='orgName']/div[1]");
	// List<Map<String, Object>> mhcx = new ArrayList<Map<String, Object>>();
	// for (HtmlElement rzqy_onclick : rzqy_onclicks) {
	// HtmlPage zslb_page = rzqy_onclick.click();
	// Map<String, Object> jbxxMap = new LinkedHashMap<String, Object>();
	// List<Map<String, String>> rzxqs = new ArrayList<Map<String, String>>();
	// @SuppressWarnings("unchecked")
	// List<HtmlElement> zsxqs = (List<HtmlElement>) zslb_page
	// .getByXPath("//div[@id='cert_data_panel']/table[@class='datagrid-btable']/tbody/tr");
	// HtmlElement wdd = rzqy_onclick
	// .getFirstByXPath("//div[1]");
	// jbxxMap.put("companyName", wdd.getTextContent()
	// .trim());
	// for (HtmlElement zsxq : zsxqs) {
	// HtmlElement wd = zsxq
	// .getFirstByXPath("//td[3]/div[1]");
	// if (wd.getTextContent().trim()
	// .contains("有效")) {
	// HtmlPage zsxq_page = zsxq.click();
	// Map<String, String> rzxqMap = new LinkedHashMap<String, String>();
	// rzxqMap.put("zsxq", zsxq_page.asXml());
	// rzxqs.add(rzxqMap);
	// }
	// }
	// jbxxMap.put("ydy", rzxqs);
	// mhcx.add(jbxxMap);
	// }
	// resultMap.put("mhcx", mhcx);
	// resultData.html = resultMap.toString();
	// } else {
	// resultData.statusCode = StatusCodeDef.NO_DATA_FOUND;
	// resultData.message = "没有查询到相关的企业或个人信息！";
	// resultData.html = firstInfoPage.asXml();
	// }
	// } else {
	// resultData.statusCode = StatusCodeDef.NO_DATA_FOUND;
	// resultData.message = "没有查询到相关的企业或个人信息！";
	// resultData.html = firstInfoPage.asXml();
	// }
	// }
	// }
	//
	// }
	//
	// LOGGER.returnRedisResource();
	//
	// return resultData;
	//
	// }
	// /**
	// * 暂时没有使用
	// *
	// * @param webClient
	// * @param webParam
	// * @param searchPage
	// * @return
	// * @throws Exception
	// */
	// @SuppressWarnings("unused")
	// private HtmlPage getFirstInfoPage(WebClient webClient, WebParam webParam,
	// HtmlPage searchPage) throws Exception {
	//
	// ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
	// CommonFunction.class, webParam.getLogback());
	//
	// LOGGER.info(searchPage.asXml());
	//
	// // 请求参数
	// Map<String, String> params = webParam.getParams();
	// String keywordXpath = params.get("keywordXpath");
	// String keyword = params.get("keyword").trim(); // 关键字
	// HtmlInput inputKeyword = (HtmlInput) searchPage
	// .getFirstByXPath(keywordXpath); // querySelector("#keyword");
	// inputKeyword.reset();
	// inputKeyword.setAttribute("value", keyword);
	// String imagecodeXpath = params.get("imagecodeXpath");
	// String imagecode = params.get("imagecode"); // 图片验证码
	// if (StringUtils.isEmpty(imagecode)) {
	// imagecode = "0000";
	// }
	// HtmlInput inputImagecode = (HtmlInput) searchPage
	// .getFirstByXPath(imagecodeXpath);
	// HtmlPage firstInfoPage = null;
	// if (inputImagecode != null && imagecode != null) {
	// inputImagecode.reset();
	// inputImagecode.setValueAttribute(imagecode);
	// String loginBtnXpath = params.get("loginBtnXpath");
	// HtmlElement loginButton = (HtmlElement) searchPage
	// .getFirstByXPath(loginBtnXpath);
	// firstInfoPage = loginButton.click();
	// }
	//
	// LOGGER.info("The firstInfoPage is:" + firstInfoPage.asXml());
	// LOGGER.info("The firstInfoPage Url is:" + firstInfoPage.getUrl());
	//
	// LOGGER.returnRedisResource();
	//
	// return firstInfoPage;
	//
	// }

	// // 根据URL地址获取查询结果
	// private HtmlPage getFirstInfoPageByUrl(WebParam webParam,
	// HtmlPage searchPage) throws Exception {
	//
	// ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
	// CommonFunction.class, webParam.getLogback());
	//
	// LOGGER.info("=============" + searchPage.asXml());
	//
	// // 请求参数
	// Map<String, String> params = webParam.getParams();
	// String imagecode = params.get("imagecode"); // 图片验证码
	// if (StringUtils.isEmpty(imagecode)) {
	// imagecode = "0000";
	// }
	// String url = webParam.getUrl(); // url
	// HtmlPage firstInfoPage = null;
	// if (imagecode != null) {
	// firstInfoPage = searchPage.getWebClient().getPage(url);
	// LOGGER.info("The firstInfoPage is:" + firstInfoPage.asXml());
	// LOGGER.info("The firstInfoPage Url is:" + firstInfoPage.getUrl());
	// }
	//
	// LOGGER.returnRedisResource();
	//
	// return firstInfoPage;
	//
	// }

}
