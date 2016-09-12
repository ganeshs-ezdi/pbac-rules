package com.ezdi.pbac.services;

import com.ezdi.pbac.beans.SecurityContext;

import java.util.List;

/**
 * Created by EZDI\ganesh.s on 10/9/16.
 */
public interface PermissionService {
    List<String> getPermissions(SecurityContext securityContext);
}
