package com.crawler.storm.def;

import com.google.gson.GsonBuilder;

public class IecmsParam {

	private String corp_code;
	private String corp_name;
	private String sc_code;
	private String codea;

	public IecmsParam() {
		super();
	}

	public IecmsParam(String corp_code, String corp_name, String sc_code,
			String codea) {
		super();
		this.corp_code = corp_code;
		this.corp_name = corp_name;
		this.sc_code = sc_code;
		this.codea = codea;
	}

	public String getCorp_code() {
		return corp_code;
	}

	public void setCorp_code(String corp_code) {
		this.corp_code = corp_code;
	}

	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}

	public String getSc_code() {
		return sc_code;
	}

	public void setSc_code(String sc_code) {
		this.sc_code = sc_code;
	}

	public String getCodea() {
		return codea;
	}

	public void setCodea(String codea) {
		this.codea = codea;
	}

	public String toJson() {
		return new GsonBuilder().create().toJson(this);
	}

	public IecmsParam fromJson(String iecmsParam) {
		return new GsonBuilder().create()
				.fromJson(iecmsParam, IecmsParam.class);
	}

}
