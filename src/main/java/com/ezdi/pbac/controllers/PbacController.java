package com.ezdi.pbac.controllers;

import com.ezdi.pbac.beans.SecurityContext;
import com.ezdi.pbac.services.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by EZDI\ganesh.s on 10/9/16.
 */
@RestController
public class PbacController {
    private static final Logger log = LoggerFactory.getLogger(PbacController.class);

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value="/pbac", method = RequestMethod.POST)
    public List<String> getPermissions(@RequestBody SecurityContext securityContext) {
        log.info("GetPermissions: {}", securityContext);

        // Get From Redis or From MySQL depending on which
        // service is wired into.
        List<String> permissions = permissionService.getPermissions(securityContext);

        log.info("GetPermissions: {}", permissions);
        return permissions;
    }
}
