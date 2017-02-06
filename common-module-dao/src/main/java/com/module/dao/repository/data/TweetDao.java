package com.module.dao.repository.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.module.dao.entity.data.Region;
import com.module.dao.entity.data.Tweet;
import com.module.dao.entity.data.UserFeed;

public interface TweetDao extends PagingAndSortingRepository<Tweet, Integer>,JpaSpecificationExecutor<Tweet>, JpaRepository<Tweet, Integer> {

	Tweet findById(Long id);
	
	@Query("select t.tid from Tweet t")
	List<Long> findTidAll();
	
	@Query("select count(t.tid) from Tweet t where t.region is not null")
	long countTidAllRegionIsNotNull();
	
	@Query("select count(t.tid) from Tweet t where t.region in :regions")
	long countTidByAlarm(@Param("regions") List<Region> regions);
    
	@Query("from Tweet o where o.userFeed=:userFeed")
	List<Tweet> findByUserFeedId(@Param("userFeed")UserFeed userFeed);
	
	@Query("from Tweet t where t.region is not null order by t.created_at desc")
	Page<Tweet> findByRegionIsNotNullOrderByCreated_atDesc(Pageable pageable);
	
	@Query("from Tweet t where t.region in :regions order by t.created_at desc")
	Page<Tweet> findByRegionInOrderByCreated_atDesc(@Param("regions") List<Region> regions,Pageable pageable);
	
	@Query("select max(t.id) from Tweet t where t.region in :regions")
	long findMaxIdByAlarm(@Param("regions") List<Region> regions);
	
	@Query("from Tweet t where t.id >:id and t.region in :regions")
	List<Tweet> findNewByAlarmOrderByCreated_atDesc(@Param("id") long id,@Param("regions") List<Region> regions);
	
	@Query("from Tweet t where t.id >:id and t.region is not null")
	List<Tweet> findNewByRegionIsNotNullOrderByCreated_atDesc(@Param("id") long id);
	
	@Query("select max(t.id) from Tweet t where t.region is not null")
	long findMaxId();
}
