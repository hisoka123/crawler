package com.crawler.cnca.htmlparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.crawler.cnca.domain.json.Certificate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class CncaParser extends AbstractCncaParser {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CncaParser.class);

	public Certificate cncaDetailParser(String html) {

		LOGGER.info("CncaParser.cncaDetailParser begin html is {}", html);

		if (StringUtils.isEmpty(html)) {
			return null;
		}

		Document jtxx = Jsoup.parseBodyFragment(html);
		Element rzzsxx = jtxx.getElementById("cert_info");
		Certificate certificate = null;// 证书信息
		if (null != rzzsxx) {
			certificate = new Certificate();// 证书信息
			Elements cert_tds = rzzsxx.select("td");
			for (Element cert_td : cert_tds) {
				String wd = cert_td.text().trim();
				switch (wd) {
				case "证书编号":
					certificate.setNumber(cert_td.nextElementSibling().text()
							.trim());// 证书编号
					break;
				case "Certificate No.":
					certificate.setNumber(cert_td.nextElementSibling().text()
							.trim());// 证书编号
					break;
				case "证书状态":
					certificate.setStatus(cert_td.nextElementSibling().text()
							.trim());// 证书状态
					break;
				case "Certificate Status":
					certificate.setStatus(cert_td.nextElementSibling().text()
							.trim());// 证书状态
					break;
				case "证书到期日期":
					certificate.setEndDate(cert_td.nextElementSibling().text()
							.trim());// 证书到期日期
					break;
				case "Expiration Date":
					certificate.setEndDate(cert_td.nextElementSibling().text()
							.trim());// 证书到期日期
					break;
				case "认证依据":
					certificate.setBasis(cert_td.nextElementSibling().text()
							.trim());// 认证依据
					break;
				case "Certification Basis":
					certificate.setBasis(cert_td.nextElementSibling().text()
							.trim());// 认证依据
					break;
				case "认证覆盖的业务范围":
					certificate.setCovering(cert_td.nextElementSibling().text()
							.trim());// 认证覆盖的业务范围
					break;
				case "Business Scope":
					certificate.setCovering(cert_td.nextElementSibling().text()
							.trim());// 认证覆盖的业务范围
					break;
				case "认证依据的标准和技术要求":
					certificate.setBasis(cert_td.nextElementSibling().text()
							.trim());// 认证依据的标准和技术要求
					break;
				case "Standards and technical requirements for certification":
					certificate.setBasis(cert_td.nextElementSibling().text()
							.trim());// 认证依据的标准和技术要求
					break;
				case "产品类别":
					certificate.setProductCategory(cert_td.nextElementSibling()
							.text().trim());// 产品类别
					break;
				case "Product Category":
					certificate.setProductCategory(cert_td.nextElementSibling()
							.text().trim());// 产品类别
					break;
				default:
					break;
				}
			}
		}

		LOGGER.info("CncaParser cncaDetailParser:{}", certificate);

		return certificate;

	}

	public List<Certificate> cncaListParser(String html) {

		LOGGER.info("CncaParser.cncaListParser begin html is {}", html);

		if (StringUtils.isEmpty(html)) {
			return null;
		}
		List<Certificate> certificateList = new ArrayList<Certificate>();
		Gson gson = new GsonBuilder().create();
		Map<String, Object> prd = gson.fromJson(html,
				new TypeToken<Map<String, Object>>() {
				}.getType());
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> prds = (List<Map<String, Object>>) prd
				.get("allCertificate");
		if (null != prds && prds.size() > 0) {
			for (Map<String, Object> map : prds) {
				Certificate cncaDetail = cncaDetailParser(map.get("rzrkywxx")
						.toString());
				if (null != cncaDetail
						&& (!StringUtils.isEmpty(cncaDetail.getBasis())
								|| !StringUtils.isEmpty(cncaDetail
										.getCovering())
								|| !StringUtils
										.isEmpty(cncaDetail.getEndDate())
								|| !StringUtils.isEmpty(cncaDetail.getNumber()) || !StringUtils
									.isEmpty(cncaDetail.getProductCategory()))) {
					certificateList.add(cncaDetail);
				}
			}
		}

		LOGGER.info("CncaParser cncaListParser:{}", certificateList);

		return certificateList;

	}

}
