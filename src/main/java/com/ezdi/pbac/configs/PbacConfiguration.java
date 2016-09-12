package com.ezdi.pbac.configs;

import com.ezdi.pbac.services.MySqlPermissionService;
import com.ezdi.pbac.services.PermissionService;
import com.ezdi.pbac.services.RedisPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by EZDI\ganesh.s on 10/9/16.
 */
@Configuration
public class PbacConfiguration {
    private static final Logger log = LoggerFactory.getLogger(PbacConfiguration.class);

    @Value("${permission.store.class}")
    private String permissionStore;

    @Bean
    public PermissionService permissionService() {
        try {
            log.info("Creating permissionService for class {}", permissionStore);
            return (PermissionService)Class.forName(permissionStore).newInstance();
        } catch (Exception ex) {
            log.error("Unable to create permission service: " + permissionStore, ex);
        }
        return null;
    }
}
