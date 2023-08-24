package com.moing.moingbe.global.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moing.moingbe.global.redis.dao.RefreshRedisDao;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RefreshRedisSerializer implements RedisSerializer<RefreshRedisDao> {

    private final ObjectMapper objectMapper;

    public RefreshRedisSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public byte[] serialize(RefreshRedisDao refreshRedisDao) throws SerializationException {
        String convertValue = objectMapper.convertValue(refreshRedisDao, String.class);
        return convertValue.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public RefreshRedisDao deserialize(byte[] bytes) throws SerializationException {
        String deserializeValue = new String(bytes, StandardCharsets.UTF_8);
        return objectMapper.convertValue(deserializeValue, RefreshRedisDao.class);
    }
}
