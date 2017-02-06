package com.module.dao.repository.renfawang;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.renfawang.PeopleCourt;

@Repository
public interface PeopleCourtRepository  extends JpaRepository<PeopleCourt,Long> {
	
	  
	   @Query("select new PeopleCourt(p.pname,p.partyCardNum,p.execCourtName,p.caseCreateTime,p.caseCode,p.execMoney) from PeopleCourt p where p.executetime=(select max(c.executetime) from PeopleCourt c where c.companyOrIdentity.id in(?1))")
	   List<PeopleCourt> findByCOI(List<Long> idList);
	
}
