/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示->企业年报
 */
public class EntpubAnnreportInfo implements Serializable { //QygsQynbInfo
	private static final long serialVersionUID = 5683394885080247795L;
	//报送年度
	private String submitYear;
	//发布日期
	private String pubDateTime; //pubDate;
	//首次公示日期
	private String firstDate;
	//备注
	private String remarks; 
	//企业基本信息
	private EntpubAnnreportBaseInfo baseInfo; //qygsQynbJbInfo;
	//网站或网店信息
	private List<EntpubAnnreportWebsiteInfo> websiteInfos; //qygsQynbWzhwdInfos;
	//股东及出资信息
	private List<EntpubAnnreportStohrinvestInfo> stohrInvestInfos; //qygsQynbGdjczInfos;
	//对外投资信息
	private List<EntpubAnnreportExtinvestInfo> extInvestInfos; //qygsQynbDwtzInfos;
	//企业资产状况信息
	private EntpubAnnreportAssetInfo assetInfo; //qygsQynbQyzczkInfo;
	//对外提供保证担保信息
	private List<EntpubAnnreportExtguaranteeInfo> extGuaranteeInfos; //qygsQynbDwtgbzdbInfos;
	//股权变更信息
	private List<EntpubAnnreportEquchangeInfo> equChangeInfos; //qygsQynbGqbgInfos;
	//修改记录
	private List<EntpubAnnreportModifyInfo> changeInfos; //qygsQynbXgjlInfos;
	//行政許可情況	
	private List<EntpubAnnreportAdmlicenseInfo> admlicenseInfos;//新加
	//生产经营情况
	private List<EntpubAnnreportManageInfo> manageInfos;//新加
	
	//html内容
	private String html;
	public String getSubmitYear() {
		return submitYear;
	}
	public void setSubmitYear(String submitYear) {
		this.submitYear = submitYear;
	}
	public String getPubDateTime() {
		return pubDateTime;
	}
	public void setPubDateTime(String pubDateTime) {
		this.pubDateTime = pubDateTime;
	}
	public String getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}
	public EntpubAnnreportBaseInfo getBaseInfo() {
		return baseInfo;
	}
	public void setBaseInfo(EntpubAnnreportBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}
	public List<EntpubAnnreportWebsiteInfo> getWebsiteInfos() {
		return websiteInfos;
	}
	public void setWebsiteInfos(List<EntpubAnnreportWebsiteInfo> websiteInfos) {
		this.websiteInfos = websiteInfos;
	}
	public List<EntpubAnnreportStohrinvestInfo> getStohrInvestInfos() {
		return stohrInvestInfos;
	}
	public void setStohrInvestInfos(
			List<EntpubAnnreportStohrinvestInfo> stohrInvestInfos) {
		this.stohrInvestInfos = stohrInvestInfos;
	}
	public List<EntpubAnnreportExtinvestInfo> getExtInvestInfos() {
		return extInvestInfos;
	}
	public void setExtInvestInfos(List<EntpubAnnreportExtinvestInfo> extInvestInfos) {
		this.extInvestInfos = extInvestInfos;
	}
	public EntpubAnnreportAssetInfo getAssetInfo() {
		return assetInfo;
	}
	public void setAssetInfo(EntpubAnnreportAssetInfo assetInfo) {
		this.assetInfo = assetInfo;
	}
	public List<EntpubAnnreportExtguaranteeInfo> getExtGuaranteeInfos() {
		return extGuaranteeInfos;
	}
	public void setExtGuaranteeInfos(
			List<EntpubAnnreportExtguaranteeInfo> extGuaranteeInfos) {
		this.extGuaranteeInfos = extGuaranteeInfos;
	}
	public List<EntpubAnnreportEquchangeInfo> getEquChangeInfos() {
		return equChangeInfos;
	}
	public void setEquChangeInfos(List<EntpubAnnreportEquchangeInfo> equChangeInfos) {
		this.equChangeInfos = equChangeInfos;
	}
	public List<EntpubAnnreportModifyInfo> getChangeInfos() {
		return changeInfos;
	}
	public void setChangeInfos(List<EntpubAnnreportModifyInfo> changeInfos) {
		this.changeInfos = changeInfos;
	}
	
	
	public List<EntpubAnnreportAdmlicenseInfo> getAdmlicenseInfos() {
		return admlicenseInfos;
	}
	public void setAdmlicenseInfos(
			List<EntpubAnnreportAdmlicenseInfo> admlicenseInfos) {
		this.admlicenseInfos = admlicenseInfos;
	}
	public List<EntpubAnnreportManageInfo> getManageInfos() {
		return manageInfos;
	}
	public void setManageInfos(List<EntpubAnnreportManageInfo> manageInfos) {
		this.manageInfos = manageInfos;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "EntpubAnnreportInfo [submitYear=" + submitYear
				+ ", pubDateTime=" + pubDateTime + ", baseInfo=" + baseInfo
				+ ", websiteInfos=" + websiteInfos + ", stohrInvestInfos="
				+ stohrInvestInfos + ", extInvestInfos=" + extInvestInfos
				+ ", assetInfo=" + assetInfo + ", extGuaranteeInfos="
				+ extGuaranteeInfos + ", equChangeInfos=" + equChangeInfos
				+",admlicenseInfos="+admlicenseInfos+",manageInfos="+manageInfos
				+",remarks="+remarks
				+ ", changeInfos=" + changeInfos + ", html=" + html + "]";
	}
}
