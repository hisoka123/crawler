package com.storm.function;

import java.io.File;
import java.io.IOException;
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

import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.SearchDetail;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.htmlunit.WebCrawler;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.module.ocr.utils.OCRConnectUtils;
import com.storm.def.StormTopologyConfig;
import com.storm.domian.CustomsParam;
import com.storm.domian.WebParam;

/**
 * 爬取中国海关企业进出口信用信息公示平台数据的核心模块
 */
public class CustomsFunction {

	public static String CUSTOMS_SEARCH = "http://credit.customs.gov.cn";

	public class ResultData {
		public String statusCode;
		public String message;
		public Set<Cookie> cookies;
		public String imageUrl;
		public String html;
	}

	// 将获取的数据对象转为json字符串
	private String parseResultDataToJson(ResultData resultData) {
		return new GsonBuilder().create().toJson(resultData);
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
	 * 查询页：获取查询页面cookies和图片验证码
	 * 
	 * @return ResultData（包含查询页cookies、验证码图片URL）
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation" })
	public String searchPageHandle(WebParam webParam) throws Exception {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CustomsFunction.class, webParam.getLogback());

		LOGGER.info("=====CustomsFunction.searchPageHandle is begin=====");
		ResultData resultData = new ResultData();
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		// webClient.getOptions().setUseInsecureSSL(true);

		// 清除cookies
		clearCookies(webClient, new URL(CustomsFunction.CUSTOMS_SEARCH));

		// 重设cookies
		// 初始化webClient，并get请求查询
		webClient.addRequestHeader("Origin", CustomsFunction.CUSTOMS_SEARCH);
		webClient.addRequestHeader("Referer", CustomsFunction.CUSTOMS_SEARCH);
		WebRequest requestSearch = new WebRequest(new URL(
				CustomsFunction.CUSTOMS_SEARCH), HttpMethod.GET);

		HtmlPage searchPage = webClient.getPage(requestSearch);
		LOGGER.info("the return html of CustomsFunction.searchPageHandle is:"
				+ searchPage.asXml());
		HtmlImage image = (HtmlImage) searchPage
				.getFirstByXPath("//img[@src='/ccppCopAction/createImage.action']");
		if (image != null
				&& (StringUtils.isEmpty(image.getAttribute("src")) || image
						.getAttribute("src").trim().equals("#"))) { // 验证码图片标签不为空，而其属性src为空，验证码特殊处理
			image.click();
		}
		String resultDataToJson = "";

		if (null == image) {
			resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
			resultData.message = "获取查询页面失败，请重新获取查询页面！";
			webClient.removeRequestHeader("Origin");
			webClient.removeRequestHeader("Referer");
			resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("获取查询页面的验证码图片失败，请重新查询！");
		} else {
			Set<Cookie> cookies = webClient.getCookieManager().getCookies(
					new URL(CustomsFunction.CUSTOMS_SEARCH));
			resultData.cookies = cookies;
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
			LOGGER.info("The codeImageFile of CustomsFunction.searchPageHandle is:"
					+ codeImageFile.getAbsolutePath());
			image.saveAs(codeImageFile);
			LOGGER.info("----codeImageFile saved!----");
			resultData.imageUrl = "http://"
					+ StormTopologyConfig.getNfs_nginx_server() + "/"
					+ imageName;
			LOGGER.info("The imageUrl is: " + resultData.imageUrl);
			// 状态信息
			resultData.statusCode = StatusCodeDef.SCCCESS;
			resultData.message = "成功获取查询页cookies及图片验证码";
			webClient.removeRequestHeader("Origin");
			webClient.removeRequestHeader("Referer");
			resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of CustomsFunction.searchPageHandle is:"
					+ resultDataToJson);
		}

		LOGGER.returnRedisResource();

		return resultDataToJson;

	}

	/**
	 * 查询列表
	 * 
	 * @param customsParam
	 *            （查询页cookies、copName、imageCode）
	 * @return ResultData
	 * @throws IOException
	 * @throws FailingHttpStatusCodeException
	 */
	@SuppressWarnings("deprecation")
	public String search(CustomsParam customsParam, WebParam webParam)
			throws FailingHttpStatusCodeException, IOException {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CustomsFunction.class, webParam.getLogback());

		LOGGER.info("=====CustomsFunction.search is begin=====");
		ResultData resultData = new ResultData();

		// customsParam
		Set<Cookie> cookies = customsParam.getCookies();
		String copName = customsParam.getCopName();
		String randomCode = customsParam.getCodeImageValue();
		LOGGER.info(copName + "=====" + randomCode + "=====" + cookies.size());

		if (null == cookies || cookies.isEmpty()) {
			resultData.statusCode = StatusCodeDef.COOKIE_ERROR;
			resultData.message = "没有获取到查询页面的cookie！";
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of CustomsFunction.search is:"
					+ resultDataToJson);
			return resultDataToJson;
		}

		if (null == copName || StringUtils.isEmpty(copName)
				|| copName.length() < 2) {
			resultData.statusCode = StatusCodeDef.USERNAME_OR_PASSWORD_ERROR;
			resultData.message = "查询条件为空或小于两个字符串！";
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of CustomsFunction.search is:"
					+ resultDataToJson);
			return resultDataToJson;
		}

		if (null == randomCode || StringUtils.isEmpty(randomCode)) {
			resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
			resultData.message = "查询图片验证码为空！";
			String resultDataToJson = parseResultDataToJson(resultData);
			LOGGER.info("The return ResultDataJson of CustomsFunction.search is:"
					+ resultDataToJson);
			return resultDataToJson;
		}

		WebClient webClient = WebCrawler.getInstance().getWebClient();
		// webClient.getOptions().setUseInsecureSSL(true);

		// 初始化webClient，并post请求查询
		webClient.addRequestHeader("Origin", CustomsFunction.CUSTOMS_SEARCH);
		webClient.addRequestHeader("Referer", CustomsFunction.CUSTOMS_SEARCH);
		addCookies(webClient, cookies);
		WebRequest requestSearch = new WebRequest(new URL(
				"http://credit.customs.gov.cn/ccppCopAction/queryCop.action?copName="
						+ URLEncoder.encode(copName, "UTF-8")), HttpMethod.POST);
		List<NameValuePair> nameValuePairsSearch = new ArrayList<NameValuePair>();
		// nameValuePairsSearch.add(new NameValuePair("copName", copName));
		nameValuePairsSearch.add(new NameValuePair("randomCode", randomCode));
		nameValuePairsSearch.add(new NameValuePair("x", String.valueOf(Math
				.round(Math.random() * 100))));
		nameValuePairsSearch.add(new NameValuePair("y", String.valueOf(Math
				.round(Math.random() * 50))));
		requestSearch.setRequestParameters(nameValuePairsSearch);
		HtmlPage loggedPage = webClient.getPage(requestSearch);
		LOGGER.info("the return html of CustomsFunction.search is:"
				+ loggedPage.asXml());
		String alertMsg = WebCrawler.getAlertMsg();
		if (null != alertMsg && alertMsg.contains("输入的验证码不正确")) {
			resultData.html = loggedPage.asXml();
			resultData.statusCode = StatusCodeDef.IMAGECODE_ERROR;
			resultData.message = "验证码输入有误！";
		} else if (null != alertMsg && alertMsg.contains("没有符合条件的数据")) {
			resultData.html = loggedPage.asXml();
			resultData.statusCode = StatusCodeDef.NO_DATA_FOUND;
			resultData.message = "没有符合条件的数据！";
		} else {
			// 获取登录操作后的数据
			Set<Cookie> cookies2 = webClient.getCookieManager().getCookies(
					new URL(
							"http://credit.customs.gov.cn/ccppCopAction/queryCop.action?copName="
									+ URLEncoder.encode(copName, "UTF-8")));

			resultData.html = loggedPage.asXml();
			resultData.statusCode = StatusCodeDef.SCCCESS;
			resultData.message = "查询成功！";
			resultData.cookies = cookies2;
		}

		// 后处理
		webClient.removeRequestHeader("Origin");
		webClient.removeRequestHeader("Referer");

		// 返回
		String resultDataToJson = parseResultDataToJson(resultData);
		LOGGER.info("The return ResultDataJson of CustomsFunction.search is:"
				+ resultDataToJson);

		LOGGER.returnRedisResource();

		return resultDataToJson;

	}

	/**
	 * 查询详情页
	 * 
	 * @param searchPage
	 *            （查询页cookies、copName、imageCode）
	 * @return ResultData
	 * @throws IOException
	 * @throws FailingHttpStatusCodeException
	 */
	public String searchDetail(SearchDetail searchPage, WebParam webParam)
			throws FailingHttpStatusCodeException, IOException {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CustomsFunction.class, webParam.getLogback());

		String seqNo = searchPage.getSeqNo();
		String saicSysNo = searchPage.getSaicSysNo();
		LOGGER.info("searchDetail is begin=====args" + seqNo + "===args==="
				+ saicSysNo);

		ResultData resultData = new ResultData();
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		// webClient.getOptions().setUseInsecureSSL(true);

		// 初始化webClient，并get请求查询
		webClient.addRequestHeader("Origin", CustomsFunction.CUSTOMS_SEARCH);
		webClient.addRequestHeader("Referer",
				"http://credit.customs.gov.cn/ccppCopAction/getDetail.action?seqNo="
						+ seqNo + "&saicSysNo=" + saicSysNo);
		addCookies(webClient, searchPage.getCookies());
		WebRequest requestLogin = new WebRequest(new URL(
				"http://credit.customs.gov.cn/ccppCopAction/getDetail.action?seqNo="
						+ searchPage.getSeqNo() + "&saicSysNo="
						+ searchPage.getSaicSysNo()), HttpMethod.GET);
		HtmlPage loggedPage = webClient.getPage(requestLogin);

		resultData.html = loggedPage.asXml();
		resultData.statusCode = StatusCodeDef.SCCCESS;

		// 后处理
		webClient.removeRequestHeader("Origin");
		webClient.removeRequestHeader("Referer");

		// 返回
		String resultDataToJson = parseResultDataToJson(resultData);
		LOGGER.info("The return ResultDataJson of CustomsFunction.searchDetail is:"
				+ resultDataToJson);

		LOGGER.returnRedisResource();

		return resultDataToJson;

	}

	public String searchWithOCR(WebParam webParam) throws Exception {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CustomsFunction.class, webParam.getLogback());

		String searchPageHandle = searchPageHandle(webParam);
		ResultData resultData = new Gson().fromJson(searchPageHandle,
				new TypeToken<ResultData>() {
				}.getType());
		Map<String, Object> mapp = new LinkedHashMap<String, Object>();
		String tt = resultData.statusCode;
		if (StatusCodeDef.SCCCESS.equals(tt)) {
			webParam.setCookies(resultData.cookies);
			webParam.addParam("imageUrl", resultData.imageUrl);
			mapp = search(webParam);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(tt)) {
			mapp.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
		} else {
			mapp.put("statusCodeDef", StatusCodeDef.FAILURE);
		}

		LOGGER.returnRedisResource();

		return new GsonBuilder().setPrettyPrinting().create().toJson(mapp);

	}

	public Map<String, Object> search(WebParam webParam)
			throws FailingHttpStatusCodeException, IOException,
			InterruptedException {

		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(
				CustomsFunction.class, webParam.getLogback());

		try {

			LOGGER.info("=====CustomsFunction.search is begin=====");
			Map<String, Object> mapp = new LinkedHashMap<String, Object>();
			Set<Cookie> cookies = webParam.getCookies();
			String copName = webParam.getParams().get("keyword");
			String imageUrl = webParam.getParams().get("imageUrl");
			String randomCode = OCRConnectUtils.getVerifycode(
					"/gsxt/jiangsu/getVerifycode", imageUrl);
			LOGGER.info(copName + "=====" + randomCode + "====="
					+ cookies.isEmpty());

			if (null == cookies || cookies.isEmpty()) {
				mapp.put("statusCodeDef", StatusCodeDef.COOKIE_ERROR);
				LOGGER.info("没有获取到查询页面的cookies！");
				return mapp;
			}

			if (StringUtils.isEmpty(copName) || copName.length() < 2) {
				mapp.put("statusCodeDef",
						StatusCodeDef.USERNAME_OR_PASSWORD_ERROR);
				LOGGER.info("查询条件为空或小于两个字符串！");
				return mapp;
			}

			if (null == randomCode || StringUtils.isEmpty(randomCode)) {
				mapp.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
				mapp.put("isImageNull", "true");
				LOGGER.info("没有获取到正确的图片验证码！");
				return mapp;
			}

			WebClient webClient = WebCrawler.getInstance().getWebClient();
			// 初始化webClient，并post请求查询
			webClient
					.addRequestHeader("Origin", CustomsFunction.CUSTOMS_SEARCH);
			webClient.addRequestHeader("Referer",
					CustomsFunction.CUSTOMS_SEARCH);
			addCookies(webClient, cookies);
			WebRequest requestSearch = new WebRequest(new URL(
					"http://credit.customs.gov.cn/ccppCopAction/queryCop.action?copName="
							+ URLEncoder.encode(copName, "UTF-8")),
					HttpMethod.POST);
			List<NameValuePair> nameValuePairsSearch = new ArrayList<NameValuePair>();
			// nameValuePairsSearch.add(new NameValuePair("copName", copName));
			nameValuePairsSearch
					.add(new NameValuePair("randomCode", randomCode));
			nameValuePairsSearch.add(new NameValuePair("x", String.valueOf(Math
					.round(Math.random() * 100))));
			nameValuePairsSearch.add(new NameValuePair("y", String.valueOf(Math
					.round(Math.random() * 50))));
			requestSearch.setRequestParameters(nameValuePairsSearch);
			HtmlPage loggedPage = webClient.getPage(requestSearch);
			String alertMsg = WebCrawler.getAlertMsg();
			if (null != alertMsg && alertMsg.contains("输入的验证码不正确")) {
				mapp.put("statusCodeDef", StatusCodeDef.IMAGECODE_ERROR);
				LOGGER.info("验证码输入错误！");
			} else if (null != alertMsg && alertMsg.contains("没有符合条件的数据")) {
				mapp.put("statusCodeDef", StatusCodeDef.NO_DATA_FOUND);
				LOGGER.info("没有符合条件的数据！");
			} else {
				WebWindow webWindow = loggedPage.getWebClient()
						.getCurrentWindow();
				@SuppressWarnings("unchecked")
				List<HtmlElement> wds = (List<HtmlElement>) loggedPage
						.getByXPath("//div[@id='coplist']/div[@class='sub2-bg']");
				if (null != wds && !wds.isEmpty() && wds.size() > 0) {
					LOGGER.info("=====" + wds.toString() + "=====");
					List<String> yy = new ArrayList<String>();
					for (HtmlElement htmlElement : wds) {
						String xhhm = htmlElement.getAttribute("onclick");
						String mn = xhhm.split("\\(\'")[1];
						String[] aabb = mn.split("\'\\,\'");
						String aa = aabb[0];
						String bb = aabb[1].split("\'\\)")[0];
						Page wc = loggedPage.getWebClient().getPage(
								webWindow,
								new WebRequest(new URL(
										"http://credit.customs.gov.cn/ccppCopAction/getDetail.action?seqNo="
												+ aa + "&saicSysNo=" + bb)));
						if (null != wc && wc.isHtmlPage()) {
							HtmlPage tt = (HtmlPage) wc;
							yy.add(tt.asXml());
						}
					}
					mapp.put("creditCustoms", yy);
					mapp.put("statusCodeDef", StatusCodeDef.SCCCESS);
				} else {
					mapp.put("statusCodeDef", StatusCodeDef.FAILURE);
					LOGGER.info("查询数据失败！");
				}
			}

			// 后处理
			webClient.removeRequestHeader("Origin");
			webClient.removeRequestHeader("Referer");

			return mapp;

		} finally {

			LOGGER.returnRedisResource();

		}

	}

}
