package com.module.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.redis.dao.PbcrcPDFCreditReportDao;

@Service
public class PbccrcPDFCreditReportServiceImpl implements PbccrcPDFCreditReportService {
	@Autowired
	private PbcrcPDFCreditReportDao pbcrcPDFCreditReportDao;
	
	@Override
	public String cachePDFReportURL(String username, String pdfReportUrl) {
		return pbcrcPDFCreditReportDao.cachePDFReportURL(username, pdfReportUrl);
	}

	@Override
	public String getPDFReportURLByUserName(String username) {
		return pbcrcPDFCreditReportDao.getPDFReportURLByUserName(username);
	}

}
