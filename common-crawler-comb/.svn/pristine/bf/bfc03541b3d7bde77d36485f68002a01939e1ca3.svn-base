package com.crawler.pbccrc.htmlparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.crawler.domain.json.Result;
import com.crawler.pbccrc.domain.json.CreditCardDetail;
import com.crawler.pbccrc.domain.json.CreditRecord;
import com.crawler.pbccrc.domain.json.CreditSummary;
import com.crawler.pbccrc.domain.json.HousingLoanDetail;
import com.crawler.pbccrc.domain.json.OtherLoanDetail;
import com.crawler.pbccrc.domain.json.PbcCreditReportFeed;
import com.crawler.pbccrc.domain.json.QueryRecord;
import com.crawler.pbccrc.domain.json.ReportBase;
import com.crawler.pbccrc.domain.json.ReportData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class PbcCreditFeedParser extends AbstractPbccrcParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(PbcCreditFeedParser.class);
	private static final Map<String, String> creditCardDetailTypeMap;
	private static final Map<String, String> housingLoanDetailTypeMap;
	private static final Map<String, String> otherLoanDetailTypeMap;
	
	static {
		creditCardDetailTypeMap = new HashMap<String, String>();
		creditCardDetailTypeMap.put("透支超过60天的准贷记卡账户明细如下：", "2");
		creditCardDetailTypeMap.put("发生过逾期的贷记卡账户明细如下：", "1");
		creditCardDetailTypeMap.put("从未逾期过的贷记卡及透支未超过60天的准贷记卡账户明细如下：", "3");
		
		housingLoanDetailTypeMap = new HashMap<String, String>();
		housingLoanDetailTypeMap.put("从未逾期过的账户明细如下：", "2");
		housingLoanDetailTypeMap.put("发生过逾期账户明细如下：", "1");
		
		otherLoanDetailTypeMap = new HashMap<String, String>();
		otherLoanDetailTypeMap.put("发生过逾期的账户明细如下：", "1");
		otherLoanDetailTypeMap.put("从未逾期过的账户明细如下：", "2");
	}
	
	
	
	public PbcCreditReportFeed getPbcCreditFeed(String html) throws IOException{
		if (StringUtils.isEmpty(html)) { //html is null <==> Exception in storm
			return null;
		}
		
		LOGGER.info("=================PbcCreditFeedParser.getPbcCreditFeed begin=================");
		PbcCreditReportFeed pbcCreditReportFeed = new PbcCreditReportFeed();
		Elements eles = Jsoup.parseBodyFragment(html).select("body");
		if (eles==null || eles.isEmpty()) {
			return pbcCreditReportFeed;
		}
		Element ele = eles.get(0);
		if (getfirstChildElement(ele, "th div.h1 strong:contains(个人信用报告)")==null || getfirstChildElement(ele, "tr>td>strong:contains(报告编号：)")==null) {
			return pbcCreditReportFeed;
		}

		 /*
		 * 报告表头
		 */
		ReportBase reportBase = new ReportBase();
		Element ele_report_id = getfirstChildElement(ele, "tr>td>strong:contains(报告编号：)"); //报告编号
		String report_id = getElementText(ele_report_id);
		report_id = report_id.replaceAll("报告编号：", "").trim();
		reportBase.setReportId(report_id);
		Element ele_query_time = getfirstChildElement(ele, "tr>td>strong.p:contains(查询时间：)"); //查询时间
		String query_time = getElementText(ele_query_time);
		query_time = query_time.replaceAll("查询时间：", "").trim();
		reportBase.setQueryTime(query_time);
		Element ele_report_time = getfirstChildElement(ele, "tr>td>strong.p:contains(报告时间：)"); //报告时间
		String report_time = getElementText(ele_report_time);
		report_time = report_time.replaceAll("报告时间：", "").trim();
		reportBase.setReportTime(report_time);
		Element ele_realname = getfirstChildElement(ele, "tr>td>strong.p:contains(姓名：)"); //姓名
		String realname = getElementText(ele_realname);
		realname = realname.replaceAll("姓名：", "").trim();
		reportBase.setRealname(realname);
		Element ele_certificate_type = getfirstChildElement(ele, "tr>td>strong.p:contains(证件类型：)"); //证件类型
		String certificate_type = getElementText(ele_certificate_type);
		certificate_type = certificate_type.replaceAll("证件类型：", "").trim();
		reportBase.setCertificateType(certificate_type);
		Element ele_certificate_num = getfirstChildElement(ele, "tr>td>strong.p:contains(证件号码：)"); //证件号码
		String certificate_num = getElementText(ele_certificate_num);
		certificate_num = certificate_num.replaceAll("证件号码：", "").trim();
		reportBase.setCertificateNum(certificate_num);
		Element ele_is_married = getfirstChildElement(ele, "tr>td>strong.p:contains(未婚),tr>td>strong.p:contains(已婚)"); //婚否
		String marriage_status_str = getElementText(ele_is_married);
		String marriage_status = "";
		if (ele_is_married==null) {
			marriage_status = "未知";
		} else {
			marriage_status = "已婚".equals(marriage_status_str) ? "已婚" : "未婚";
		}
		reportBase.setMarriageStatus(marriage_status);
		pbcCreditReportFeed.setReportBase(reportBase);
		
		
		/*
		 * 通过字段个数和字段名找到相应的表格
		 */
		Element ele_table_creditSummery = null;		//信息概要
		Element ele_table_queryRecord_org = null; 	//机构查询记录明细
		Element ele_table_queryRecord_per = null; 	//个人查询记录明细
		Elements ele_tables = getElements(ele, "table");
		for (int i=0; i<ele_tables.size(); i++) {
			Elements ele_tds = getElements(ele_tables.get(i), "tbody>tr:eq(0)>td");
			int size = ele_tds.size();
			StringBuffer sb = new StringBuffer("--");
			for (Element ele_tb : ele_tds) {
				sb.append(getElementText(ele_tb) + "--");
			}
			if (size==4 && "-- --信用卡--购房贷款--其他贷款--".equals(sb.toString())) {
				ele_table_creditSummery = ele_tables.get(i);
				continue;
			}
			if (size==1 && ("--机构查询记录明细--".equals(sb.toString()))) {
				ele_table_queryRecord_org = ele_tables.get(i);
				continue;
			}
			if (size==1 && ("--个人查询记录明细--".equals(sb.toString()))) {
				ele_table_queryRecord_per = ele_tables.get(i);
				continue;
			}
		}
		
		
		 /*
		 * 信贷记录
		 */
		CreditRecord creditRecord = new CreditRecord();
		//信息概要
		CreditSummary creditSummary = new CreditSummary();
		Map<String, String> creditCards = new HashMap<String, String>();  //信用卡
		Map<String, String> housingLoans = new HashMap<String, String>(); //住房贷款
		Map<String, String> otherLoans = new HashMap<String, String>();	  //其他贷款
		String[] creditSummaryItem = {"accountNum", "activeNum", "overdueNum", "overdue90Num", "guaranteeNum"};
		Elements ele_table_trs_creditSummery = getElements(ele_table_creditSummery, "tbody tr:gt(0)"); 
		if (ele_table_trs_creditSummery!=null) {
			for (int i = 0; i < 5; i++) {
				Element ele_creditCards_item = getfirstChildElement(ele_table_trs_creditSummery.get(i), "td:eq(1)");
				Element ele_housingLoans_item = getfirstChildElement(ele_table_trs_creditSummery.get(i), "td:eq(2)");
				Element ele_otherLoans_item = getfirstChildElement(ele_table_trs_creditSummery.get(i), "td:eq(3)");
				creditCards.put(creditSummaryItem[i], getElementText(ele_creditCards_item).trim());
				housingLoans.put(creditSummaryItem[i], getElementText(ele_housingLoans_item).trim());
				otherLoans.put(creditSummaryItem[i], getElementText(ele_otherLoans_item).trim());
			}
		}
		creditSummary.setCreditCards(creditCards);
		creditSummary.setHousingLoans(housingLoans);
		creditSummary.setOtherLoans(otherLoans);
		creditRecord.setCreditSummary(creditSummary); //
		
		//信用卡明细
		Element ele_creditCardDetail_ol = getfirstChildElement(ele, "ol.p.olstyle:contains(透支超过60天的准贷记卡账户明细如下：),ol.p.olstyle:contains(发生过逾期的贷记卡账户明细如下：),ol.p.olstyle:contains(从未逾期过的贷记卡及透支未超过60天的准贷记卡账户明细如下：)");
		Elements ele_creditCardDetail_li_spans = getElements(ele_creditCardDetail_ol, "li,span");
		int creditCardDetailSpanNum = 0;
		if (ele_creditCardDetail_li_spans!=null && !ele_creditCardDetail_li_spans.isEmpty()) {
			Set<CreditCardDetail> creditCardDetails = new LinkedHashSet<CreditCardDetail>();
			CreditCardDetail creditCardDetail = null;
			for (int i = 0; i < ele_creditCardDetail_li_spans.size(); i++) {
				Element ele_creditCardDetail_li_span = ele_creditCardDetail_li_spans.get(i);
				if (ele_creditCardDetail_li_span.toString().contains("<span")) { //标题
					creditCardDetailSpanNum++;
					String creditCardDetailType = creditCardDetailTypeMap.get(getElementText(ele_creditCardDetail_li_span).trim());
					creditCardDetail = new CreditCardDetail();
					creditCardDetail.setType(creditCardDetailType);
					creditCardDetail.setDetails(new LinkedHashMap<String, String>());
				} else if (ele_creditCardDetail_li_span.toString().contains("<li")) { //列表
					creditCardDetail.getDetails().put((i+1-creditCardDetailSpanNum)+"", getElementText(ele_creditCardDetail_li_span).trim());
				}
				creditCardDetails.add(creditCardDetail);
			}
			creditRecord.setCreditCardDetails(new ArrayList<CreditCardDetail>(creditCardDetails));
		}
		
		//住房贷款明细
		Element ele_housingLoanDetail_ol = getfirstChildElement(ele, "span.h1:contains(购房贷款) + ol.p.olstyle:contains(从未逾期过的账户明细如下：),span.h1:contains(购房贷款) + ol.p.olstyle:contains(发生过的逾期的帐户明细如下：)");
		Elements ele_housingLoanDetail_li_spans = getElements(ele_housingLoanDetail_ol, "li,span");
		int housingLoanDetailSpanNum = 0;
		if (ele_housingLoanDetail_li_spans!=null && !ele_housingLoanDetail_li_spans.isEmpty()) {
			Set<HousingLoanDetail> housingLoanDetails = new LinkedHashSet<HousingLoanDetail>();
			HousingLoanDetail housingLoanDetail = null;
			for (int i = 0; i < ele_housingLoanDetail_li_spans.size(); i++) {
				Element ele_housingLoanDetail_li_span = ele_housingLoanDetail_li_spans.get(i);
				if (ele_housingLoanDetail_li_span.toString().contains("<span")) { //标题
					housingLoanDetailSpanNum++;
					String housingLoanDetailType = housingLoanDetailTypeMap.get(getElementText(ele_housingLoanDetail_li_span).trim());
					if ("发生过逾期账户明细如下:".equals(getElementText(ele_housingLoanDetail_li_span).trim())) {
						housingLoanDetailType = "1";
					}
					housingLoanDetail = new HousingLoanDetail();
					housingLoanDetail.setType(housingLoanDetailType);
					housingLoanDetail.setDetails(new LinkedHashMap<String, String>());
				} else if (ele_housingLoanDetail_li_span.toString().contains("<li")) { //列表
					housingLoanDetail.getDetails().put((i+1-housingLoanDetailSpanNum)+"", getElementText(ele_housingLoanDetail_li_span).trim());
				}
				housingLoanDetails.add(housingLoanDetail);
			}
			creditRecord.setHousingLoanDetails(new ArrayList<HousingLoanDetail>(housingLoanDetails));
		}
		
		//其他贷款
		Element ele_otherLoanDetail_ol = getfirstChildElement(ele, "span.h1:contains(其他贷款) + ol.p.olstyle:contains(发生过逾期的账户明细如下：),span.h1:contains(其他贷款) + ol.p.olstyle:contains(从未逾期过的账户明细如下：)");
		Elements ele_otherLoanDetail_li_spans = getElements(ele_otherLoanDetail_ol, "li,span");
		int otherLoanDetailSpanNum = 0;
		if (ele_otherLoanDetail_li_spans!=null && !ele_otherLoanDetail_li_spans.isEmpty()) {
			Set<OtherLoanDetail> otherLoanDetails = new LinkedHashSet<OtherLoanDetail>();
			OtherLoanDetail otherLoanDetail = null;
			for (int i = 0; i < ele_otherLoanDetail_li_spans.size(); i++) {
				Element ele_otherLoanDetail_li_span = ele_otherLoanDetail_li_spans.get(i);
				if (ele_otherLoanDetail_li_span.toString().contains("<span")) { //标题
					otherLoanDetailSpanNum++;
					String otherLoanDetailType = otherLoanDetailTypeMap.get(getElementText(ele_otherLoanDetail_li_span).trim());
					otherLoanDetail = new OtherLoanDetail();
					otherLoanDetail.setType(otherLoanDetailType);
					otherLoanDetail.setDetails(new LinkedHashMap<String, String>());
				} else if (ele_otherLoanDetail_li_span.toString().contains("<li")) { //列表
					otherLoanDetail.getDetails().put((i+1-otherLoanDetailSpanNum)+"", getElementText(ele_otherLoanDetail_li_span).trim());
				}
				otherLoanDetails.add(otherLoanDetail);
			}
			creditRecord.setOtherLoanDetails(new ArrayList<OtherLoanDetail>(otherLoanDetails));
		}
		
		//为他人担保信息
		Element ele_guaranteeInfoDetail_ol = getfirstChildElement(ele, "span.h1:contains(为他人担保信息) + ol.p.olstyle");
		Elements ele_guaranteeInfoDetail_lis = getElements(ele_guaranteeInfoDetail_ol, "li");
		if (ele_guaranteeInfoDetail_lis!=null && !ele_guaranteeInfoDetail_lis.isEmpty()) {
			Map<String, String> guaranteeInfoDetails = new LinkedHashMap<String, String>();
			for (int i = 0; i < ele_guaranteeInfoDetail_lis.size(); i++) {
				Element ele_guaranteeInfoDetail_li = ele_guaranteeInfoDetail_lis.get(i);
				guaranteeInfoDetails.put((i+1)+"", getElementText(ele_guaranteeInfoDetail_li));
			}
			creditRecord.setGuaranteeInfoDetails(guaranteeInfoDetails);
		}
		pbcCreditReportFeed.setCreditRecord(creditRecord);
		
		
		 /*
		 * 查询记录
		 */
		List<QueryRecord> queryRecords =new ArrayList<QueryRecord>();
		int index = 0;
		if (ele_table_queryRecord_org!=null) { //机构查询记录明细
			Elements ele_table_trs_queryRecord_org = getElements(ele_table_queryRecord_org, "tbody>tr");
			for (int i=0; i<ele_table_trs_queryRecord_org.size(); i++) {
				Element ele_table_tr_queryRecord_org = ele_table_trs_queryRecord_org.get(i);
				Elements ele_table_tds_queryRecord = getElements(ele_table_tr_queryRecord_org, "td");
				if (i==2 || ele_table_tds_queryRecord.size()!=4) {
					continue;
				}
				QueryRecord queryRecord = new QueryRecord();
				String num = ele_table_tds_queryRecord.get(0).text().trim();
				String queryDate = ele_table_tds_queryRecord.get(1).text().trim();
				String operator = ele_table_tds_queryRecord.get(2).text().trim();
				String queryCause = ele_table_tds_queryRecord.get(3).text().trim();
				queryRecord.setNum(num);
				queryRecord.setQueryDate(queryDate);
				queryRecord.setOperator(operator);
				queryRecord.setQueryCause(queryCause);
				queryRecord.setQueryType("机构查询");
				queryRecords.add(queryRecord);
				index++;
			}
		}
		if (ele_table_queryRecord_per!=null) { //个人查询记录明细
			Elements ele_table_trs_queryRecord_per = getElements(ele_table_queryRecord_per, "tbody>tr");
			for (int i=0; i<ele_table_trs_queryRecord_per.size(); i++) {
				Element ele_table_tr_queryRecord_per = ele_table_trs_queryRecord_per.get(i);
				Elements ele_table_tds_queryRecord = getElements(ele_table_tr_queryRecord_per, "td");
				if (i==2 || ele_table_tds_queryRecord.size()!=4) {
					continue;
				}
				QueryRecord queryRecord = new QueryRecord();
				String num = ele_table_tds_queryRecord.get(0).text().trim();
				String queryDate = ele_table_tds_queryRecord.get(1).text().trim();
				String operator = ele_table_tds_queryRecord.get(2).text().trim();
				String queryCause = ele_table_tds_queryRecord.get(3).text().trim();
				queryRecord.setNum((Integer.parseInt(num)+index)+"");
				queryRecord.setQueryDate(queryDate);
				queryRecord.setOperator(operator);
				queryRecord.setQueryCause(queryCause);
				queryRecord.setQueryType("个人查询");
				queryRecords.add(queryRecord);
			}
		}
		pbcCreditReportFeed.setQueryRecords(queryRecords);
		
		return pbcCreditReportFeed;
	}
	
	
	
	
	//解析信用卡明细部分字符串解析
	private static final String IS_QUASI_CREDIT_CARD = "isQuasiCreditCard"; //是否为准贷记卡
	private static final String ACCOUNT_STATUS = "accountStatus";			//账户状态
	private static final String CURRENCY = "currency";						//币种
	private static final String IS_OVERDUE = "isOverdue";					//是否发生过逾期
	private static final String ISSUE_DAY = "issueDay";						//发放日期
	private static final String ABORT_DAY = "abortDay";						//截至年月
	private static final String LIMIT = "limit";							//额度
	private static final String USED_LIMIT = "usedLimit";					//已使用额度
	private static final String OVERDUE_AMOUNT = "overdueAmount";			//逾期金额
	private static final String OVERDUE_NO = "overdueNo";					//最近5年内逾期次数
	private static final String OVERDUE_FOR_NO = "overdueForNo";			//最近5年内90天以上的逾期次数
	private static final String CANCELLATION_DAY = "cancellationDay";		//销户年月
	public PbcCreditReportFeed parseCreditCardDetails(PbcCreditReportFeed reportFeed) {
		List<CreditCardDetail> creditCardDetails = reportFeed.getCreditRecord().getCreditCardDetails();
		if (creditCardDetails==null || creditCardDetails.isEmpty()) {
			return reportFeed;
		}
		
		List<String> matchResultList = null;
		String matchResultStr = null;
		List<Map<String,Object>> parsedCreditCardDetails = new ArrayList<Map<String,Object>>();
		for (CreditCardDetail creditCardDetail : creditCardDetails) {
			Map<String, String> details = creditCardDetail.getDetails();
			Set<Entry<String, String>> detailsEntrySet = details.entrySet();
			for (Entry<String, String> detailEntry : detailsEntrySet) {
				Map<String,Object> parsedCreditCardDetail = new LinkedHashMap<String, Object>();
				
				//
				//String num = detailEntry.getKey();
				String detailItem = detailEntry.getValue();
				
				//是否为准贷记卡 若是则不解析
				if (detailItem.contains("准贷记卡")) {
					parsedCreditCardDetail.put(IS_QUASI_CREDIT_CARD, "是");
					parsedCreditCardDetails.add(parsedCreditCardDetail);
					continue;
				}
				parsedCreditCardDetail.put(IS_QUASI_CREDIT_CARD, "否");
				
				//账户状态
				if (detailItem.contains("已销户")) {
					parsedCreditCardDetail.put(ACCOUNT_STATUS, "销户");
				} else if (detailItem.contains("尚未激活")) {
					parsedCreditCardDetail.put(ACCOUNT_STATUS, "未激活");
				} else if (detailItem.contains("止付")) {
					parsedCreditCardDetail.put(ACCOUNT_STATUS, "止付");
				} else if (detailItem.contains("冻结")) {
					parsedCreditCardDetail.put(ACCOUNT_STATUS, "冻结");
				} else if (detailItem.contains("呆账")) {
					parsedCreditCardDetail.put(ACCOUNT_STATUS, "呆账");
				} else {
					parsedCreditCardDetail.put(ACCOUNT_STATUS, "正常");
				}
				
				//币种
				matchResultList = getSubStringByRegex(detailItem, "\uff08(?!.*\uff08)[\u4e00-\u9fa5].*?\u8d26\u6237\uff09");
				if (matchResultList.size()>0) {
					matchResultStr = matchResultList.get(0);
					matchResultStr = matchResultStr.replaceAll("\uff08|\u8d26\u6237\uff09", "");
					parsedCreditCardDetail.put(CURRENCY, matchResultStr);
				} else {
					parsedCreditCardDetail.put(CURRENCY, "其他");
				}
				
				//是否发生过逾期  & 逾期次数
				matchResultList = getSubStringByRegex(detailItem, "\u6700\u8fd15\u5e74\u5185\u6709\\d\\d?\u4e2a\u6708\u5904\u4e8e\u903e\u671f\u72b6\u6001");
				if (matchResultList.size()>0) {
					matchResultStr = matchResultList.get(0);
					matchResultStr = matchResultStr.replaceAll("\u6700\u8fd15\u5e74\u5185\u6709|\u4e2a\u6708\u5904\u4e8e\u903e\u671f\u72b6\u6001", "");
					parsedCreditCardDetail.put(OVERDUE_NO, matchResultStr);
					matchResultStr = Integer.parseInt(matchResultStr)>0 ? "是" : "否";
					parsedCreditCardDetail.put(IS_OVERDUE, matchResultStr);
				} else {
					parsedCreditCardDetail.put(OVERDUE_NO, "");
					parsedCreditCardDetail.put(IS_OVERDUE, "");
				}
				
				//发放日期
				matchResultList = getSubStringByRegex(detailItem, "^([12]\\d{3}\u5e74\\d\\d?\u6708\\d\\d?\u65e5)");
				if (matchResultList.size()>0) {
					matchResultStr = matchResultList.get(0).replaceAll("\u5e74|\u6708", ".").replace("\u65e5", "");
					parsedCreditCardDetail.put(ISSUE_DAY, matchResultStr);
				} else {
					parsedCreditCardDetail.put(ISSUE_DAY, "");
				}
				
				//截至年月
				matchResultList = getSubStringByRegex(detailItem, "\u622a\u81f3[12]\\d{3}\u5e74\\d\\d?\u6708");
				if (matchResultList.size()>0) {
					matchResultStr = matchResultList.get(0).replace("\u622a\u81f3", "").replace("\u5e74", ".").replace("\u6708", "");
					parsedCreditCardDetail.put(ABORT_DAY, matchResultStr);
				} else {
					parsedCreditCardDetail.put(ABORT_DAY, "");
				}
				
				//信用额度
				matchResultList = getSubStringByRegex(detailItem, "\u4fe1\u7528\u989d\u5ea6(\u6298\u5408\u4eba\u6c11\u5e01)?\\d+(,\\d{3})?");
				if (matchResultList.size()>0) {
					matchResultStr = matchResultList.get(0).replaceAll("\u4fe1\u7528\u989d\u5ea6(\u6298\u5408\u4eba\u6c11\u5e01)?|,", "");
					parsedCreditCardDetail.put(LIMIT, matchResultStr);
				} else {
					parsedCreditCardDetail.put(LIMIT, "");
				}
				
				//已使用额度
				matchResultList = getSubStringByRegex(detailItem, "\u5df2\u4f7f\u7528\u989d\u5ea6\\d+(,\\d{3})?");
				if (matchResultList.size()>0) {
					matchResultStr = matchResultList.get(0).replaceAll("\u5df2\u4f7f\u7528\u989d\u5ea6|,", "");
					parsedCreditCardDetail.put(USED_LIMIT, matchResultStr);
				} else {
					parsedCreditCardDetail.put(USED_LIMIT, "");
				}
				
				//逾期金额 OVERDUE_AMOUNT 
				matchResultList = getSubStringByRegex(detailItem, "\u903e\u671f\u91d1\u989d\\d+(,\\d{3})?");
				if (matchResultList.size()>0) {
					matchResultStr = matchResultList.get(0).replaceAll("\u903e\u671f\u91d1\u989d|,", "");
					parsedCreditCardDetail.put(OVERDUE_AMOUNT, matchResultStr);
				} else {
					parsedCreditCardDetail.put(OVERDUE_AMOUNT, "");	
				}
				
				//最近5年内90天以上的逾期次数 OVERDUE_FOR_NO	
				matchResultList = getSubStringByRegex(detailItem, "\u5176\u4e2d\\d\\d?\u4e2a\u6708\u903e\u671f\u8d85\u8fc790\u5929");
				if (matchResultList.size()>0) {
					matchResultStr = matchResultList.get(0).replaceAll("\u5176\u4e2d|\u4e2a\u6708\u903e\u671f\u8d85\u8fc790\u5929", "");
					parsedCreditCardDetail.put(OVERDUE_FOR_NO, matchResultStr);
				} else {
					parsedCreditCardDetail.put(OVERDUE_FOR_NO, "0");
				}
				
				//销户年月 CANCELLATION_DAY
				matchResultList = getSubStringByRegex(detailItem, "[12]\\d{3}\u5e74\\d\\d?\u6708\u5df2\u9500\u6237");
				if (matchResultList.size()>0) {
					matchResultStr = matchResultList.get(0).replace("\u5e74", ".").replace("\u6708\u5df2\u9500\u6237", "");
					parsedCreditCardDetail.put(CANCELLATION_DAY, matchResultStr);
				} else {
					parsedCreditCardDetail.put(CANCELLATION_DAY, "");
				}
				parsedCreditCardDetails.add(parsedCreditCardDetail);
			}
		}
		reportFeed.getCreditRecord().setParsedCreditCardDetails(parsedCreditCardDetails);
		return reportFeed;
	}
	
	
	//贷款明细部分字符串解析
	private static final String LOAN_ACCOUNT_STATUS = "accountStatus"; 	//账户状态*
	private static final String LOAN_CURRENCY = "currency";				//贷款种类		
	private static final String LOAN_IS_OVERDUE = "isOverdue"; 			//是否发生过逾期* 	
	private static final String LOAN_ISSUE_DAY = "issueDay"; 			//发放日期*		
	private static final String LOAN_ABORT_DAY = "abortDay"; 			//到期日期*		
	private static final String LOAN_ACTUAL_DAY = "actualDay"; 			//截至年月*		
	private static final String LOAN_CONTEACT_AMOUNT = "conteactAmount";//贷款合同金额*	
	private static final String LOAN_LOAN_BALANCE = "loanBalance"; 		//贷款余额*		
	private static final String LOAN_OVERDUE_AMOUNT = "overdueAmount";	//逾期金额*		
	private static final String LOAN_OVERDUE_NO = "overdueNo";			//最近5年内逾期次数* 
	private static final String LOAN_OVERDUE_FOR_NO = "overdueForNo";	//最近5年内90天以上的逾期次数  
	private static final String LOAN_SETTLE_DAY = "settleDay";			//结清年月		
	
	private Map<String, Object> parseCommonPartOfLoadDetails(String detailItem, Map<String, Object> parsedLoanDetail) {
		List<String> matchResultList = null;
		String matchResultStr = null;
		//贷款种类
		matchResultList = getSubStringByRegex(detailItem, "[\u5e01\u5143\u9551]{1}\uff09[\u4e00-\u9fa5\uff08\uff09]+\uff0c");
		if (matchResultList.size()>0) {
			matchResultStr = matchResultList.get(0);
			matchResultStr = matchResultStr.replaceAll("[\u5e01\u5143\u9551]{1}\uff09|\uff0c", "");
			parsedLoanDetail.put(LOAN_CURRENCY, matchResultStr);
		}
		
		//账户状态
		if (detailItem.contains("逾期")) {
			parsedLoanDetail.put(LOAN_ACCOUNT_STATUS, "逾期");
		} else if (detailItem.contains("结清")) {
			parsedLoanDetail.put(LOAN_ACCOUNT_STATUS, "结清");
		} else if (detailItem.contains("转出")) {
			parsedLoanDetail.put(LOAN_ACCOUNT_STATUS, "转出");
		} else if (detailItem.contains("呆账")) {
			parsedLoanDetail.put(LOAN_ACCOUNT_STATUS, "呆账");
		} else {
			parsedLoanDetail.put(LOAN_ACCOUNT_STATUS, "正常");
		}
		
		//是否发生过逾期* 		LOAN_IS_OVERDUE	& LOAN_OVERDUE_NO
		matchResultList = getSubStringByRegex(detailItem, "\u6700\u8fd15\u5e74\u5185\u6709\\d\\d?\u4e2a\u6708\u5904\u4e8e\u903e\u671f\u72b6\u6001");
		if (matchResultList.size()>0) {
			matchResultStr = matchResultList.get(0);
			matchResultStr = matchResultStr.replaceAll("\u6700\u8fd15\u5e74\u5185\u6709|\u4e2a\u6708\u5904\u4e8e\u903e\u671f\u72b6\u6001", "");
			parsedLoanDetail.put(LOAN_OVERDUE_NO, matchResultStr);
			matchResultStr = Integer.parseInt(matchResultStr)>0 ? "是" : "否";
			parsedLoanDetail.put(LOAN_IS_OVERDUE, matchResultStr);
		} else {
			parsedLoanDetail.put(LOAN_OVERDUE_NO, "");
			parsedLoanDetail.put(LOAN_IS_OVERDUE, "");
		}
		
		//发放日期*			LOAN_ISSUE_DAY 
		matchResultList = getSubStringByRegex(detailItem, "^([12]\\d{3}\u5e74\\d\\d?\u6708\\d\\d?\u65e5)");
		if (matchResultList.size()>0) {
			matchResultStr = matchResultList.get(0).replaceAll("\u5e74|\u6708", ".").replace("\u65e5", "");
			parsedLoanDetail.put(LOAN_ISSUE_DAY, matchResultStr);
		} else {
			parsedLoanDetail.put(LOAN_ISSUE_DAY, "");
		}
		
		//到期日期*			LOAN_ABORT_DAY 
		matchResultList = getSubStringByRegex(detailItem, "[12]\\d{3}\u5e74\\d\\d?\u6708\\d\\d?\u65e5\u5230\u671f");
		if (matchResultList.size()>0) {
			matchResultStr = matchResultList.get(0).replaceAll("\u5e74|\u6708", ".").replace("\u65e5\u5230\u671f", "");
			parsedLoanDetail.put(LOAN_ABORT_DAY, matchResultStr);
		} else {
			parsedLoanDetail.put(LOAN_ABORT_DAY, "");
		}
		
		//截至年月*			LOAN_ACTUAL_DAY 
		matchResultList = getSubStringByRegex(detailItem, "\u622a\u81f3[12]\\d{3}\u5e74\\d\\d?\u6708");
		if (matchResultList.size()>0) {
			matchResultStr = matchResultList.get(0).replaceAll("\u622a\u81f3|\u6708", "").replace("\u5e74", ".");
			parsedLoanDetail.put(LOAN_ACTUAL_DAY, matchResultStr);
		} else {
			parsedLoanDetail.put(LOAN_ACTUAL_DAY, "");
		}
		
		//贷款合同金额*			LOAN_CONTEACT_AMOUNT
		matchResultList = getSubStringByRegex(detailItem, "\u53d1\u653e\u7684\\d+(,\\d{3})*");
		if (matchResultList.size()>0) {
			matchResultStr = matchResultList.get(0).replaceAll("\u53d1\u653e\u7684|,", "");
			parsedLoanDetail.put(LOAN_CONTEACT_AMOUNT, matchResultStr);
		} else {
			parsedLoanDetail.put(LOAN_CONTEACT_AMOUNT, "");
		}
		
		//贷款余额*			LOAN_LOAN_BALANCE 
		matchResultList = getSubStringByRegex(detailItem, "\u4f59\u989d\\d+(,\\d{3})*");
		if (matchResultList.size()>0) {
			matchResultStr = matchResultList.get(0).replaceAll("\u4f59\u989d|,", "");
			parsedLoanDetail.put(LOAN_LOAN_BALANCE, matchResultStr);
		} else {
			parsedLoanDetail.put(LOAN_LOAN_BALANCE, "");
		}
		
		//逾期金额*			LOAN_OVERDUE_AMOUNT 	
		matchResultList = getSubStringByRegex(detailItem, "\u903e\u671f\u91d1\u989d\\d+(,\\d{3})*");
		if (matchResultList.size()>0) {
			matchResultStr = matchResultList.get(0).replaceAll("\u903e\u671f\u91d1\u989d|,", "");
			parsedLoanDetail.put(LOAN_OVERDUE_AMOUNT, matchResultStr);
		} else {
			parsedLoanDetail.put(LOAN_OVERDUE_AMOUNT, "");
		}
		
		//最近5年内90天以上的逾期次数 	LOAN_OVERDUE_FOR_NO
		matchResultList = getSubStringByRegex(detailItem, "\u5176\u4e2d\\d\\d?\u4e2a\u6708\u903e\u671f\u8d85\u8fc790\u5929");
		if (matchResultList.size()>0) {
			matchResultStr = matchResultList.get(0).replaceAll("\u5176\u4e2d|\u4e2a\u6708\u903e\u671f\u8d85\u8fc790\u5929", "");
			parsedLoanDetail.put(LOAN_OVERDUE_FOR_NO, matchResultStr);
		} else {
			parsedLoanDetail.put(LOAN_OVERDUE_FOR_NO, "0");
		}
		
		//结清年月				LOAN_SETTLE_DAY	
		matchResultList = getSubStringByRegex(detailItem, "[12]\\d{3}\u5e74\\d\\d?\u6708\u5df2\u7ed3\u6e05");
		if (matchResultList.size()>0) {
			matchResultStr = matchResultList.get(0).replace("\u5e74", ".").replace("\u6708\u5df2\u7ed3\u6e05", "");
			parsedLoanDetail.put(LOAN_SETTLE_DAY, matchResultStr);
		} else {
			parsedLoanDetail.put(LOAN_SETTLE_DAY, "");
		}
		return parsedLoanDetail;
	}
	
	public PbcCreditReportFeed parseLoanDetails(PbcCreditReportFeed reportFeed) {
		List<Map<String,Object>> parsedLoanDetails = new ArrayList<Map<String,Object>>();
		
		//购房贷款明细
		List<HousingLoanDetail> housingLoanDetails = reportFeed.getCreditRecord().getHousingLoanDetails();
		if (housingLoanDetails!=null && !housingLoanDetails.isEmpty()) {
			for (HousingLoanDetail housingLoanDetail : housingLoanDetails) {
				Map<String, String> details = housingLoanDetail.getDetails();
				Set<Entry<String, String>> detailsEntrySet = details.entrySet();
				for (Entry<String, String> detailEntry : detailsEntrySet) {
					Map<String,Object> parsedLoanDetail = new LinkedHashMap<String, Object>();
					//
					//String num = detailEntry.getKey();
					String detailItem = detailEntry.getValue();
					
					//贷款种类
					parsedLoanDetail.put(LOAN_CURRENCY, "个人住房贷款");
					//解析除了贷款种类以外的字段
					parseCommonPartOfLoadDetails(detailItem, parsedLoanDetail);
					parsedLoanDetails.add(parsedLoanDetail);
				}
			}
		}
		
		//其他贷款明细
		List<OtherLoanDetail> otherLoanDetails = reportFeed.getCreditRecord().getOtherLoanDetails();
		if (otherLoanDetails!=null && !otherLoanDetails.isEmpty()) {
			for (OtherLoanDetail otherLoanDetail : otherLoanDetails) {
				Map<String, String> details = otherLoanDetail.getDetails();
				Set<Entry<String, String>> detailsEntrySet = details.entrySet();
				for (Entry<String, String> detailEntry : detailsEntrySet) {
					Map<String,Object> parsedLoanDetail = new LinkedHashMap<String, Object>();
					//
					//String num = detailEntry.getKey();
					String detailItem = detailEntry.getValue();
					
					//贷款种类
					parsedLoanDetail.put(LOAN_CURRENCY, "其他");
					//解析除了贷款种类以外的字段
					parseCommonPartOfLoadDetails(detailItem, parsedLoanDetail);
					parsedLoanDetails.add(parsedLoanDetail);
				}
			}
		}
		
		reportFeed.getCreditRecord().setParsedLoanDetails(parsedLoanDetails);
		return reportFeed;
	}
	
	
	
	//为他人担保信息
	private static final String GUARANTEED_PERSON = "guaranteedPerson"; 			//被担保人姓名
	private static final String GUARANTEED_PERSON_ID_NUM = "guaranteedPersonIdNum"; //被担保人身份证号
	private static final String OTHER_GUARANTEE_AMOUNT = "otherGuaranteeAmount";	//为他人贷款合同担保金额
	private static final String REAL_PRINCIPAL = "realPrincipal"; 					//被担保贷款实际本金余额
	private static final String ACTUAL_DAY = "actualDay"; 							//截至年月
	public PbcCreditReportFeed parseGuaranteeInfoDetails(PbcCreditReportFeed reportFeed) {
		List<Map<String,Object>> parsedGuaranteeInfoDetails = new ArrayList<Map<String,Object>>();
		List<String> matchResultList = null;
		String matchResultStr = null;
		
		Map<String, String> guaranteeInfoDetails = reportFeed.getCreditRecord().getGuaranteeInfoDetails();
		if (guaranteeInfoDetails==null || guaranteeInfoDetails.isEmpty()) {
			return reportFeed;
		}
		
		Set<Entry<String, String>> guaranteeInfoDetailsEntrySet = guaranteeInfoDetails.entrySet();
		for (Entry<String, String> guaranteeInfoDetailEntry : guaranteeInfoDetailsEntrySet) {
			Map<String, Object> parsedGuaranteeInfoDetail = new LinkedHashMap<String, Object>();
			
			//String num = guaranteeInfoDetailEntry.getKey();
			String detailItem = guaranteeInfoDetailEntry.getValue();
			
			//被担保人姓名
			matchResultList = getSubStringByRegex(detailItem, "\u4e3a[\u4e00-\u9fa5]+\uff08\u8bc1\u4ef6\u7c7b\u578b");
			if (matchResultList.size()>0) {
				matchResultStr = matchResultList.get(0).replaceAll("\u4e3a|\uff08\u8bc1\u4ef6\u7c7b\u578b", "");
				parsedGuaranteeInfoDetail.put(GUARANTEED_PERSON, matchResultStr);
			} else {
				parsedGuaranteeInfoDetail.put(GUARANTEED_PERSON, "");
			}
				
			//被担保人身份证号  GUARANTEED_PERSON_ID_NUM
			matchResultList = getSubStringByRegex(detailItem, "\u8eab\u4efd\u8bc1\uff0c\u8bc1\u4ef6\u53f7\u7801\uff1a(\\*+|\\d+)\\d{4}");
			if (matchResultList.size()>0) {
				matchResultStr = matchResultList.get(0).replace("\u8eab\u4efd\u8bc1\uff0c\u8bc1\u4ef6\u53f7\u7801\uff1a", "");
				parsedGuaranteeInfoDetail.put(GUARANTEED_PERSON_ID_NUM, matchResultStr);
			} else {
				parsedGuaranteeInfoDetail.put(GUARANTEED_PERSON_ID_NUM, "");
			}
			
			//为他人贷款合同担保金额  OTHER_GUARANTEE_AMOUNT
			matchResultList = getSubStringByRegex(detailItem, "\u62c5\u4fdd\u8d37\u6b3e\u5408\u540c\u91d1\u989d\\d+(,\\d{3})*");
			if (matchResultList.size()>0) {
				matchResultStr = matchResultList.get(0).replaceAll("\u62c5\u4fdd\u8d37\u6b3e\u5408\u540c\u91d1\u989d|,", "");
				parsedGuaranteeInfoDetail.put(OTHER_GUARANTEE_AMOUNT, matchResultStr);
			} else {
				parsedGuaranteeInfoDetail.put(OTHER_GUARANTEE_AMOUNT, "");
			}
			
			//被担保贷款实际本金余额  REAL_PRINCIPAL
			matchResultList = getSubStringByRegex(detailItem, "\u62c5\u4fdd\u8d37\u6b3e(\u672c\u91d1)?\u4f59\u989d\\d+(,\\d{3})*");
			if (matchResultList.size()>0) {
				matchResultStr = matchResultList.get(0).replaceAll("\u62c5\u4fdd\u8d37\u6b3e(\u672c\u91d1)?\u4f59\u989d|,", "");
				parsedGuaranteeInfoDetail.put(REAL_PRINCIPAL, matchResultStr);
			} else {
				parsedGuaranteeInfoDetail.put(REAL_PRINCIPAL, "");
			}
			
			//截至年月  ACTUAL_DAY
			matchResultList = getSubStringByRegex(detailItem, "\u622a\u81f3[12]\\d{3}\u5e74\\d\\d?\u6708\\d\\d?\u65e5");
			if (matchResultList.size()>0) {
				matchResultStr = matchResultList.get(0).replaceAll("\u622a\u81f3|\u65e5", "").replaceAll("\u5e74|\u6708", ".");
				parsedGuaranteeInfoDetail.put(ACTUAL_DAY, matchResultStr);
			} else {
				parsedGuaranteeInfoDetail.put(ACTUAL_DAY, "");
			}
			parsedGuaranteeInfoDetails.add(parsedGuaranteeInfoDetail);
		}
		
		reportFeed.getCreditRecord().setParsedGuaranteeInfoDetails(parsedGuaranteeInfoDetails);
		return reportFeed;
	}
	
	
	
	
	/*public static void main(String[] args) throws IOException {
		PbcCreditFeedParser pfp = new PbcCreditFeedParser();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String html = FileUtils.readFileToString(new File("E:/CODE-2015/工作日志/上容数据采集系统/doc/人行征信/人行征信报告收集/个人征信报告简版-2016年6月22日.htm"), "UTF-8");
		PbcCreditReportFeed reportFeed = pfp.getPbcCreditFeed(html);
		Result<ReportData> reportResult = new Result<ReportData>();
		//System.out.println("==============================解析前===================================");
		//System.out.println(gson.toJson(reportFeed));
		
		//解析信用卡明细
		reportFeed = pfp.parseCreditCardDetails(reportFeed);
		//解析贷款明细（住房贷款 和 其他贷款）
		reportFeed = pfp.parseLoanDetails(reportFeed);
		//解析担保信息明细
		reportFeed = pfp.parseGuaranteeInfoDetails(reportFeed);
		
		//System.out.println("\n\n==============================解析后===================================");
		ReportData reportData = new ReportData("0", "查询成功", reportFeed, null);
		reportResult.setData(reportData);
		System.out.println(gson.toJson(reportResult));
	}*/
}
