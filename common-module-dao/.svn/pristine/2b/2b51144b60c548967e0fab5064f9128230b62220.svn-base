package com.module.dao.repository.auth;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.module.dao.entity.auth.ActiveUser;

import java.util.List;


public interface ActiveUserDao extends PagingAndSortingRepository<ActiveUser, Long>, JpaSpecificationExecutor<ActiveUser> {
	  
	ActiveUser findByName(String name);

	ActiveUser findByLoginName(String loginName);

	
}
