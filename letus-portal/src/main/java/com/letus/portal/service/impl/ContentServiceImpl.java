/**
 * File：ContentServiceImpl.java
 * Package：com.letus.portal.service.impl
 * Author：xfyin
 * Date：2016-12-4 下午10:51:50
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.HttpClientUtil;
import com.letus.common.utils.JsonUtils;
import com.letus.pojo.TbContent;
import com.letus.portal.service.ContentService;

/**
 * 门户调用服务层服务，查询商品内容列表--广告位展示service实现
 * 
 * @author xfyin
 * 
 */
@Service
public class ContentServiceImpl implements ContentService {
  
  /**
   * 服务层  基础URL
   */
  @Value("${REST_BASE_URL}")
  private String REST_BASE_URL;
  
  /**
   * 首页大广告位URL
   */
  @Value("${REST_INDEX_AD_URL}")
  private String REST_INDEX_AD_URL;
  
  @SuppressWarnings("unchecked")
  @Override
  public String queryContentList() {
    // 调用服务层服务
    String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
    try {
      // 将字符串转换成LetusResult
      LetusResult letusResult = LetusResult.formatToList(result, TbContent.class);
      // 获取内容列表
      List<TbContent> contents = (List<TbContent>) letusResult.getData();
      // 创建一个jsp页面要求的pojo列表
      List<Map<String, Object>> resultList = new ArrayList<>();
      
      for (TbContent content : contents) {
        Map<String, Object> map = new HashMap<>();
        map.put("src", content.getPic());
        map.put("width", 670);
        map.put("height", 240);
        map.put("srcB", content.getPic2());
        map.put("widthB", 550);
        map.put("heightB", 240);
        map.put("alt", content.getSubTitle());
        map.put("herf", content.getUrl());
        resultList.add(map);
      }
      return JsonUtils.objectToJson(resultList);
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
}
