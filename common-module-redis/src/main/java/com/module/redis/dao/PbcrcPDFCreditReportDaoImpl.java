package com.module.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

@Repository
public class PbcrcPDFCreditReportDaoImpl implements PbcrcPDFCreditReportDao {
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	
	@Override
	public String cachePDFReportURL(String username, String pdfReportUrl) { //执行成功返回OK
		Jedis jedis = jedisConnectionFactory.getShardInfo().createResource();
		jedis.select(DATABASE_INDEX02);
		
		String result = jedis.set(username, pdfReportUrl);
		jedis.expire(username, EXPIRE_TIME); //
		jedis.close();
		
		return result;
	}

	@Override
	public String getPDFReportURLByUserName(String username) { //没有数据返回为null
		Jedis jedis = jedisConnectionFactory.getShardInfo().createResource();
		jedis.select(DATABASE_INDEX02);
		
		String pdfReportUrl = jedis.get(username);
		jedis.close();
		
		return pdfReportUrl;
	}
	
}
