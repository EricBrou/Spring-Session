package com.project.models.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

    public void save(String chave, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(chave, map);
    }

    public Map<Object, Object> get(String chave) {
        return redisTemplate.opsForHash().entries(chave);
    }

    public void delete(String chave) {
        redisTemplate.delete(chave);
    }
	
}
