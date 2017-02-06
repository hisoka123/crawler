package com.crawlermanage.service.gsxt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.gsxt.Company;
import com.module.dao.entity.gsxt.TResultJson;
import com.module.dao.repository.gsxt.CompanyRepository;
import com.module.dao.repository.gsxt.TResultJsonRepository;


@Component("gsxtDBService")
public class GsxtDBService {

	
	   @Autowired
	   CompanyRepository companyRepository;
	   
	   @Autowired
	   TResultJsonRepository tResultJsonRepository;
	   
	   
	   
	   //检查企业是否已存在于定时任务列表
	   public Company companyIsExist(String city,String name){
		   
		      return companyRepository.findByCityAndName(city, name);
		      
	   }
	   
	   //检查企业
	   public Company findById(long id){
		      return companyRepository.findOne(id);
	   }
	   
	   //查询企业详情
	   public List<TResultJson> getTResultJson(Long companyID){
		   
		      return tResultJsonRepository.findByCompanyID(companyID);
		   
	   }
	   
	   //保存企业
	   public Company saveCompany(Company company){
		      return companyRepository.save(company);
	   }
	   
	   
}
