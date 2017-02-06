package com.storm.domian;

public class WeiboFriendShipApiParam {
	
	private String sourceId;
	
	private String targetId;
	
	private String accessToken;

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "WeiboFriendShipApiParam [sourceId=" + sourceId + ", targetId="
				+ targetId + ", accessToken=" + accessToken + "]";
	}

	
	
	
}
