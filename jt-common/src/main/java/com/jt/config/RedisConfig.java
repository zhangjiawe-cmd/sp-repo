package com.jt.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
@PropertySource("classpath:/properties/jedis.properties")
public class RedisConfig {
	/*
	 * @Value("${jedis.host}") private String host;
	 * 
	 * @Value("${jedis.port}") private Integer port;
	 * 
	 * @Bean public Jedis jedis() { return new Jedis(host,port); }
	 */
	@Value("${redis.nodes}")
	private String redisNodes;
	//@Value("${redis.sentinel.masterName}")
	//private String masterName;
	//@Value("${redis.sentinels}")
	//private String jedisSentinelNodes;

	@Bean
	public JedisCluster jedisCluster() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		String[] nodes1 = redisNodes.split(",");
		for (String node : nodes1) {
			String ip = node.split(":")[0];
			int port = Integer.parseInt(node.split(":")[1]);
			nodes.add(new HostAndPort(ip, port));
		}
		return new JedisCluster(nodes);
	}
		/*@Bean
	public JedisSentinelPool jedisSentinelPool() {
		Set<String> sentinels = new HashSet<>();
		sentinels.add(jedisSentinelNodes);
		return new JedisSentinelPool(masterName, sentinels);
	}
	@Bean
	public ShardedJedis shardedJedis() {
		ArrayList<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		String[] nodes = redisNodes.split(",");
		for (String node : nodes) {
			String host = node.split(":")[0];
			int port = Integer.parseInt(node.split(":")[1]);
			JedisShardInfo info = new JedisShardInfo(host,port);
			shards.add(info);
		}
		return new ShardedJedis(shards);
	}*/

	
}