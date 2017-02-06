package com.module.dao.repository.doc;

import com.module.dao.entity.doc.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zxz on 2016/1/7.
 */
@Repository
public interface SiteDao extends JpaRepository<Site, Long> {
	
    public Site findById(Long id);

    @Override
    public <S extends Site> S save(S s);
}
