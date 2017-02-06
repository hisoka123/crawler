package com.crawlermanage.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.dao.entity.data.Alarm;
import com.module.dao.entity.data.Email;
import com.module.dao.entity.data.Region;
import com.module.dao.repository.data.AlermDao;

@Service
@Transactional
public class AlertService {

	@Autowired
	AlermDao alermDao ;
	
	public Alarm findOne(Long id) {
		return alermDao.findOne(id);
	}

	public void delete(Long id) {
		alermDao.delete(id);
	}

	public void flush() {
		alermDao.flush();
	}


	public <S extends Alarm> S save(S entity) {
		return alermDao.save(entity);
	}

	public <S extends Alarm> S saveAndFlush(S entity) {
		return alermDao.saveAndFlush(entity);
	}

	@Transactional(value=TxType.REQUIRED)
	public List<Alarm> findAll() {
		return alermDao.findAll();
	}
	
	/**
	 * 将Email和region关联关系放入map
	 */
	@Transactional(value=TxType.REQUIRED)
	public Map<String, Set<String>> findEmailByRegion() {
		
		Map<String, Set<String>> regionEmailMap = new HashMap<String, Set<String>>(); 
		//得到所有的alarm
		List<Alarm> alarms = findAll();
		
		//循环遍历每个alarm，将alarm内的region和Email建立关系并放入map里面
		for (Alarm alarm : alarms) {
			List<Region> regions = alarm.getRegions();
			for (Region region : regions) {
				if(regionEmailMap.containsKey(region.getRegionChiName())){
					//如果map里面已经有了region键值则将新邮件加入该该键值下
					Set<String> emls = regionEmailMap.get(region.getRegionChiName());
					//邮件去重
					List<Email> emails = alarm.getEmails();
					for (Email email : emails) {
						emls.add(email.getAddress());
					}
					regionEmailMap.put(region.getRegionChiName(), emls);
				}else{
					//否则将其放入map，键值为region
					Set<String> emls = new HashSet<String>();
					//邮件去重
					List<Email> emails = alarm.getEmails();
					for (Email email : emails) {
						emls.add(email.getAddress());
					}
				regionEmailMap.put(region.getRegionChiName(), emls);
				}
			}
			
		}
		return regionEmailMap;
	}
	
}
