package com.module.dao.repository.gsxt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.gsxt.TResultJson;

@Repository
public interface TResultJsonRepository extends JpaRepository<TResultJson,Long> {

	  @Query("select new TResultJson(cname,result,executetime) from TResultJson  where company_id=? order by id desc")
      List<TResultJson>  findByCompanyID(Long companyID);
	
}
