package com.module.dao.repository.auth.impl;

import com.module.dao.entity.auth.Menu;
import com.module.dao.repository.auth.MenuDao;
import org.springframework.stereotype.Repository;

/**
 * Created by zxz on 2015/12/24.
 */
@Repository("menuDao")
public class MenuDaoImpl  extends GenericDaoImpl<Menu,Long> implements MenuDao {
}
