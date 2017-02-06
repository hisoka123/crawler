package com.module.dao.repository.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.module.dao.entity.data.UserFeed;

public interface UserFeedRepository extends JpaRepository<UserFeed,Integer> {
    
	//(1)查询表UserFeed中的内容，除Collection<UserFeed> followers字段
		@Query("select new  UserFeed(u.uid,u.screen_name,u.name,u.location,u.description,"
			                                              +"u.url,u.profile_image_url,u.gender,u.followers_count,"
				                                          +"u.followers_url,u.friends_count,u.friends_url,u.statuses_count,"
			                                              +"u.statuses_url,u.favourites_count,u.created_at,u.enable)"
			   +"from UserFeed u where enable=1")
	    List<UserFeed> findAllUser();
		//Page<UserFeed> fincdAllUser(Pageable pageable);
		
		
		
		
		//(2)查询单个已关注人物的基本信息,以id为对象
		UserFeed findById(Long id);
		
		
		
		//(3)用于查询已关注的全部人物,以昵称为对象
		//@Query("from UserFeed o where o.screen_name like %?1%")
		@Query("select new UserFeed(u.uid,u.screen_name,u.name,u.location,u.description,"
	                                                    +"u.url,u.profile_image_url,u.gender,u.followers_count,"
	                                                    +"u.followers_url,u.friends_count,u.friends_url,u.statuses_count,"
	                                                    +"u.statuses_url,u.favourites_count,u.created_at,u.enable)"
	           +"from UserFeed u where u.screen_name like %?1%")
		List<UserFeed> findScreen_name(String screen_name);
		
		
		
		
		
		//(4)以uid查询某个已关注人物的基本信息,以uid为对象
		UserFeed findByUid(Long uid);
    
	
	
	
}
