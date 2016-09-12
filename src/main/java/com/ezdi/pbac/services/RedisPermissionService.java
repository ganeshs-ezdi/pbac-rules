package com.ezdi.pbac.services;

import com.ezdi.pbac.beans.SecurityContext;
import com.ezdi.pbac.serializers.SecurityContextKeySerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by EZDI\ganesh.s on 10/9/16.
 */
public class RedisPermissionService implements PermissionService {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public List<String> getPermissions(SecurityContext securityContext) {
        RedisTemplate<SecurityContext, String[]> template =
                this.getRedisTemplate(new SecurityContextKeySerializer(Arrays.asList("pc", "pf")));
        String[] permissions = template.opsForValue().get(securityContext);
        if(permissions != null)
            return Arrays.asList(permissions);
        return Arrays.asList();
    }

    public RedisTemplate<SecurityContext, String[]> getRedisTemplate(RedisSerializer<SecurityContext> keySerializer) {
        RedisTemplate<SecurityContext, String[]> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(keySerializer);
        template.setValueSerializer(new Jackson2JsonRedisSerializer<String[]>(String[].class));
        template.afterPropertiesSet();
        return template;
    }
}
