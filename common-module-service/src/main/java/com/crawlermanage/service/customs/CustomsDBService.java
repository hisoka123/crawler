package com.crawlermanage.service.customs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.customs.CUResultJson;
import com.module.dao.entity.customs.CustomsCompany;
import com.module.dao.entity.renfawang.PeopleCourt;
import com.module.dao.repository.customs.CustomsCompanyRepository;
import com.module.dao.repository.customs.CustomsResultJsonRepository;

@Component("customsDBService")
public class CustomsDBService {

	   @Autowired
	   CustomsCompanyRepository customsCompanyRepository;
	   
	   @Autowired
	   CustomsResultJsonRepository customsResultJsonRepository;
	
	   //检查是定时任务中是否存在该任务
	   public List<CustomsCompany>  checkIsExistBYName(String content){
		   
		     return customsCompanyRepository.findByNames(content);
	   }
	   
	 //查询详情
	   public List<CUResultJson> getDetailByCcId(List<Long> idList){
		      
		      return customsResultJsonRepository.findByCcId(idList);
	   }
	
	   //保存定时任务
	   public CustomsCompany saveTimeTask(CustomsCompany customsCompany){
		     return customsCompanyRepository.save(customsCompany);
	   }
	   
	   //通过id查询定时任务
	   public CustomsCompany getTimeTask(Long id){
		     return customsCompanyRepository.findOne(id);
	   }
	
}
