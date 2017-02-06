package com.crawlermanage.service.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.module.dao.entity.cnca.Certificate;

/**
 * 认证网对象转化工具
 */
public class CncaPoVoTransfer {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CncaPoVoTransfer.class);

	public static com.crawler.cnca.domain.json.Certificate poToVo(
			Certificate certificate) {
		LOGGER.info("Certificate transfer to com.crawler.cnca.domain.json.Certificate is begin!");
		com.crawler.cnca.domain.json.Certificate customs = null;
		if (null != certificate) {
			customs = new com.crawler.cnca.domain.json.Certificate();
			customs.setNumber(certificate.getCertificateNo());// 证书编号
			customs.setStatus(certificate.getCertificateStatus());// 证书状态
			customs.setBasis(certificate.getCertificateBasis());// 认证依据的标准和技术要求
			customs.setCovering(certificate.getBusinessScope());// 认证覆盖的业务范围
			customs.setEndDate(certificate.getDueDate());// 证书到期日期
			customs.setProductCategory(certificate.getProductCategory());// 产品类别
		}
		LOGGER.info("Certificate transfer to com.crawler.cnca.domain.json.Certificate is end!");
		return customs;
	}

	public static Certificate voToPo(
			com.crawler.cnca.domain.json.Certificate customs) {
		LOGGER.info("com.crawler.cnca.domain.json.Certificate transfer to Certificate is begin!");
		Certificate certificate = null;
		if (customs != null) {
			certificate = new Certificate();
			certificate.setCertificateNo(customs.getNumber());// 证书编号
			certificate.setCertificateStatus(customs.getStatus());// 证书状态
			certificate.setCertificateBasis(customs.getBasis());// 认证依据的标准和技术要求
			certificate.setBusinessScope(customs.getCovering());// 认证覆盖的业务范围
			certificate.setDueDate(customs.getEndDate());// 证书到期日期
			certificate.setProductCategory(customs.getProductCategory());// 产品类别
		}
		LOGGER.info("com.crawler.cnca.domain.json.Certificate transfer to Certificate is end!");
		return certificate;
	}

}