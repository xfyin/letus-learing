/**
 * File：RedisService.java
 * Package：com.letus.rest.service
 * Author：xfyin
 * Date：2016-12-8 下午9:51:24
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.service;

import com.letus.common.pojo.LetusResult;

/**
 * redis对外提供服务接口
 * 
 * @author xfyin
 * 
 */
public interface RedisService {
  
  /**
   * redis同步缓存
   * 
   * @param contentCid
   *        商品分类id
   * @return LetusResult
   */
  LetusResult syncContent(long contentCid);
}
