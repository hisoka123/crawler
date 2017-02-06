package com.storm.domian;

import com.crawler.storm.def.SearchDetail;
import com.google.gson.Gson;

public class FunctionCallParam {
	
	private String function;
	
	private WebParam webEngineParam;
	
	private WeiboApiParam apiEngineParam;
	
	private WeiboLogonParam weiboLogonParam;
	
	private WeiboHandleParam weiboHandleEngineParam;
	
	private RssParam rssParam;
	
	private WeiboFriendShipApiParam weiboFriendShipApiParam; 
	private JsonParam JsonParam;
	
	private LogonParam logonParam;
	
	private PbccrcParam pbccrcParam;
	
	private CreditchinaParam creditchinaParam;
	
	public CreditchinaParam getCreditchinaParam() {
		return creditchinaParam;
	}

	public void setCreditchinaParam(CreditchinaParam creditchinaParam) {
		this.creditchinaParam = creditchinaParam;
	}

	public FunctionCallParam(){
		
	}
	
	public FunctionCallParam fromParam(String json){
		Gson gson = new Gson();  
		FunctionCallParam param = gson.fromJson(json, FunctionCallParam.class);
		return param;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public WebParam getWebEngineParam() {
		return webEngineParam;
	}

	public void setWebEngineParam(WebParam webEngineParam) {
		this.webEngineParam = webEngineParam;
	}

	public WeiboApiParam getApiEngineParam() {
		return apiEngineParam;
	}

	public void setApiEngineParam(WeiboApiParam apiEngineParam) {
		this.apiEngineParam = apiEngineParam;
	}

	public WeiboHandleParam getWeiboHandleEngineParam() {
		return weiboHandleEngineParam;
	}

	public void setWeiboHandleEngineParam(
			WeiboHandleParam weiboHandleEngineParam) {
		this.weiboHandleEngineParam = weiboHandleEngineParam;
	}

	public WeiboLogonParam getWeiboLogonParam() {
		return weiboLogonParam;
	}

	public void setWeiboLogonParam(WeiboLogonParam weiboLogonParam) {
		this.weiboLogonParam = weiboLogonParam;
	}
	
	
	public RssParam getRssParam() {
		return rssParam;
	}

	public void setRssParam(RssParam rssParam) {
		this.rssParam = rssParam;
	}
	 

	public WeiboFriendShipApiParam getWeiboFriendShipApiParam() {
		return weiboFriendShipApiParam;
	}

	public void setWeiboFriendShipApiParam(
			WeiboFriendShipApiParam weiboFriendShipApiParam) {
		this.weiboFriendShipApiParam = weiboFriendShipApiParam;
	}
  
	public JsonParam getJsonParam() {
		return JsonParam;
	}

	public void setJsonParam(JsonParam jsonParam) {
		JsonParam = jsonParam;
	}
	
	public LogonParam getLogonParam() {
		return logonParam;
	}

	public void setLogonParam(LogonParam logonParam) {
		this.logonParam = logonParam;
	}

	public PbccrcParam getPbccrcParam() {
		return pbccrcParam;
	}

	public void setPbccrcParam(PbccrcParam pbccrcParam) {
		this.pbccrcParam = pbccrcParam;
	}

	public String toJson(){
		Gson gson = new Gson();   
		return gson.toJson(this);
	}
	 
	private CustomsParam customsParam;
	private SearchDetail searchDetail;

	public CustomsParam getCustomsParam() {
		return customsParam;
	}
	public void setCustomsParam(CustomsParam customsParam) {
		this.customsParam = customsParam;
	}
	public SearchDetail getSearchDetail() {
		return searchDetail;
	}
	public void setSearchDetail(SearchDetail searchDetail) {
		this.searchDetail = searchDetail;
	}

	@Override
	public String toString() {
		return "FunctionCallParam [function=" + function + ", webEngineParam="
				+ webEngineParam + ", apiEngineParam=" + apiEngineParam
				+ ", weiboLogonParam=" + weiboLogonParam
				+ ", weiboHandleEngineParam=" + weiboHandleEngineParam
				+ ", rssParam=" + rssParam + ", weiboFriendShipApiParam="
				+ weiboFriendShipApiParam + ", JsonParam=" + JsonParam
				+ ", logonParam=" + logonParam + ", pbccrcParam=" + pbccrcParam
				+ ", creditchinaParam=" + creditchinaParam + ", customsParam="
				+ customsParam + ", searchDetail=" + searchDetail + "]";
	}
}
