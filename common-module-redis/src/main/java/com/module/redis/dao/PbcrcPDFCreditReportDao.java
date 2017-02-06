package com.module.redis.dao;

public interface PbcrcPDFCreditReportDao {
	public static final int EXPIRE_TIME = 24*3600; //24h
	public static final int DATABASE_INDEX02 = 2; //2库
	
	//保存 pbccrc用户的pdf征信报告url，保存期为24h
	public String cachePDFReportURL(String username, String pdfReportUrl);
	
	//根据用户名获取pdf征信报告的url
	public String getPDFReportURLByUserName(String username);
	
}
