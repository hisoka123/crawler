package com.module.dao.repository.auth.impl;

import com.module.dao.entity.auth.ActiveUser;
import com.module.dao.repository.auth.UserDao;
import org.springframework.stereotype.Repository;

/**
 * Created by zxz on 2015/11/13.
 */

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<ActiveUser,Long> implements UserDao {


}
