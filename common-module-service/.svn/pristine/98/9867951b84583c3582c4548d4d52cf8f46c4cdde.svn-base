package com.crawlermanage.service.shixin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.renfawang.CompanyOrID;
import com.module.dao.entity.renfawang.PeopleCourt;
import com.module.dao.entity.shixin.Shixin;
import com.module.dao.entity.shixin.ShixinKeyword;
import com.module.dao.repository.shixin.ShixinKeywordRepository;
import com.module.dao.repository.shixin.ShixinRepository;

@Component("shixinDBService")
public class ShixinDBService {

	   @Autowired
	   ShixinKeywordRepository shixinKeywordRepository;
	   
	   @Autowired
	   ShixinRepository shixinRepository;
	 
	   //检查是定时任务中是否存在该任务
	   public List<ShixinKeyword>  checkIsExist(String queryType,String name,String cardNum){
		   
		      if("0".equals(queryType)){
		    	  return shixinKeywordRepository.findByTypeAndKeyword(queryType,name);
		      }else{
		    	  return shixinKeywordRepository.findByTypeAndKeywordAndCardNum(queryType,name, cardNum);
		      }
		   
		     
	   }
	   
	 //查询详情
	   public List<Shixin> getDetailByTTId(List<Long> idList){
		      
		      return shixinRepository.findByTTId(idList);
	   }
	   
	   //通过id查询
	   public ShixinKeyword getTimeTaskById(Long id){
		      return shixinKeywordRepository.findOne(id);
	   }
	   
	 //将新任务保存到定时任务表中
	   public ShixinKeyword saveTimeTask(ShixinKeyword shixinKeyword){
		      return shixinKeywordRepository.save(shixinKeyword);
	   }
}
