/**
 * 
 */
package com.module.dao.repository.doc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.doc.WikiDocBase;

/**
 * @author kingly
 * @date 2015年12月24日
 * 
 */
@Repository
public interface WikiDocBaseRepository extends JpaRepository<WikiDocBase, Long> {
	public WikiDocBase findById(Long id);
	@Override
	public <S extends WikiDocBase> S save(S s);
	
	@Query("select new WikiDocBase(w.id,w.name,w.title,w.titleIcon,w.path,w.params,w.site_id,w.requestMethod) from WikiDocBase w where site_id=?")
	List<WikiDocBase> findBySite_id(Long site_id);
	
	
}
