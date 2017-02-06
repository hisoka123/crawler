package com.module.dao.repository.dailianmeng;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.dailianmeng.LoanUnion;

@Repository
public interface LoanUnionRepository  extends JpaRepository<LoanUnion,Long> {
	@Query("select new LoanUnion(p.name,p.caseNum,p.age,p.sex,p.cardID,p.legalPerson,"
			+ "p.executeCourt,"
			+ "p.province,"
			+ "p.executeNum,"
			+ "p.caseDate,"
			+ "p.dependCourt,"
			+ "p.effectNum,"
			+ "p.executeSituation,"
			+ "p.alreadyExecute,"
			+ "p.noExecute,"
			+ "p.behaviorSituation,"
			+ "p.pubDate,"
			+ "p.updateDate,"
			+ "p.debtMoney,"
			+ "p.loanDate,"
			+ "p.loanTerm,"
			+ "p.listType,"
			+ "p.loanState,"
			+ "p.describe,"
			+ "p.birthday,"
			+ "p.issuePlace) "
			+ "from LoanUnion p where p.executetime=(select max(c.executetime) from LoanUnion c where c.companyOrIdentity.id in(?1))")
	List<LoanUnion> findByCOI(List<Long> idList);
}