package com.jt.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.jt.annotation.RequiredCache;
import com.jt.util.ObjectMapperUtil;

import redis.clients.jedis.Jedis;

/*@Component
@Aspect
public class CacheDemo {
	@Autowired
	private Jedis jedis;
	@Around("@annotation(requiredCache)")
	public Object around(ProceedingJoinPoint jp,RequiredCache requiredCache) throws Throwable {
		String key=getKey(jp,requiredCache);
		String result = jedis.get(key);
		Object data =null;
		if(StringUtils.isEmpty(result)) {
			data = jp.proceed();
			String json = ObjectMapperUtil.toJson(data);
			if (requiredCache.secondes()==0)
				jedis.set(key, json);
			else
				jedis.setex(key, requiredCache.secondes(), json); 
			System.out.println("查询了数据库");
		}else {
			Class c=getClass(jp);
			data = ObjectMapperUtil.toObject(result, c);
			System.out.println("查询缓存");
		}
		return data;
	}
	private Class getClass(ProceedingJoinPoint jp) {
		// TODO Auto-generated method stub
		return null;
	}
	private String getKey(ProceedingJoinPoint jp, RequiredCache requiredCache) {
		// TODO Auto-generated method stub
		return null;
	}
}*/
