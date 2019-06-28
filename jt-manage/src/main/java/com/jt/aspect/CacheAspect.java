package com.jt.aspect;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jt.annotation.RequiredCache;
import com.jt.enu.KEY_ENUM;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.RedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedis;
@Component
@Aspect
public class CacheAspect {
	//private RedisService jedis;
	@Autowired(required=false)
	private JedisCluster jedis;
	//容器初始化时不需要实例化
	//只有用户使用时才初始化
	//一般工具类中添加该注解
	//@Autowired(required = false)
	//private Jedis jedis;
	//@Autowired(required = false)
	//private ShardedJedis jedis;
	//池1000个连接
	//@Autowired(required=false)//第三方工具api
	//private JedisSentinelPool pool;
	@Around("@annotation(requiredCache)")
	public Object around(ProceedingJoinPoint jp,RequiredCache requiredCache) throws Throwable{
		//1.获取key的值
		String key=getKey(jp,requiredCache);
		String result = jedis.get(key);
		//List<EasyUITree> treeList = new ArrayList<>();
		Object data=null;
		if(StringUtils.isEmpty(result)) {
			//treeList = findItemCatByParentId(parentId);
			data = jp.proceed();  
			String json = ObjectMapperUtil.toJson(data);
			//判断用户是否设定了超时时间
			if(requiredCache.secondes()==0) 
				jedis.set(key, json);
			else
				jedis.setex(key, requiredCache.secondes(), json);
			System.out.println("业务查询数据库");
			
		}else {
			Class targetC=getTargetClass(jp);
			data = ObjectMapperUtil.toObject(result, targetC);
			System.out.println("查询缓存");
		}
		return data;
	}
	private Class getTargetClass(ProceedingJoinPoint jp) {
		MethodSignature methodSignature=(MethodSignature) jp.getSignature();
		return methodSignature.getReturnType();
	}
	/**
	 * 1.判断用户key的类型
	 * @param jp
	 * @param requiredCache
	 * @return
	 */
	private String getKey(ProceedingJoinPoint jp, RequiredCache requiredCache) {
		//1.获取key的类型
		KEY_ENUM key_ENUM = requiredCache.keyType();
		//2.判断key的类型
		if(key_ENUM.equals(KEY_ENUM.EMPTY)) {
			return requiredCache.key();
		}else {
			String key=requiredCache.key()+"_"+String.valueOf(jp.getArgs()[0]);
			return key;
		}
	}

}
