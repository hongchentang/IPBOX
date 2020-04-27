package com.hcis.ipanther.core.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis缓存相关工具类
 * 只支持对字符串的简单存取
 * @author wuwentao
 * @date 2015年8月4日
 */
@Component("redisUtils")
public class RedisUtils {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 新增
	 * @param key
	 * @param value
	 * @param expire 有效时间，毫秒数
	 */
	public void addOrUpdate(String key,String value,Long expire) {
		redisTemplate.opsForValue().set(key, value);
		if(null!=expire) {
			redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
		}
	}
	
	/**
	 * 新增
	 * @param key
	 * @param value
	 * @return
	 */
	public void addOrUpdate(String key, String value) {
		this.addOrUpdate(key, value,null);
	}

	/**
	 * 删除
	 * @param key
	 */
	public void delete(String key) {
		redisTemplate.delete(key);
	}
	
	/**
	 * 获取
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return (String) redisTemplate.opsForValue().get(key);
	}
}
