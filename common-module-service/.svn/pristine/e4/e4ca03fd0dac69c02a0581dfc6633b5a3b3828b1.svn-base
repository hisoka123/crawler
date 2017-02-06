package com.crawlermanage.service.iautos;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.iautos.Iautos;
import com.module.dao.entity.iautos.IautosKeyword;
import com.module.dao.repository.iautos.IautosKeywordRepository;
import com.module.dao.repository.iautos.IautosRepository;

@Component("iautosDBService")
public class IautosDBService {
	
	
	    @Autowired
	    IautosKeywordRepository iautosKeywordRepository;
	    
	    @Autowired
	    IautosRepository iautosRepository;
	
	   //检查是定时任务中是否存在该任务
	   public List<IautosKeyword>  checkIsExistBYName(String city,String name){
		   
		     return iautosKeywordRepository.findByCityAndName(city, name);
	   }
	   
	   //通过id查询定时任务
	   public IautosKeyword  getTimeTask(long id){
		      return iautosKeywordRepository.findOne(id);
	   }
	   
	   //查询详情
	   public List<Iautos> getDetailByCOI(List<Long> idList){
		      
		      return iautosRepository.findByCOI(idList);
	   }
	   
	   //将新任务保存到定时任务表中
	   public IautosKeyword saveTimeTask(IautosKeyword iautosKeyword){
		      return iautosKeywordRepository.save(iautosKeyword);
	   }

	   
}
