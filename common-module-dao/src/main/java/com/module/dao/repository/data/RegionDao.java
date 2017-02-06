package com.module.dao.repository.data;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.data.Region;
 


@Repository
public interface RegionDao extends JpaRepository<Region, Long> {

	Region findById(Long id);

	Region findByRegionIdAndCountryCode(String regionId, String countryCode);

	List<Region> findByParentId(Long param);

	List<Region> findByParentIdAndCountryCode(Long param, String code);

	List<Region> findByCountryCode(String code, Sort sort);

	List<Region> findByCountryChiName(String countryChiName);

	List<Region> findByRegionChiName(String regionChiName);

	@Query("SELECT r FROM Region r WHERE LOWER(r.countryCode) = ?1 AND LOWER(r.regionEngName) in ?2 ORDER BY id asc")
	List<Region> findByCountryCodeAndRegionEngNameInOrderByIdAsc(String countryCode, Collection<String> regionEngName);

	@Query("SELECT r FROM Region r WHERE LOWER(r.countryCode) = ?1 AND LOWER(r.regionEngName) in ?2 AND parentId = ?3 ORDER BY id asc")
	List<Region> findByCountryCodeAndRegionEngNameInAndParentIdOrderByIdAsc(String countryCode, Collection<String> regionEngName,
																			Long parentId);

	@Query("Select r from Region r where LOWER(r.regionChiName) like :regionName or LOWER(r.regionEngName) like :regionName")
	List<Region> getRegionListByName(@Param("regionName") String name);

	@Query("Select id from Region")
	List<Long> getAllRegionId();

	@Query("Select max(id) from Region")
	Long getMaxId();

	@Query("Select id from Region where countryCode = ?1 and id >0")
	List<Long> getAllRegionIdByCountryCode(String code);

	@Query("Select id from Region where parentId = ?1")
	List<Long> getAllSubRegionIdByParent(Long id);
	
	@Query("Select r from Region r where countryCode ='CN' and id not in (32, 33, 391,528,-2)")
	List<Region> getAllMainlandProvince();
	
	
}
