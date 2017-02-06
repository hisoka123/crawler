package com.module.dao.repository.customs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.customs.CustomsCompany;

@Repository
public interface CustomsCompanyRepository extends
		JpaRepository<CustomsCompany, Long> {

	CustomsCompany findTopByStateInOrderByPriorityDesc(List<Integer> states);

	CustomsCompany findTopByStateInAndNumLessThanOrderByPriorityDesc(
			List<Integer> states, Integer num);

	CustomsCompany findTop1ByName(String name);

	CustomsCompany findByName(String name);
	
	@Query("select c from CustomsCompany c where c.name=?1")
	List<CustomsCompany> findByNames(String name);

}
