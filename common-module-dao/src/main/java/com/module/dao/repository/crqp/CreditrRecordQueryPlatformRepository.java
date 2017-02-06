package com.module.dao.repository.crqp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.crqp.CreditrRecordQueryPlatform;
 

@Repository
public interface CreditrRecordQueryPlatformRepository  extends JpaRepository<CreditrRecordQueryPlatform,Long> {
//private String companyTitle;//企业标题
//	
//	private String keywordDescription;//关键字描述
//	
//	private String content;//内容
//	
//	private Date executetime;//创建时间
//	
//	@ManyToOne
//	@JoinColumn(name="creditrrecordqueryplatformcompany_id")
//	private CreditrRecordQueryPlatformCompany creditrrecordqueryplatformcompany;
	
	 @Query("select new CreditrRecordQueryPlatform(p.companyTitle,p.keywordDescription,p.content) from CreditrRecordQueryPlatform p where p.executetime=(select max(c.executetime) from CreditrRecordQueryPlatform c where c.creditrrecordqueryplatformcompany.id in(?1))")
	   List<CreditrRecordQueryPlatform> findByCOI(List<Long> idList);
	
}
