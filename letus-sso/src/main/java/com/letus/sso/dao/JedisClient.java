/**
 * File：JedisClient.java
 * Package：com.letus.rest.dao
 * Author：xfyin
 * Date：2016-12-6 下午11:48:52
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.sso.dao;

/**
 * jedis常用方法接口
 * 
 * @author xfyin
 * 
 */
public interface JedisClient {
  
  /**
   * 取数
   * 
   * @param key
   *        key
   * @return value
   */
  String get(String key);
  
  /**
   * key-vlaue存
   * 
   * @param key
   *        key
   * @param value
   *        value
   * @return
   */
  String set(String key, String value);
  
  /**
   * 取hash
   * 
   * @param hkey
   *        hkey
   * @param ke
   */
  String hget(String hkey, String key);
  
  /**
   * 存hashy
   * key
   * 
   * @return String
   * 
   * @param hkey
   *        hkey
   * @param key
   *        key
   * @param value
   *        value
   * @return long
   */
  long hset(String hkey, String key, String value);
  
  /**
   * 自增+1
   * 
   * @param key
   * @return
   */
  long incr(String key);
  
  /**
   * 自减
   * 
   * @param key
   *        key
   * @return 结果
   */
  long decr(String key);
  
  /**
   * 过期时间
   * 
   * @param key
   *        可key
   * @param seconds
   *        秒
   * @return 剩余时间 ， -2表示不存在了
   */
  long expire(String key, int seconds);
  
  /**
   * 看剩余时间，判断是否过期
   * 
   * @param key
   *        key
   * @return 剩余时间 ， -2表示不存在了
   */
  long ttl(String key);
  
  /**
   * 删除key对应的值
   * 
   * @param key
   *        key
   * @return long long
   */
  long del(String key);
  
  /**
   * hash 删除key对应的值
   * 
   * @param hkey
   *        hkey
   * @param key
   *        key
   * @return long
   */
  long hdel(String hkey, String key);
  
}
