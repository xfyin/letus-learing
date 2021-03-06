/**
 * File：JedisClientCluster.java
 * Package：com.letus.rest.dao.impl
 * Author：xfyin
 * Date：2016-12-7 上午12:14:46
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.sso.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisCluster;

import com.letus.sso.dao.JedisClient;


/**
 * jedis集群版
 * 
 * @author xfyin
 *
 */
public class JedisClientCluster implements JedisClient {
  
  @Autowired
  private JedisCluster jedisCluster;
  
  @Override
  public String get(String key) {
    return jedisCluster.get(key);
  }
  
  @Override
  public String set(String key, String value) {
    return jedisCluster.set(key, value);
  }
  
  @Override
  public String hget(String hkey, String key) {
    return jedisCluster.hget(hkey, key);
  }
  
  @Override
  public long hset(String hkey, String key, String value) {
    return jedisCluster.hset(key, key, value);
  }
  
  @Override
  public long incr(String key) {
    return jedisCluster.incr(key);
  }
  
  @Override
  public long decr(String key) {
    return jedisCluster.decr(key);
  }
  
  @Override
  public long expire(String key, int seconds) {
    return jedisCluster.expire(key, seconds);
  }
  
  @Override
  public long ttl(String key) {
    return jedisCluster.ttl(key);
  }

  @Override
  public long del(String key) {
    return jedisCluster.del(key);
  }

  @Override
  public long hdel(String hkey, String key) {
    return jedisCluster.hdel(key, key);
  }
  
}
