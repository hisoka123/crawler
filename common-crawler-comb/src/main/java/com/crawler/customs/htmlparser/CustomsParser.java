package com.crawler.customs.htmlparser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.crawler.customs.domain.json.AdmPuInformation;
import com.crawler.customs.domain.json.CreditRate;
import com.crawler.customs.domain.json.Customs;
import com.crawler.customs.domain.json.SearchDetail;

@Component
public class CustomsParser extends AbstractCustomsParser {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomsParser.class);

	public Customs customsListParser(String html) {

		if (StringUtils.isEmpty(html)) {
			return null;
		}

		Customs customs = new Customs();

		LOGGER.info("CustomsParser.customsListParser begin html is {}", html);

		Element tabs = Jsoup.parseBodyFragment(html).getElementById("tabs");
		Element tabs1 = Jsoup.parseBodyFragment(html).getElementById("tabs-1");
		Element tabs2 = Jsoup.parseBodyFragment(html).getElementById("tabs-2");
		Element tabs3 = Jsoup.parseBodyFragment(html).getElementById("tabs-3");

		if (tabs == null) {
			return null;
		}

		Elements table1 = tabs1.select("tr");
		Elements table2 = tabs2.select("table").get(0).select("tbody");
		Elements table3 = tabs3.select("table").get(0).select("tbody");

		if (null != table1 && table1.size() > 0) {
			// for (Element table1_tr : table1) {
			// Elements table1_td = table1.select("td");
			// if (table1_td.size() > 3) {
			// String td_name1 = table1.select("td").get(0).text().trim();
			// String td_name2 = table1.select("td").get(2).text().trim();
			// switch (td_name1) {
			// case
			// "海关注册编码":customs.setcRCode(table1.get(1).select("td").get(1).text());//
			// 海关注册编码
			// break;
			// case "":
			// break;
			// case "":
			// break;
			// case "":
			// break;
			// case "":
			// break;
			// case "":
			// break;
			// case "":
			// break;
			// case "":
			// break;
			// case "":
			// break;
			// case "":
			// break;
			// case "":
			// break;
			// case "":
			// break;
			// default:
			// break;
			// }
			//
			// }
			//
			// }
			customs.setcRCode(table1.get(1).select("td").get(1).text());// 海关注册编码
			customs.setSocialCreditCode(table1.get(1).select("td").get(3)
					.text());// 社会信用代码
			customs.setRecordDate(table1.get(2).select("td").get(1).text());// 注册日期
			customs.setBarCode(table1.get(2).select("td").get(3).text());// 组织机构代码
			customs.setbCName(table1.get(3).select("td").get(1).text());// 企业中文名称
			customs.setCustomsName(table1.get(1).select("td").get(3).text());// 注册海关
			customs.setRegAddress(table1.get(4).select("td").get(1).text());// 工商注册地址
			customs.setAdmDivision(table1.get(5).select("td").get(1).text());// 行政区划
			customs.setEcoReg(table1.get(5).select("td").get(3).text());// 经济区划
			customs.setEconomicCategory(table1.get(6).select("td").get(1)
					.text());// 经济类型
			customs.setBusType(table1.get(6).select("td").get(3).text());// 经营类别
			customs.setBusinessLine(table1.get(7).select("td").get(1).text());// 行业种类
			customs.setCustomsValidity(table1.get(7).select("td").get(3).text());// 报关有效期
			customs.setCustomsMarks(table1.get(8).select("td").get(1).text());// 海关注销标志
			customs.setAnnualReport(table1.get(8).select("td").get(3).text());// 年报情况
		}

		Elements tr2 = table2.select("tr");
		if (null != tr2 && tr2.size() > 0) {
			List<CreditRate> creditRateList = new ArrayList<CreditRate>();
			for (Element aa : tr2) {
				Elements bb = aa.select("td");
				if (null != bb && bb.size() > 3) {
					CreditRate creditRate = new CreditRate();
					creditRate.setcId(bb.get(0).text());// 序号
					creditRate.setIdentifyTime(bb.get(1).text());// 认定时间
					creditRate.setLegalNumber(bb.get(2).text());// 法律文书编号
					creditRate.setQuatityRate(bb.get(3).text());// 信用等级
					creditRateList.add(creditRate);
				}
			}
			customs.setCreditRate(creditRateList);
		}

		Elements tr3 = table3.select("tr");
		if (null != tr3 && tr3.size() > 0) {
			List<AdmPuInformation> admPuInformationList = new ArrayList<AdmPuInformation>();
			for (Element td3 : tr3) {
				Elements cc = td3.select("td");
				if (null != cc && cc.size() > 3) {
					AdmPuInformation admPuInformation = new AdmPuInformation();
					admPuInformation.setaId(cc.get(0).text());// 序号
					admPuInformation.setParty(cc.get(1).text());// 当事人
					admPuInformation.setCaseNature(cc.get(2).text());// 案件性质
					admPuInformation.setPunishmentDate(cc.get(3).text());// 处罚日期
					admPuInformation.setPenaltyNumber(cc.get(4).text());// 行政处罚决定书编号
					admPuInformationList.add(admPuInformation);
				}
			}
			customs.setAdmPuInformation(admPuInformationList);
		}

		LOGGER.info("CustomsParser customsListParser:{}", customs);

		return customs;

	}

	public List<SearchDetail> getSearchArgs(String html) {

		List<SearchDetail> detailList = new ArrayList<SearchDetail>();

		Elements eles = Jsoup.parseBodyFragment(html).select("div.sub2-bg");
		for (Element ele : eles) {
			SearchDetail searchDetail = new SearchDetail();
			String attr = ele.attr("onclick");
			String[] aa = attr.split("\\D+");
			searchDetail.setSeqNo(aa[1]);
			searchDetail.setSaicSysNo(aa[2]);
			searchDetail.setbCName(ele.select("div.title-font").get(0).text());
			/*
			 * String res =
			 * ele.select("div.content-search").get(0).text().trim(); String[]
			 * bb = res.split("\\："); if (bb.length >= 6) {
			 * searchDetail.setcRCode(bb[1].trim());
			 * searchDetail.setCustomsName(bb[3].trim());
			 * searchDetail.setBusType(bb[5].trim()); }
			 */
			detailList.add(searchDetail);
		}

		return detailList;

	}

	/*
	 * public static void main(String[] args) throws IOException {
	 * 
	 * File wd = new File("d:/wt.txt"); InputStreamReader is = new
	 * InputStreamReader(new FileInputStream(wd), "UTF-8"); BufferedReader br =
	 * new BufferedReader(is); String html = ""; while (br.read() != -1) { html
	 * += br.readLine(); } System.out.println(html); br.close();
	 * 
	 * // String html = "aa"; Scanner sr = new Scanner(new File("d:/wd.txt"));
	 * // while(sr.hasNextLine()){ html += sr.nextLine(); } sr.close(); //
	 * System.out.println(html);
	 * 
	 * CustomsParser cp = new CustomsParser(); // Customs customsListParser =
	 * cp.customsListParser(html); List<SearchDetail> searchArgs =
	 * cp.getSearchArgs(html);
	 * 
	 * // System.out.println(customsListParser.getBarCode() + "=====" // +
	 * customsListParser.getBusType());
	 * System.out.println(searchArgs.get(0).getCustomsName
	 * ()+"======="+searchArgs.get(0).getbCName());
	 * 
	 * }
	 */

}
