package com.crawlermanage.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.module.dao.entity.data.Region;
import com.module.dao.repository.data.RegionDao;

@Component
@Transactional
public class RegionService {
	@Autowired
	private RegionDao regionDao;
	

	public long count() {
		return regionDao.count();
	}

	public void delete(Iterable<? extends Region> arg0) {
		regionDao.delete(arg0);
	}

	public void delete(Long arg0) {
		regionDao.delete(arg0);
	}

	public void delete(Region arg0) {
		regionDao.delete(arg0);
	}

	public void deleteAll() {
		regionDao.deleteAll();
	}

	public List<Region> findByCountryCode(String code, Sort sort) {
		return regionDao.findByCountryCode(code, sort);
	}

	public List<Region> findByCountryChiName(String countryChiName) {
		return regionDao.findByCountryChiName(countryChiName);
	}

	public List<Region> findByCountryCodeAndRegionEngNameInOrderByIdAsc(
			String countryCode, Collection<String> regionEngName) {
		return regionDao.findByCountryCodeAndRegionEngNameInOrderByIdAsc(
				countryCode, regionEngName);
	}

	public List<Region> findByCountryCodeAndRegionEngNameInAndParentIdOrderByIdAsc(
			String countryCode, Collection<String> regionEngName, Long parentId) {
		return regionDao
				.findByCountryCodeAndRegionEngNameInAndParentIdOrderByIdAsc(
						countryCode, regionEngName, parentId);
	}
	/**
	 * 获取所有地点
	 * @return
	 */
	//@Cacheable(value="dataCache", key="RegionService_findAll")
	@Cacheable(value="dataCache", key="'RegionService_findAll'")
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	public List<Region> findAll(Sort sort) {
		return regionDao.findAll(sort);
	}

	public List<Region> findAll(Iterable<Long> ids) {
		return regionDao.findAll(ids);
	}

	public void deleteInBatch(Iterable<Region> entities) {
		regionDao.deleteInBatch(entities);
	}

	public void deleteAllInBatch() {
		regionDao.deleteAllInBatch();
	}

	public boolean exists(Long arg0) {
		return regionDao.exists(arg0);
	}

	public Page<Region> findAll(Pageable arg0) {
		return regionDao.findAll(arg0);
	}

	public Region findById(Long id) {
		return regionDao.findById(id);
	}

	public Region findByRegionIdAndCountryCode(String regionId,
			String countryCode) {
		return regionDao.findByRegionIdAndCountryCode(regionId, countryCode);
	}

	public List<Region> findByParentId(Long param) {
		return regionDao.findByParentId(param);
	}

	public List<Region> findByParentIdAndCountryCode(Long param, String code) {
		return regionDao.findByParentIdAndCountryCode(param, code);
	}

	public List<Region> findByRegionChiName(String regionChiName) {
		return regionDao.findByRegionChiName(regionChiName);
	}

	public Region findOne(Long arg0) {
		return regionDao.findOne(arg0);
	}

	public List<Long> getAllRegionId() {
		return regionDao.getAllRegionId();
	}

	public <S extends Region> List<S> save(Iterable<S> entities) {
		return regionDao.save(entities);
	}

	public Long getMaxId() {
		return regionDao.getMaxId();
	}

	public List<Long> getAllRegionIdByCountryCode(String code) {
		return regionDao.getAllRegionIdByCountryCode(code);
	}

	public void flush() {
		regionDao.flush();
	}

	public <S extends Region> S saveAndFlush(S entity) {
		return regionDao.saveAndFlush(entity);
	}

	public List<Long> getAllSubRegionIdByParent(Long id) {
		return regionDao.getAllSubRegionIdByParent(id);
	}
	@Cacheable(value="dataCache", key="'RegionService_getAllMainlandProvince'")
	public List<Region> getAllMainlandProvince() {
		return regionDao.getAllMainlandProvince();
	}

	public Region getOne(Long id) {
		return regionDao.getOne(id);
	}

	public <S extends Region> S save(S arg0) {
		return regionDao.save(arg0);
	}


	public List<Region> getRegionListByName(String name) {
		return regionDao.getRegionListByName(name);
	}

	public RegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDao regionDao) {
		this.regionDao = regionDao;
	}
	public Map<Region,List<Region>> getAllSubRegionByParentId(long parentId) {
		List<Region> regions = regionDao.findByParentId(parentId);
		Map<Region,List<Region>> map = new HashMap<Region ,List<Region>>();
		for (Region region : regions) {
			List<Region> regions2 = regionDao.findByParentId(region.getId());
			map.put(region, regions2);
		}
		return map;
	}

	public List<Region> getRegionsByIds(List<Long> ids){
		List<Region> regions = new ArrayList<Region>();
		for (Long id : ids) {
			Region region = findById(id);
			regions.add(region);
		}
		return regions;
	}

	
	
	
}
