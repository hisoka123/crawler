package com.module.dao.repository.dailianmeng;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.dailianmeng.CompanyOrIdentity;

@Repository
public interface CompanyOrIdentityRepository  extends JpaRepository<CompanyOrIdentity,Long> {
	CompanyOrIdentity findTopByTypeAndStateInAndNumLessThanOrderByPriorityDesc(String type, List<Integer> states, Integer num);

	@Query("select c from CompanyOrIdentity c where type=?1 and keyword=?2")
	List<CompanyOrIdentity> findByTypeAndKeyword(String type,String keyword);
}
