package com.module.dao.repository.auth;

import com.module.dao.entity.auth.ActiveUser;
import com.module.dao.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by zxz on 2015/11/6.
 */
public interface RoleDao  extends GenericDao<Role,Long>{




}
