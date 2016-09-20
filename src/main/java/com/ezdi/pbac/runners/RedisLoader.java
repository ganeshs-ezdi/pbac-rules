package com.ezdi.pbac.runners;

import com.ezdi.pbac.beans.SecurityContext;
import com.ezdi.pbac.beans.UserDocumentPermission;
import com.ezdi.pbac.daos.UserDocumentPermissionDao;
import com.ezdi.pbac.serializers.SecurityContextKeySerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by EZDI\ganesh.s on 10/9/16.
 */
@Component
public class RedisLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(RedisLoader.class);

    private static final String[] classes = new String[]{"InPatient","OutPatient","Emergency"};
    private static final String[] types = new String[]{"InPatient","OutPatient","Emergency"};
    private static final String[] lines = new String[]{"SL1", "SL2", "SL3", "SL4", "SL5", "SL6", "SL7"};
    private static final String[] cares = new String[]{"POC1","POC2","POC3","POC4","POC5","POC6","POC7"};

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private UserDocumentPermissionDao userDocumentPermissionDao;

    @Override
    public void run(String[] strings) throws Exception {
        log.info("Loading Redis ...");

        RedisTemplate<SecurityContext, String[]> template = this.getRedisTemplate(
                new SecurityContextKeySerializer(Arrays.asList("patient_class", "patient_type", "service_line", "point_of_care"))
        );

        Iterable<UserDocumentPermission> permissions = userDocumentPermissionDao.findAll();
        for(UserDocumentPermission permission: permissions) {
            ArrayList<SecurityContext> contexts = getContext(permission);
            for(SecurityContext context : contexts) {
                String[] value = template.opsForValue().get(context);
                if(value == null) {
                    value = new String[]{permission.getPermission()};
                } else {
                    value = Arrays.copyOf(value, value.length+1);
                    value[value.length-1] = permission.getPermission();
                }
                template.opsForValue().set(context, value);
            }
        }

        log.info("Finished Redis ...");
    }

    public RedisTemplate<SecurityContext, String[]> getRedisTemplate(RedisSerializer<SecurityContext> keySerializer) {
        RedisTemplate<SecurityContext, String[]> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(keySerializer);
        template.setValueSerializer(new Jackson2JsonRedisSerializer<String[]>(String[].class));
        template.afterPropertiesSet();
        return template;
    }

    public ArrayList<SecurityContext> getContext(UserDocumentPermission permission) {
        String[] patientClasses = null;
        String[] patientTypes = null;
        String[] serviceLines = null;
        String[] pointOfCares = null;
        if("*".equals(permission.getPatientClass())){
            patientClasses = classes;
        } else {
            patientClasses = new String[]{permission.getPatientClass()};
        }
        if("*".equals(permission.getPatientType())){
            patientTypes = types;
        } else {
            patientTypes = new String[]{permission.getPatientType()};
        }
        if("*".equals(permission.getServiceLine())){
            serviceLines = lines;
        } else {
            serviceLines = new String[]{permission.getServiceLine()};
        }
        if("*".equals(permission.getPointOfCare())){
            pointOfCares = cares;
        } else {
            pointOfCares = new String[]{permission.getPointOfCare()};
        }
        return getDocumentContext(permission.getUserId()+"", patientClasses, patientTypes, serviceLines, pointOfCares);
    }

    public ArrayList<SecurityContext> getDocumentContext(String userId, String[] patient_classes, String[] patient_types, String[] service_lines, String[] point_of_cares) {
        ArrayList<SecurityContext> contexts = new ArrayList<>();
        for(String patient_class : patient_classes) {
            for(String patient_type : patient_types) {
                for(String service_line : service_lines) {
                    for(String point_of_care : point_of_cares) {
                        SecurityContext context = new SecurityContext();
                        context.setUserId(userId);
                        context.setEntityType("document");
                        HashMap<String, String> map = new HashMap<>();
                        map.put("patient_class", patient_class);
                        map.put("patient_type", patient_type);
                        map.put("service_line", service_line);
                        map.put("point_of_care", point_of_care);
                        context.setContextParams(map);
                        contexts.add(context);
                    }
                }
            }
        }

        return contexts;
    }
}
