package com.crawlermanage.service.creditchina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.creditchinatwo.BDResultJson;
import com.module.dao.entity.creditchinatwo.CreditCompany;
import com.module.dao.repository.creditchinatwo.BDResultJsonRepository;
import com.module.dao.repository.creditchinatwo.CreditCompanyRepository;

@Component("creditchinaDBService")
public class CreditchinaDBService {

	@Autowired
	CreditCompanyRepository creditcompanyRepository;
	@Autowired
	BDResultJsonRepository bDResultJsonRepository;

	// 检查企业是否已存在于定时任务列表
	public CreditCompany companyIsExist(String name, String objectType) {
		return creditcompanyRepository.findTopByNameAndObjectType(name,
				objectType);
	}

	// 检查企业
	public CreditCompany findById(long id) {
		return creditcompanyRepository.findOne(id);
	}

	// 查询企业详情
	public BDResultJson getBDResultJson(Long id) {
		return bDResultJsonRepository
				.findTopByCreditCompany_IdOrderByExecutetimeDesc(id);
	}

	// 保存企业
	public CreditCompany saveCreditCompany(CreditCompany creditCompany) {
		return creditcompanyRepository.save(creditCompany);
	}

}
