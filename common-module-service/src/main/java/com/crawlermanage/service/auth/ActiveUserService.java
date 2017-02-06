package com.crawlermanage.service.auth;

import com.crawler.domain.json.Result;
import com.module.dao.entity.auth.PagerHelper;
import com.module.dao.repository.auth.UserDao;
import com.module.unit.CommonConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.dao.entity.auth.ActiveUser;
import com.module.dao.repository.auth.ActiveUserDao;
import com.module.unit.Digests;
import com.module.unit.Encodes;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ActiveUserService {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static final Logger log = LoggerFactory.getLogger(ActiveUserService.class); 

	@Autowired
	private ActiveUserDao activeUserDao;

	@Autowired
	private UserDao userDao;
	
	public ActiveUser findUserByLoginName(String name){
		return activeUserDao.findByLoginName(name);
	}


	/**
	 * 跟据传进来的entity查找用户记录
	 * @param activeUser
	 * @return
     */
	public ActiveUser findUsersByModule(ActiveUser activeUser){

		return activeUser;
	}

	public ActiveUser findById(Long id){
		log.info("ActiveUserSerivce.finById()");
		return userDao.findById(id);
	}

	public Result<PagerHelper> findAllUsers(int page){
		log.info("ActiveUserSerivce.findAllUsers(int page)");
		Integer start = page;
		Integer pageSize = CommonConstruct.PAGE_SIZE_TWIENTY;
		Long totalCount = 0L;
		Map<String,Object> map = new HashMap<String,Object>();

		Result<PagerHelper> result = new Result<PagerHelper>();
		PagerHelper pagerHelper = null;
		List<ActiveUser> activeUserList = null;
		activeUserList =  userDao.findByModule(map,start,pageSize);
		totalCount = userDao.countAll(map);

		pagerHelper = new PagerHelper(activeUserList,page,pageSize,totalCount);
		result.setData(pagerHelper);

		return result;
	}

	public ActiveUser saveActiveUser(ActiveUser au){
		// 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
		if (StringUtils.isNotBlank(au.getPlainPassword())) {
			entryptPassword(au);
		}
		return activeUserDao.save(au);
	}


	/**
	 *
	 */
	public void updateUser(ActiveUser user){
		userDao.update(user);
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(ActiveUser user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

}
