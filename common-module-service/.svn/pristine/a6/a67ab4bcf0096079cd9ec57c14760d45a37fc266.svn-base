package com.crawlermanage.service.aspect;

import org.apache.thrift7.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import backtype.storm.generated.DRPCExecutionException;
import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.storm.def.ExecptionMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.module.drpc.service.CrawlerEngineService;

@Component
public class CrawlerEngine {
	@Autowired
	private CrawlerEngineService crawlerEngineService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerEngine.class);
	
	private static Gson gson;
	static {
		gson = new GsonBuilder().create();
	}

	public <T>Result<T> execute(String param, Result<T> result) {
		String html = null;
		ErrorMsg errorMsg = null;
		try {
			html = crawlerEngineService.execute(param);
			LOGGER.info("html:{}", html);
			
			ExecptionMessage em = gson.fromJson(html, ExecptionMessage.class);
			if (em.getExceptionName()==null && em.getExceptionMsg()==null) {
				result.setHtml(html);
				return result;
			}
			
			errorMsg = new ErrorMsg();
			errorMsg.setIp(em.getIp());
			errorMsg.setHostName(em.getHostName());
			errorMsg.setErrorCode(getExceptionCode(em.getExceptionName()));
			if ("100019".equals(errorMsg.getErrorCode())) {
				errorMsg.setErrorName("缺少安全证书");
				errorMsg.setErrorMsg("程序爬取过程中缺少安全证书");
			} else {
				errorMsg.setErrorName(em.getExceptionName());
				errorMsg.setErrorMsg(em.getExceptionName() + ": " + em.getExceptionMsg());
			}
			result.setError(errorMsg);
		} catch (JsonSyntaxException e) {//
			result.setHtml(html);
			return result;
		} catch (TException e) {
			errorMsg = initErrorMsg(e);
			result.setError(errorMsg);
			return result;
		} catch (DRPCExecutionException e) {
			errorMsg = initErrorMsg(e);
			result.setError(errorMsg);
			return result;
		}
		
		return result;
	}
	
	
	public String execute(String param) {
		String html = null;
		try {
			html = crawlerEngineService.execute(param);
			LOGGER.info("html:{}", html);
		} catch (TException e) {
			e.printStackTrace();
		} catch (DRPCExecutionException e) {
			e.printStackTrace();
		}
		return html;
	}

	
	public String getExceptionCode(String exceptionName) {
		if (StringUtils.isEmpty(exceptionName)) {
			return null;
		} else if (exceptionName.contains("java.net.MalformedURLException: unknown protocol")) {
			return "100000";
		} else if (exceptionName.contains("java.net.MalformedURLException: no protocol")) {
			return "100001";
		} else if (exceptionName.contains("java.lang.RuntimeException: java.net.MalformedURLException: Missing host name in url")) {
			return "100002";
		} else if (exceptionName.contains("java.lang.Exception: username input text can not found")) {
			return "100003";
		} else if (exceptionName.contains("org.apache.http.conn.ConnectTimeoutException") && exceptionName.contains("failed: connect timed out")) {
			return "100004";
		} else if (exceptionName.contains("java.net.UnknownHostException") && exceptionName.contains("Name or service not known")) {
			return "100005";
		} else if (exceptionName.contains("java.net.UnknownHostException")) {
			return "100006";
		} else if (exceptionName.contains("org.jsoup.HttpStatusException: HTTP error fetching URL. Status=404")) {
			return "100007";
		} else if (exceptionName.contains("org.jsoup.HttpStatusException: HTTP error fetching URL. Status=400")) {
			return "100008";
		} else if (exceptionName.contains("java.lang.ClassCastException: com.gargoylesoftware.htmlunit.TextPage cannot be cast to com.gargoylesoftware.htmlunit.html.HtmlPage")) {
			return "100009";
		} else if (exceptionName.contains("java.lang.IllegalArgumentException: Malformed URL")) {
			return "100010";
		} else if (exceptionName.contains("java.lang.IllegalArgumentException: protocol = http host = null")) {
			return "100011";
		} else if (exceptionName.contains("java.lang.NullPointerException")) {
			return "100012";
		} else if (exceptionName.contains("DRPCExecutionException")) {
			return "100013";
		} else if (exceptionName.contains("java.net.SocketTimeoutException: Read timed out")) { //请求超时 GSXT还要继续爬取一次
			return "100014";
		} else if (exceptionName.contains("java.net.SocketException: Connection reset")) {
			return "100015";
		} else if (exceptionName.contains("StatusCodeDef.FREQUENCY_LIMITED")) {
			return "100016";
		} else if (exceptionName.contains("StatusCodeDef.IMAGECODE_ERROR")) {
			return "100017";
		} else if (exceptionName.contains("java.lang.IndexOutOfBoundsException")) {
			return "100018";
		} else if (exceptionName.contains("javax.net.ssl.SSLHandshakeException")) {
			return "100019";
		} else if (exceptionName.contains("org.apache.http.conn.HttpHostConnectException")) {
			return "100020";
		} else {
			return "111111";
		}
	}
	
	//初始化异常信息
	public ErrorMsg initErrorMsg(Exception e) {
		e.printStackTrace();
		ErrorMsg errorMsg = new ErrorMsg();
		errorMsg.setErrorCode(getExceptionCode(e.toString()));
		errorMsg.setErrorName(e.toString());
		//
		String separator = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer(e.getMessage() + separator);
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			sb.append(stackTraceElement.toString() + separator);
		}
		errorMsg.setErrorMsg(e.toString() + ": " + sb.toString());
		
		return errorMsg;
	}
	
	public static void main(String[] args) {
		DRPCExecutionException drpcExecutionException = new DRPCExecutionException();
		System.out.println(drpcExecutionException.toString());
	}
}





