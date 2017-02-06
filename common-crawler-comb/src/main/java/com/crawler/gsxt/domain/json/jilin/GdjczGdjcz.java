package com.crawler.gsxt.domain.json.jilin;

import java.util.List;

/**
 * 企业公示信息->股东及出资信息->股东及出资信息
 */
public class GdjczGdjcz {

	private Czxx czxx;
	private List<Rjmx> rjxx;
	private List<Sjmx> sjxx;

	public Czxx getCzxx() {
		return czxx;
	}

	public void setCzxx(Czxx czxx) {
		this.czxx = czxx;
	}

	public List<Rjmx> getRjxx() {
		return rjxx;
	}

	public void setRjxx(List<Rjmx> rjxx) {
		this.rjxx = rjxx;
	}

	public List<Sjmx> getSjxx() {
		return sjxx;
	}

	public void setSjxx(List<Sjmx> sjxx) {
		this.sjxx = sjxx;
	}

	@Override
	public String toString() {
		return "GdjczGdjcz [czxx=" + czxx + ", rjxx=" + rjxx + ", sjxx=" + sjxx
				+ "]";
	}

}
