/**
 * File：ContentServiceImpl.java
 * Package：com.letus.rest.service.impl
 * Author：xfyin
 * Date：2016-12-4 下午8:54:45
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.letus.common.utils.JsonUtils;
import com.letus.mapper.TbContentMapper;
import com.letus.pojo.TbContent;
import com.letus.pojo.TbContentExample;
import com.letus.pojo.TbContentExample.Criteria;
import com.letus.rest.dao.JedisClient;
import com.letus.rest.service.ContentService;

/**
 * 首页展示商品内容信息service实现
 * 
 * @author xfyin
 * 
 */
@Service
public class ContentServiceImpl implements ContentService {
  
  /**
   * 首页内容信息在redis中保存的key
   */
  @Value("${INDEX_CONTENT_REDIS_KEY}")
  private String INDEX_CONTENT_REDIS_KEY;
  
  /**
   * 注入 contentMapper
   */
  @Autowired
  private TbContentMapper contentMapper;
  
  /**
   * 单机版jedis dao
   */
  @Autowired
  private JedisClient jedisClientSingle;
  
  @Override
  public List<TbContent> queryContentList(long categoryId) {
    
    // 从缓存中取内容
    try {
      String getContentsList = jedisClientSingle.hget(INDEX_CONTENT_REDIS_KEY, categoryId + "");
      // 缓存中取出去数据
      if (!StringUtils.isEmpty(getContentsList)) {
        return JsonUtils.jsonToList(getContentsList, TbContent.class);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    //缓存中没有数据时，从数据库查
    TbContentExample example = new TbContentExample();
    Criteria criteria = example.createCriteria();
    criteria.andCategoryIdEqualTo(categoryId);
    List<TbContent> contents = contentMapper.selectByExample(example);
    
    // 添加到缓存
    try {
      // 先将list转换成字符串
      String addContentsList = JsonUtils.objectToJson(contents);
      // 添加缓存
      jedisClientSingle.hset(INDEX_CONTENT_REDIS_KEY, categoryId + "", addContentsList);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return contents;
  }
  
}
