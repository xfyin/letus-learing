/**
 * File：JedisClientSingle.java
 * Package：com.letus.rest.dao.impl
 * Author：xfyin
 * Date：2016-12-6 下午11:59:21
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.sso.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.letus.sso.dao.JedisClient;

/**
 * 单机版接口实现
 * 
 * @author xfyin
 * 
 */
// @Repository 不用该注解需要在配置文件配置
public class JedisClientSingle implements JedisClient {
  
  /**
   * jedis池
   */
  @Autowired
  private JedisPool jedisPool;
  
  @Override
  public String get(String key) {
    Jedis pool = jedisPool.getResource();
    String value = pool.get(key);
    pool.close();
    return value;
  }
  
  @Override
  public String set(String key, String value) {
    Jedis pool = jedisPool.getResource();
    String str = pool.set(key, value);
    pool.close();
    return str;
  }
  
  @Override
  public String hget(String hkey, String key) {
    Jedis pool = jedisPool.getResource();
    String value = pool.hget(hkey, key);
    pool.close();
    return value;
  }
  
  @Override
  public long hset(String hkey, String key, String value) {
    Jedis pool = jedisPool.getResource();
    Long lon = pool.hset(hkey, key, value);
    pool.close();
    return lon;
  }
  
  @Override
  public long incr(String key) {
    Jedis pool = jedisPool.getResource();
    Long value = pool.incr(key);
    pool.close();
    return value;
  }
  
  @Override
  public long decr(String key) {
    Jedis pool = jedisPool.getResource();
    Long value = pool.decr(key);
    pool.close();
    return value;
  }
  
  @Override
  public long expire(String key, int seconds) {
    Jedis pool = jedisPool.getResource();
    Long value = pool.expire(key, seconds);
    pool.close();
    return value;
  }
  
  @Override
  public long ttl(String key) {
    Jedis pool = jedisPool.getResource();
    Long value = pool.ttl(key);
    pool.close();
    return value;
  }
  
  @Override
  public long del(String key) {
    Jedis pool = jedisPool.getResource();
    Long value = pool.del(key);
    pool.close();
    return value;
  }

  @Override
  public long hdel(String hkey, String key) {
    Jedis pool = jedisPool.getResource();
    Long value = pool.hdel(hkey, key);
    pool.close();
    return value;
  }
  
}
