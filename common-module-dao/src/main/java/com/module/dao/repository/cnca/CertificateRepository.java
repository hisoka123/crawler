package com.module.dao.repository.cnca;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.cnca.Certificate;
import com.module.dao.entity.dailianmeng.LoanUnion;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

	// public Set<Certificate> findByOrganization_CertificateCompany_Id(Long
	// id);
	public Set<Certificate> findByCertificateCompany_Id(Long id);

	@Query("select new Certificate(p.certificateNo,p.certificateStatus,p.certificateBasis,p.businessScope,p.dueDate,p.productCategory) "
			+ "from Certificate p where p.executetime=(select max(c.executetime) from Certificate c where c.certificateCompany.id in(?1))")
	List<Certificate> findByCompany(List<Long> idList);
}
