package com.module.ocr.utils;

import java.io.Serializable;

public class HandleResult implements Serializable { 
	private static final long serialVersionUID = 4381038398969604506L;
	
	private String status; //eg:IVerifyHandler.HANDLE_SUCCESS
	private String result;

	public HandleResult(String status, String result) {
		this.status = status;
		this.result = result;
	}

	public HandleResult() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "HandleResult [status=" + status + ", result=" + result + "]";
	}
}
