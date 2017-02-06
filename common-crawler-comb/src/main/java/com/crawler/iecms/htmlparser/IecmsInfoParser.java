package com.crawler.iecms.htmlparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.crawler.htmlparser.AbstractParser;
import com.crawler.iecms.domain.json.IecmsJson;
import com.crawler.iecms.domain.json.UserFeedJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class IecmsInfoParser extends AbstractParser {

	private static final Logger logger = LoggerFactory
			.getLogger(IecmsInfoParser.class);

	public UserFeedJson parseResultHtml(String html) {
		UserFeedJson userFeedJson = new UserFeedJson();

		List<String> theadList = new ArrayList<String>();

		List<Map<String, String>> tbodyList = new ArrayList<Map<String, String>>();
		// <table align="center" class="listTableClass" cellspacing="1"
		// width="100%">
		Elements eles = Jsoup.parseBodyFragment(html).select("table");
		Element element = eles.get(2);
		if (element == null || eles.isEmpty()) {
			userFeedJson.setTipMessage("暂时没有数据！");
		} else {
			// 解析table中的数据
			Element tbody = Jsoup.parseBodyFragment(html).select("tbody")
					.get(2);

			Elements trElements = tbody.select("tr");
			int i = 0;
			for (Element trElement : trElements) {
				i = i + 1;
				if (i > 2) {
					Elements tdElement = trElement.select("td");

					Element element0 = tdElement.get(0);
					Element element1 = tdElement.get(1);
					Element element2 = tdElement.get(2);
					Element element3 = tdElement.get(3);
					Element element4 = tdElement.get(4);
					Map<String, String> map = new TreeMap<String, String>();

					map.put("jyzzwmc", element0.text());
					map.put("jyzywmc", element1.text());
					map.put("zs", element2.text());
					map.put("yb", element3.text());
					map.put("cz", element4.text());
					tbodyList.add(map);

				}
			}

		}

		userFeedJson.setTbody(tbodyList);

		return userFeedJson;
	}

	public List<IecmsJson> parseResultHtmliecms(String html) {
		List<IecmsJson> iecmsList = new ArrayList<IecmsJson>();
		Gson gson = new GsonBuilder().create();
		if (!"".equals(html) && null != html) {

		
		Map<String, Object> resultHtmlMap = gson.fromJson(html,
				new TypeToken<Map<String, Object>>() {
				}.getType());
		String dwmybaxt = (String) resultHtmlMap.get("dwmybaxt");
		// 得到页面注释中的数据
		if (!"".equals(dwmybaxt)) {
			String html1 = dwmybaxt.replaceAll("<!--", "");
			String html2 = html1.replaceAll("-->", "");
			dwmybaxt = html2;
		}

		Object dwmybaxt_detail_object = resultHtmlMap.get("dwmybaxt_detail");
		ArrayList<String> gdDetailList = null;
		if (dwmybaxt_detail_object != null) {
			gdDetailList = (ArrayList<String>) dwmybaxt_detail_object;

		}

		
		Elements eles = Jsoup.parseBodyFragment(dwmybaxt).select("table");
		if (eles.size() > 3) {
			Element element = eles.get(3);
			if (null != element) {
				Element tbody = element.select("tbody").get(0);
				Elements trElements = tbody.select("tr");
				int i = 0;
				for (Element trElement : trElements) {
					i = i + 1;
					if (i >= 2) {
						int m = 0;
						Elements tdElement = trElement.select("td");
						Element element0 = tdElement.get(0);
						if (element0.text().contains("暂时没有数据")) {
							break;
						}
						Element element1 = tdElement.get(1);
						Element element2 = tdElement.get(2);
						Element element3 = tdElement.get(3);
						Element element4 = tdElement.get(4);
						IecmsJson iecmsJson = new IecmsJson();
						// private Long id;//主键id
						// private String businessChineseName;//经营者中文名称
						iecmsJson.setBusinessChineseName(element0.text());
						// private String businessEnglishName;//经营者英文名称
						iecmsJson.setBusinessEnglishName(element1.text());
						// private String residence;//住所
						iecmsJson.setResidence(element2.text());
						// private String zipcode;//邮编
						iecmsJson.setZipcode(element3.text());
						// private String fax;//邮编
						iecmsJson.setFax(element4.text());
						// private Date executetime;//创建时间
						// 解析对应详情中的字段__________________________strat
						String dwmybaxt_detail = gdDetailList.get(m);
						if (dwmybaxt_detail != ""
								&& !"".equals(dwmybaxt_detail)) {
							Document gdxxDetailDoc = Jsoup
									.parse(dwmybaxt_detail);
							Element gdxxDetailDiv = gdxxDetailDoc
									.getElementById("divquery");
							if (gdxxDetailDiv != null) {
								Elements gdxxDetailTables = gdxxDetailDiv
										.select("table");
								if (gdxxDetailTables != null
										&& !gdxxDetailTables.isEmpty()) {
									Element gdxxDetailTable = gdxxDetailTables
											.get(0);
									if (gdxxDetailTable != null) {
										Elements gdxxDetailTrs = gdxxDetailTable
												.select("tr");
										Element gdxxDetailTr = gdxxDetailTrs
												.get(2);
										Elements gdxxDetail_tds = gdxxDetailTr
												.select("td");
										Element inputManageCode1 = getfirstChildElement(
												gdxxDetail_tds.get(1),
												"input[name=manage_code]");
										iecmsJson
												.setImportExportEnterpriseCode(getElementAttr(
														inputManageCode1,
														"value"));
										// System.out.println(gdxxDetail_tds.get(1).attr("value"));
										Element inputManageCode3 = getfirstChildElement(
												gdxxDetail_tds.get(3),
												"input[name=manage_code]");
										iecmsJson
												.setUnifiedSocialCreditCode(getElementAttr(
														inputManageCode3,
														"value"));
										// System.out.println(gdxxDetail_tds.get(3).val());
										// for (Element gdxxDetailTr :
										// gdxxDetailTrs) {
										// if(gdxxDetailTrs.size()==2){
										// Elements gdxxDetail_tds =
										// gdxxDetailTr.select("td");
										// iecmsJson.setImportExportEnterpriseCode(gdxxDetail_tds.get(1).text());
										// iecmsJson.setUnifiedSocialCreditCode(gdxxDetail_tds.get(3).text());
										// }
										//
										// }
									}
								}
							}
						}
						// //解析对应详情中的字段__________________________end
						iecmsList.add(iecmsJson);
						m++;

					}

				}
			}
		}
		}
		return iecmsList;
	}

}
