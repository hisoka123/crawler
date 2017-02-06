package com.crawlermanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.module.dao.entity.data.Region;
import com.module.dao.entity.data.Tweet;
import com.module.dao.entity.data.UserFeed;
import com.module.dao.repository.data.TweetDao;



@Component
@Transactional 
public class TweetService {
	

	@Autowired
	TweetDao tweetDao;




	public List<Tweet> findNewByAlarmOrderByCreateTimeDesc(long id,
			List<Region> regions) {
		return tweetDao.findNewByAlarmOrderByCreated_atDesc(id, regions);
	}





	public long findMaxIdByAlarm(List<Region> regions) {
		return tweetDao.findMaxIdByAlarm(regions);
	}





	public long countTidByAlarm(List<Region> regions) {
		return tweetDao.countTidByAlarm(regions);
	}





	public List<Tweet> findNewByRegionIsNotNullOrderByCreateTimeDesc(long id) {
		return tweetDao.findNewByRegionIsNotNullOrderByCreated_atDesc(id);
	}





	
	//以userFeed为对象，查询
	public List<Tweet> getTweets(UserFeed userFeed){
		
		return tweetDao.findByUserFeedId(userFeed);
		
	}
	
	
	
	
	
	public List<Long> findTidAll() {
		return tweetDao.findTidAll();
	}





	public long findMaxId() {
		return tweetDao.findMaxId();
	}





	public Tweet getTweet(Long id){
		return tweetDao.findById(id);
	}





	/**
	 * 得到所有地址不为空的微博
	 * @return  List<Tweet> 微博列表
	 */
	public Page<Tweet> getTweetByRegionIsNotNull(int pageSize,int pageNumber,String pageOption) {
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<Tweet> page= tweetDao.findByRegionIsNotNullOrderByCreated_atDesc(pageRequest);
		//当pageOptiong为0时上一页
		if( "previous".equals(pageOption)){
			pageRequest = (PageRequest) page.previousPageable();
			//当pageOptiong为1时下一页
		}else if("next".equals(pageOption)){
			pageRequest = (PageRequest) page.nextPageable();
		}else if("first".equals(pageOption)){
			pageRequest = (PageRequest) pageRequest.first();
		}else if("last".equals(pageOption)){
			 pageNumber = page.getTotalPages()-1;
			 pageRequest = new PageRequest(pageNumber, pageSize);
		}
		return tweetDao.findByRegionIsNotNullOrderByCreated_atDesc(pageRequest);
	}


	public Page<Tweet> findByRegionIdOrderByCreateTimeDesc(List<Region> regions,int pageSize,int pageNumber,String pageOption
			) {
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<Tweet> page= tweetDao.findByRegionInOrderByCreated_atDesc(regions, pageRequest);
		//当pageOptiong为0时上一页
		if( "previous".equals(pageOption)){
			pageRequest = (PageRequest) page.previousPageable();
			//当pageOptiong为1时下一页
		}else if("next".equals(pageOption)){
			pageRequest = (PageRequest) page.nextPageable();
		}else if("first".equals(pageOption)){
			pageRequest = (PageRequest) pageRequest.first();
		}else if("last".equals(pageOption)){
			 pageNumber = page.getTotalPages()-1;
			 pageRequest = new PageRequest(pageNumber, pageSize);
		}
		return tweetDao.findByRegionInOrderByCreated_atDesc(regions, pageRequest);
	}



	public long countTidAllRegionIsNotNull() {
		return tweetDao.countTidAllRegionIsNotNull();
	}
 

   
}
