package com.ezdi.pbac.serializers;

import com.ezdi.pbac.beans.SecurityContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.List;
import java.util.Map;

/**
 * Created by EZDI\ganesh.s on 10/9/16.
 */
public class SecurityContextKeySerializer implements RedisSerializer<SecurityContext>{
    private List<String> keys;

    public SecurityContextKeySerializer(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public byte[] serialize(SecurityContext securityContext) throws SerializationException {
        StringBuilder builder = new StringBuilder();
        builder.append(securityContext.getUserId());
        builder.append(":");
        builder.append(securityContext.getEntityType());
        Map<String, String> contextParams = securityContext.getContextParams();

        for(String key : keys) {
            builder.append(":");
            builder.append(contextParams.get(key));
        }

        return builder.toString().getBytes();
    }

    @Override
    public SecurityContext deserialize(byte[] bytes) throws SerializationException {
        String actualString = new String(bytes);
        String[] parts = actualString.split("\\:");
        SecurityContext context = new SecurityContext();

        context.setUserId(parts[0]);
        context.setEntityType(parts[1]);

        for(int i=2; i<Math.min(parts.length-2, keys.size()) ; i++) {
            context.getContextParams().put(keys.get(i-2), parts[i]);
        }

        return context;
    }
}
