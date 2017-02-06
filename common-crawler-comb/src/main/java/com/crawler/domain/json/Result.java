/**
 * 
 */
package com.crawler.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 *
 */
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private T data;
	private ErrorMsg error;
	
	@GsonSkip
	private String html;

	public Result() {}
	public Result(T data, String html, boolean isDebug) {
		this.data = data;
		this.html = isDebug ? html : null;
	}

	public T getData() {
		return data;
	}

	public ErrorMsg getError() {
		return error;
	}
	public void setError(ErrorMsg error) {
		this.error = error;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public void setData(T data) {
		this.data = data;
	}
	public void debugMode(boolean isDebug) {
		if (!isDebug)
			this.html = null;
	}

	@Override
	public String toString() {
		return "Result [data=" + data + ", html=" + html + ", error=" + error
				+ "]";
	}

}
