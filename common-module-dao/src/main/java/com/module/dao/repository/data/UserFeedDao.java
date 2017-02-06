package com.module.dao.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.module.dao.entity.data.UserFeed;

public interface  UserFeedDao extends PagingAndSortingRepository<UserFeed, Long>,JpaSpecificationExecutor<UserFeed>, JpaRepository<UserFeed, Long> {

	UserFeed findByUid(Long uid);
}
