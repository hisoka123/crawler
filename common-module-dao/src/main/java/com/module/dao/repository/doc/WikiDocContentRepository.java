/**
 * 
 */
package com.module.dao.repository.doc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.dao.entity.doc.WikiDocBase;
import com.module.dao.entity.doc.WikiDocContent;


/**
 * @author kingly
 * @date 2015年12月24日
 * 
 */
@Repository
public interface WikiDocContentRepository extends JpaRepository<WikiDocContent, Long> {
	public WikiDocContent findById(Long id);
	@Override
	public <S extends WikiDocContent> S save(S s);
	
	
	@Query("select new WikiDocContent(id,content) from WikiDocContent where wdb_id=?")
	WikiDocContent findByWdb_id(Long wdb_id);
	
	//以站点名查询
	@Query("select c from WikiDocContent c where c.wikiDocBase.id in ("
			     + "select b.id from WikiDocBase b where b.site_id in ("
			          + "select s.id from Site s where s.name = ?1) )")
	List<WikiDocContent> findBySite(String site);
	
	
}
