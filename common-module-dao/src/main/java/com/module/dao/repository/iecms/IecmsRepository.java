package com.module.dao.repository.iecms;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.iecms.Iecms;
 

@Repository
public interface IecmsRepository  extends JpaRepository<Iecms,Long> {
	//Iecms findByName(String name);
//	private String businessChineseName;//经营者中文名称
//	
//	private String businessEnglishName;//经营者英文名称
//	
//	private String residence;//住所	
//	
//	private String zipcode;//邮编
//	
//	private String fax;//传真
//	private Date executetime;//创建时间
	
	
	  
	   @Query("select new Iecms(p.businessChineseName,p.businessEnglishName,p.residence,p.zipcode,p.fax,p.importExportEnterpriseCode,p.unifiedSocialCreditCode) from Iecms p where p.executetime=(select max(c.executetime) from Iecms c where c.iecmscompany.id in(?1))")
	   List<Iecms> findByCOI(List<Long> idList);
}
