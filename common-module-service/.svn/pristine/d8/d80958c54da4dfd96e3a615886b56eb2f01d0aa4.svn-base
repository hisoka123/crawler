package com.crawlermanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.dao.entity.data.Email;
import com.module.dao.repository.data.EmailDao;

@Service
public class EmailService {
	
	@Autowired
	private EmailDao emailDao;

	public <S extends Email> S save(S entity) {
		return emailDao.save(entity);
	}

	public <S extends Email> List<S> save(Iterable<S> entities) {
		return emailDao.save(entities);
	}


	
	
}
