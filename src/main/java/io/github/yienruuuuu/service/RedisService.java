package io.github.yienruuuuu.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @author Eric.Lee
 * Date: 2025/6/30
 */
@Service("redisService")
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setValue(String key, String value) {
        redisTemplate.opsForValue()
                .set(key, value, Duration.ofMinutes(10));
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
