package com.crawlermanage.service.auth;

import java.security.Permission;

/**
 * Created by zxz on 2015/11/6.
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
