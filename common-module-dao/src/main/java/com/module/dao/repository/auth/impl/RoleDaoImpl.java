package com.module.dao.repository.auth.impl;

import com.module.dao.entity.auth.Role;
import com.module.dao.repository.auth.RoleDao;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by zxz on 2015/11/18.
 */
@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role,Long> implements RoleDao {




}
