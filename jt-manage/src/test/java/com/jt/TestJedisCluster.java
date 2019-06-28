package com.jt;

import java.util.HashSet;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestJedisCluster {
	@Test
	public void test01() {
		HashSet<HostAndPort> sets = new HashSet<>();
		sets.add(new HostAndPort("192.168.92.132", 7000));
		sets.add(new HostAndPort("192.168.92.132", 7001));
		sets.add(new HostAndPort("192.168.92.132", 7002));
		sets.add(new HostAndPort("192.168.92.132", 7003));
		sets.add(new HostAndPort("192.168.92.132", 7004));
		sets.add(new HostAndPort("192.168.92.132", 7005));
		JedisCluster cluster = new JedisCluster(sets);
		cluster.set("1902", "集群搭建完成");
		System.out.println("获取集群数据:"+cluster.get("1902"));
		
	}
	@Test
	public void test02() {
		//int a=1/0;
		//System.out.println(a);
		System.out.println(Integer.MAX_VALUE+1);
	}
}