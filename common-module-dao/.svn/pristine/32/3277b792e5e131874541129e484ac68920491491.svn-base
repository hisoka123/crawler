package com.module.dao.repository.creditchinatwo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.creditchinatwo.CompanyRecord;

@Repository
public interface CompanyRecordRepository extends
		JpaRepository<CompanyRecord, Long> {

	public Set<CompanyRecord> findByCreditCompany_Id(long id);

}
