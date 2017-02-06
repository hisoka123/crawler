package com.module.dao.repository.gsxt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.gsxt.TQyjbxx;
 

@Repository
public interface TQyjbxxRepository  extends JpaRepository<TQyjbxx,Integer> {
	
	@Query("select o from TQyjbxx o where o.Company.id is null")
	List<TQyjbxx> findByCompany_idIsNull();
}
