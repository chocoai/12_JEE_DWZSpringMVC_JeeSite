package com.joe.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.joe.service.CacheService;

/** 
 * 	
 */
@Service
public class CacheServiceImpl implements CacheService {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * @see com.joe.service.CacheService#clearAllCache()
	 */
	public void clearAllCache() {
		em.getEntityManagerFactory().getCache().evictAll();
	}

}
