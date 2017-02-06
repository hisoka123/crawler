package com.module.dao.repository.gsxt;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.gsxt.TResultJson;

@Repository
public interface ResultJsonRepository  extends JpaRepository<TResultJson,Integer> {
	
	@Query("select o from TResultJson o where o.Company.id=?1")
	public Set<TResultJson> findByCompanyId(Long cid);
}
