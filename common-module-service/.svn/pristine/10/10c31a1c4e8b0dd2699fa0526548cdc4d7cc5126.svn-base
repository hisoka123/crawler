package com.crawlermanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.module.dao.entity.data.UserFeed;
import com.module.dao.repository.data.UserFeedRepository;

@Component
@Transactional
public class UserFeedService {
    
	@Autowired 
	UserFeedRepository userFeedRepository;
	
	//查询全部已关注的人物,全部字段
		public List<UserFeed> getUserFeeds(){
			List<UserFeed> list=userFeedRepository.findAll();
	        return list;				
		}
		
		//查询全部已关注的人物，除followers外的字段

		public List<UserFeed> getUserFeeds_2(){
	    	List<UserFeed> list=userFeedRepository.findAllUser();
			
			return list;
		}
		
		/*public Page<UserFeed> getUserFeeds_2(Integer page,Integer pageSize){
			Page<UserFeed> pages=userFeedRepository.fincdAllUser(new PageRequest(page,pageSize));
			return pages;
		}*/
    
		
		
	
	//以"用户昵称"为搜索对象，模糊查询,
	public List<UserFeed> searchScreen_name(String screen_name){
		return userFeedRepository.findScreen_name(screen_name);
	}
		
		
     //以uid为对象，查询单个已注人物的基本信息
	public UserFeed getUserFeed(Long uid){
		return userFeedRepository.findByUid(uid);
	}
	
	
	//保存关注人物基本信息,比如姓名等
	public boolean save(UserFeed userFeed){
		    if(userFeedRepository.findByUid(userFeed.getUid())!=null){
		    	UserFeed ufUpdate=userFeedRepository.findByUid(userFeed.getUid());
		    	
				ufUpdate.setScreen_name(userFeed.getScreen_name());
				ufUpdate.setLocation(userFeed.getLocation());
				ufUpdate.setDescription(userFeed.getDescription());
				ufUpdate.setProfile_image_url(userFeed.getProfile_image_url());
				ufUpdate.setGender(userFeed.getGender());
				ufUpdate.setFollowers_count(userFeed.getFollowers_count());
				ufUpdate.setFollowers_url(userFeed.getFollowers_url());
			    ufUpdate.setFriends_count(userFeed.getFriends_count());
				ufUpdate.setFriends_url(userFeed.getFriends_url());
				ufUpdate.setStatuses_count(userFeed.getStatuses_count());
				ufUpdate.setStatuses_url(userFeed.getStatuses_url());
				ufUpdate.setEnable(1);
				userFeedRepository.saveAndFlush(ufUpdate);
		    }else {
		    	userFeed.setEnable(1);
			    userFeedRepository.saveAndFlush(userFeed);
			}
		    
		    return true;		    
	}
	
	//删除某个关注人物的基本信息
	public boolean deleteUid(Long uid){
		    
		userFeedRepository.findByUid(uid).setEnable(0);
				  
		   return true; 
	}
	
}
