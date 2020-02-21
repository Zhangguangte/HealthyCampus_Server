package com.muyou.common.redis;

import java.util.List;

import redis.clients.jedis.JedisCluster;

public class JedisClientCluster implements JedisClient {

	private JedisCluster jedisCluster;

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}

	@Override
	public Long expire(String key, int seconds) {
		return jedisCluster.expire(key, seconds);
	}

	@Override
	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	@Override
	public Long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return jedisCluster.hset(key, field, value);
	}

	@Override
	public String hget(String key, String field) {
		return jedisCluster.hget(key, field);
	}

	@Override
	public Long hdel(String key, String... field) {
		return jedisCluster.hdel(key, field);
	}

	@Override
	public Long rpush(String key, String value) {
		return jedisCluster.rpush(key, value);
	}

	@Override
	public List<String> lrange(String key, long row,long limit) {
		return jedisCluster.lrange(key, row, limit);
	}

	@Override
	public String ltrim(String key, long row, long limit) {
		return jedisCluster.ltrim(key, row, limit);
	}

	@Override
	public Long lpush(String key, String value) {
		return jedisCluster.lpush(key, value);
	}

	@Override
	public Long del(String key) {
		return jedisCluster.del(key);
	}

	@Override
	public String flushAll() {
		return jedisCluster.flushAll();
	}

	@Override
	public List<String> hvals(String key) {
		return jedisCluster.hvals(key);
	}
}
