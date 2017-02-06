package com.crawlermanage.service.cnca;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.cnca.Certificate;
import com.module.dao.entity.cnca.CertificateCompany;
import com.module.dao.repository.cnca.CertificateCompanyRepository;
import com.module.dao.repository.cnca.CertificateRepository;

@Component("cncaDBService")
public class CncaDBService {
	
	
	    @Autowired
	    CertificateCompanyRepository companyRepository;
	    
	    @Autowired
	    CertificateRepository cncaRepository;
	
	   //检查是定时任务中是否存在该任务
	   public List<CertificateCompany>  checkIsExistBYName(String content){
		   
		     return companyRepository.findByName(content);
	   }
	   
	   //通过id查询定时任务
	   public CertificateCompany  getTimeTask(long id){
		      return companyRepository.findOne(id);
	   }
	   
	   //查询详情
	   public List<Certificate> getDetailByCompany(List<Long> idList){
		      
		      return cncaRepository.findByCompany(idList);
	   }
	   
	   //将新任务保存到定时任务表中
	   public CertificateCompany saveTimeTask(CertificateCompany certificateCompany){
		      return companyRepository.save(certificateCompany);
	   }

	   
}
