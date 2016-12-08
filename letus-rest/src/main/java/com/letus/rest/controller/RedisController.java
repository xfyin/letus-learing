/**
 * File：RedisController.java
 * Package：com.letus.rest.service.impl
 * Author：xfyin
 * Date：2016-12-8 下午10:01:15
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.LetusResult;
import com.letus.rest.service.RedisService;

/**
 * 缓存同步controller
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {
  
  /**
   * 注入service
   */
  @Autowired
  private RedisService redisService;
  
  /**
   * 缓存同步
   * 
   * @param contentCid
   *        商品分类id
   * @return LetusResult
   */
  @RequestMapping("/content/{contentCid}")
  @ResponseBody
  public LetusResult contentCacheSync(@PathVariable long contentCid) {
    return redisService.syncContent(contentCid);
  }
}
