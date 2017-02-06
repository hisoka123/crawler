/**
 * 
 */
package com.dataservice.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * @author kingly
 * @date 2015年8月27日
 * 
 */
public class NetWorkHelper {

	public static String getCurrentURL(HttpServletRequest request) {
		StringBuffer url = new StringBuffer();
		String appName = request.getContextPath();

		if (appName != null && appName.trim().length() > 0
				&& !appName.trim().equals("/"))
			url.append(appName);
		String page = request.getServletPath();
		if (page != null)
			url.append(page);
		String queryString = request.getQueryString();
		if (queryString != null)
			url.append("?" + queryString);

		return url.toString();
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	public static String getBrowser(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		if (userAgent.contains("MSIE")) {
			return "ie";
		} else if (userAgent.contains("Firefox")) {
			return "firefox";
		} else if (userAgent.contains("Chrome")) {
			return "chrome";
		} else if (userAgent.contains("Safari")) {
			return "safari";
		} else if (userAgent.contains("Opera")) {
			return "opera";
		}
		return "other";
	}

}
