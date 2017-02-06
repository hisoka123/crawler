package com.crawler.cnca.domain.json;

import java.io.Serializable;

public class CncaResultData implements Serializable {

	private static final long serialVersionUID = 4536734028153927198L;

	private String statusCode;
	private String message;
	private String imageUrl;
	private String serializedFileName;
	private String html;

	public CncaResultData() {
		super();
	}

	public CncaResultData(String statusCode, String message,
			String serializedFileName, String imageUrl) {
		this.statusCode = statusCode;
		this.message = message;
		this.serializedFileName = serializedFileName;
		this.imageUrl = imageUrl;
	}

	public void debugMode(boolean isDebug) {
		if (!isDebug)
			this.html = null;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSerializedFileName() {
		return serializedFileName;
	}

	public void setSerializedFileName(String serializedFileName) {
		this.serializedFileName = serializedFileName;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String toString() {
		return "CncaResultData [statusCode=" + statusCode + ", message="
				+ message + ", imageUrl=" + imageUrl + ", serializedFileName="
				+ serializedFileName + ", html=" + html + "]";
	}

}
