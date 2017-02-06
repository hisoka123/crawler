package com.module.dao.repository.doc;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.module.dao.entity.doc.Site;

/** 
 * @author hmy
 * @date：Jan 7, 2016 
 * 
 */
public interface SiteRepository extends JpaRepository<Site,Integer> {
	
           List<Site> findByIsEnabled(Integer isEnabled);
           
           Site findById(Long id);
           
           Site findByName(String name);
           
           List<Site> findByType(String type);
           
          
           
}
