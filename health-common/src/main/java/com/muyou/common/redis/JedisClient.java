package com.muyou.common.redis;

import java.util.List;

public interface JedisClient {

	//String
	String set(String key, String value);
	String get(String key);
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	Long del(String key);
	//Hash
	Long hset(String key, String field, String value);
	String hget(String key, String field);
	Long hdel(String key, String... field);
	//List
	Long rpush(String key, String value);
	Long lpush(String key, String value);
	List<String> lrange(String key, long row,long limit);
	String ltrim(String key, long row,long limit);
}
