package com.jt;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestRedis {
	//String类型操作方式
	//IP:端口号
	@Test
	public void testString() {
		Jedis jedis = new Jedis("192.168.92.132",6379);
		jedis.set("1902", "1902班");
		jedis.expire("1902", 10);
		System.out.println(jedis.get("1902"));		
	}
	@Test
	public void testTimeOut() throws InterruptedException {
		Jedis jedis = new Jedis("192.168.92.132",6379);
		jedis.setex("aa",10,"aa");		
		System.out.println(jedis.get("aa"));
		
		Thread.sleep(3000);
		//当key不存在时操作正常,当key存在时则操作失败
		jedis.setnx("aa","bb");
		System.out.println("获取输出数据:"+jedis.get("aa"));
	}
	@Test
	public void objectToJSON() throws IOException {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(1000L).setItemDesc("测试方法");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(itemDesc);
		System.out.println(json);
		ItemDesc desc2 = mapper.readValue(json, ItemDesc.class);
		System.out.println("测试对象:"+desc2);
	}
	@Test
	public void listToJSON() throws IOException {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(1000L).setItemDesc("测试方法");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(itemDesc);
		System.out.println(json);
		ItemDesc desc2 = mapper.readValue(json, ItemDesc.class);
		System.out.println("测试对象:"+desc2);
	}
	@Test
	public void list() {
		Jedis jedis = new Jedis("192.168.92.132",6379);
		jedis.lpush("list", "1","2","3","4");
		System.out.println(jedis.lpop("list"));
		System.out.println(jedis.lpop("list"));
		System.out.println(jedis.lpop("list"));
		System.out.println(jedis.lpop("list"));
	}
	@Test
	public void TestTx() {
		Jedis jedis = new Jedis("192.168.92.132",6379);
		Transaction transaction = jedis.multi();
		try {
			transaction.set("ww", "wwww");
			transaction.set("dd", null);
			transaction.exec();
		} catch (Exception e) {
			e.printStackTrace();
			//transaction.discard();
		}
	}
}
