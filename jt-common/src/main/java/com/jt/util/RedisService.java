package com.jt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

@Component
public class RedisService {
	@Autowired(required=false)
	private JedisSentinelPool sentinelpool;
	//封装方法 get
	public String get(String key) {
		Jedis jedis = sentinelpool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}
	public void set(String key,String value) {
		Jedis jedis = sentinelpool.getResource();
		jedis.set(key, value);
		jedis.close();
	}
	public void setex(String key,int seconds,String value) {
		Jedis jedis = sentinelpool.getResource();
		jedis.setex(key, seconds, value);
		jedis.close();
	}
}
