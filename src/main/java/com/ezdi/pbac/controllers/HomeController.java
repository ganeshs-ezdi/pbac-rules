package com.ezdi.pbac.controllers;

import com.ezdi.pbac.beans.UserDocumentPermission;
import com.ezdi.pbac.beans.UserEncounterPermission;
import com.ezdi.pbac.common.DocumentPermissionContext;
import com.ezdi.pbac.common.EncounterPermissionContext;
import com.ezdi.pbac.daos.UserDocumentPermissionDao;
import com.ezdi.pbac.daos.UserEncounterPermissionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by EZDI\ganesh.s on 7/9/16.
 */
@RestController
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserDocumentPermissionDao documentPermissionDao;

    @Autowired
    private UserEncounterPermissionDao encounterPermissionDao;

    @RequestMapping("/document/{userId}")
    public String[] getDocumentPermissions(@PathVariable int userId, @RequestBody DocumentPermissionContext context) {
        log.debug("UserId={}, Context={}", userId, context);
        List<UserDocumentPermission> perms = documentPermissionDao.getDocumentPermissions(
                userId,
                context.getPatientClass(),
                context.getPatientType(),
                context.getServiceLine(),
                context.getPointOfCare()
        );

        ArrayList<String> permissions = new ArrayList<>();

        for(UserDocumentPermission perm : perms) {
            log.debug("{}", perm);
            permissions.add(perm.getPermission());
        }

        log.debug("Permissions = {}", permissions);

        return permissions.toArray(new String[permissions.size()]);
    }

    @RequestMapping("/encounter/{userId}")
    public String[] getEncounterPermissions(@PathVariable int userId, @RequestBody EncounterPermissionContext context) {
        log.debug("UserId={}, Context={}", userId, context);
        List<UserEncounterPermission> perms = encounterPermissionDao.getDocumentPermissions(
                userId,
                context.getPatientClass(),
                context.getPatientType(),
                context.getFinancialClass()
        );

        ArrayList<String> permissions = new ArrayList<>();

        for(UserEncounterPermission perm : perms) {
            log.debug("{}", perm);
            permissions.add(perm.getPermission());
        }

        log.debug("Permissions = {}", permissions);

        return permissions.toArray(new String[permissions.size()]);
    }

    @RequestMapping("/cookie")
    public String getCookies(HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie("Key","Value"));
        return "json";
    }
}
