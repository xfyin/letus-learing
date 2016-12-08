/**
 * File：RedisServiceImpl.java
 * Package：com.letus.rest.service.impl
 * Author：xfyin
 * Date：2016-12-8 下午9:52:54
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.ExceptionUtil;
import com.letus.rest.dao.JedisClient;
import com.letus.rest.service.RedisService;

/**
 * redis对外提供服务实现
 * 
 * @author xfyin
 * 
 */
@Service
public class RedisServiceImpl implements RedisService {
  
  @Value("${INDEX_CONTENT_REDIS_KEY}")
  private String INDEX_CONTENT_REDIS_KEY;
  
  /**
   * 单机版redis
   */
  @Autowired
  private JedisClient jedisClientSingle;
  
  @Override
  public LetusResult syncContent(long contentCid) {
    try {
      jedisClientSingle.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
      return LetusResult.ok();
    }
    catch (Exception e) {
      e.printStackTrace();
      return LetusResult.build(102, ExceptionUtil.getStackTrace(e));
    }
  }
  
}
