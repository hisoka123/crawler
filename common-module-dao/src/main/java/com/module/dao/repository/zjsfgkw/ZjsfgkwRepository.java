package com.module.dao.repository.zjsfgkw;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.module.dao.entity.zjsfgkw.Zjsfgkw;

@Repository
public interface ZjsfgkwRepository extends JpaRepository<Zjsfgkw,Long>{
	
	   @Query("select new Zjsfgkw(p.caseState,p.name,p.idNo,p.address,p.enforceBasis,p.caseNo,p.executReason,p.court,p.amountNotExecuted,p.caseDate,p.targetAmount,p.creditDate) from Zjsfgkw p where p.executetime=(select max(c.executetime) from Zjsfgkw c where c.zjsfgkwKeyword.id in(?1))")
	   List<Zjsfgkw> findByCOI(List<Long> idList);

}
