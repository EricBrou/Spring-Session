package com.project.models.service;

import java.util.Map;

public interface IRedisService {
	
	public void save(String chave, Map<String, Object> map);
    public Map<Object, Object> get(String chave);
    public void delete(String chave);
    
}
