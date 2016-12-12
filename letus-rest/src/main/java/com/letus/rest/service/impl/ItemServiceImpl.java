/**
 * File：ItemServiceImpl.java
 * Package：com.letus.rest.service.impl
 * Author：xfyin
 * Date：2016-12-12 下午10:21:07
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.JsonUtils;
import com.letus.mapper.TbItemMapper;
import com.letus.pojo.TbItem;
import com.letus.rest.dao.JedisClient;
import com.letus.rest.service.ItemService;

/**
 * 商品信息管理service
 * 
 * @author xfyin
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {
  
  /**
   * 商品信息在redis中保存的key
   */
  @Value("{REDIS_ITEM_KEY}")
  private String REDIS_ITEM_KEY;
  
  /**
   * 商品过期时间
   */
  @Value("{REDIS_ITEM_EXPIRE}")
  private Integer REDIS_ITEM_EXPIRE;
  
  /**
   * 注入mapper
   */
  @Autowired
  private TbItemMapper itemMapper;
  
  /**
   * 注入缓存
   */
  @Autowired
  private JedisClient jedisClient;
  
  @Override
  public LetusResult queryItemBaseInfo(long itemId) {
    
    String key = REDIS_ITEM_KEY + ":" + itemId + ":base";
    
    // 从缓存中取,r若存在直接返回
    try {
      String itemBaseInfo = jedisClient.get(key);
      if (!StringUtils.isBlank(itemBaseInfo)) {
        return LetusResult.ok(JsonUtils.jsonToPojo(itemBaseInfo, TbItem.class));
      }
    }
    catch (Exception e1) {
      e1.printStackTrace();
    }
    
    // 根据商品id获取商品信息
    TbItem item = itemMapper.selectByPrimaryKey(itemId);
     
    try {
      // 把商品信息写入缓存，并设置key的有效期
      jedisClient.set(key, JsonUtils.objectToJson(item));
      jedisClient.expire(key, REDIS_ITEM_EXPIRE);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return LetusResult.ok(item);
  }
  
}
