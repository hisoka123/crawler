package com.crawlermanage.dao.result;

public class OwnerTaskResult {

	   //加入新任务   0，失败；1，成功
	   private int ownerTaskCode;
	   private String message;
	   
	   
	   
	public int getOwnerTaskCode() {
		return ownerTaskCode;
	}
	public void setOwnerTaskCode(int ownerTaskCode) {
		this.ownerTaskCode = ownerTaskCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "OwnerTaskResult [ownerTaskCode=" + ownerTaskCode + ", message="
				+ message + "]";
	}
	   
	   
	   
}
