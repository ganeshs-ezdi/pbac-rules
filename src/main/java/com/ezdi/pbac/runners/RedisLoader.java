package com.ezdi.pbac.runners;

import com.ezdi.pbac.beans.SecurityContext;
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

import java.util.Arrays;

/**
 * Created by EZDI\ganesh.s on 10/9/16.
 */
@Component
public class RedisLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(RedisLoader.class);

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void run(String[] strings) throws Exception {
        log.info("Loading Redis ...");

        SecurityContext context = new SecurityContext();
        context.setUserId("1");
        context.setEntityType("Document");
        context.getContextParams().put("pc", "InPatient");
        context.getContextParams().put("pf", "1");

        RedisTemplate<SecurityContext, String[]> template = this.getRedisTemplate(
                new SecurityContextKeySerializer(Arrays.asList("pc", "pf"))
        );
        template.opsForValue().set(context, new String[]{"canEdit", "canView"});

        log.info("Added context {} with permissions {}", context, new String[]{"canEdit", "canView"});

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
}
