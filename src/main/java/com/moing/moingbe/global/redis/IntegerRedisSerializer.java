package com.moing.moingbe.global.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.StandardCharsets;

public class IntegerRedisSerializer implements RedisSerializer<Integer> {

    @Override
    public byte[] serialize(Integer integer) throws SerializationException {
        return String.valueOf(integer).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Integer deserialize(byte[] bytes) throws SerializationException {
        return Integer.parseInt(new String(bytes, StandardCharsets.UTF_8));
    }
}
