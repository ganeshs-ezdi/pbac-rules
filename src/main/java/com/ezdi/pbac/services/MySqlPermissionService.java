package com.ezdi.pbac.services;

import com.ezdi.pbac.beans.SecurityContext;
import com.ezdi.pbac.beans.UserDocumentPermission;
import com.ezdi.pbac.daos.UserDocumentPermissionDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EZDI\ganesh.s on 10/9/16.
 */
public class MySqlPermissionService implements PermissionService {
    @Autowired
    private UserDocumentPermissionDao permissionDao;

    @Override
    public List<String> getPermissions(SecurityContext securityContext) {
        List<UserDocumentPermission> documentPermissions = permissionDao.getDocumentPermissions(
                Integer.parseInt(securityContext.getUserId()),
                securityContext.getContextParams().get("pc"),
                securityContext.getContextParams().get("pf"),
                "Unknown",
                "Unknown"
        );
        ArrayList<String> permissions = new ArrayList<>();

        for(UserDocumentPermission permission : documentPermissions) {
            permissions.add(permission.getPermission());
        }

        return permissions;
    }
}
