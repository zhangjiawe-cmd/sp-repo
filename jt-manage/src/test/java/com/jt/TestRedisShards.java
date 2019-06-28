package com.jt;

import java.util.ArrayList;

import org.junit.Test;

import com.jt.util.ObjectMapperUtil;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class TestRedisShards {
	@Test
	public void testShards() {
		ArrayList<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo info1 = new JedisShardInfo("192.168.92.132",6379);
		JedisShardInfo info2 = new JedisShardInfo("192.168.92.132",6380);
		JedisShardInfo info3 = new JedisShardInfo("192.168.92.132",6381);
		shards.add(info1);
		shards.add(info2);
		shards.add(info3);
		ShardedJedis shardedJedis = new ShardedJedis(shards);
		shardedJedis.set("1902", ObjectMapperUtil.toJson(info1));
		System.out.println(shardedJedis.get("1902"));
	}
}
