/**
 * File：SearchServiceImpl.java
 * Package：com.letus.portal.service.impl
 * Author：xfyin
 * Date：2016-12-11 下午10:36:50
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.LetusResult;
import com.letus.common.pojo.SearchResult;
import com.letus.common.utils.HttpClientUtil;
import com.letus.common.utils.JsonUtils;
import com.letus.portal.service.SearchService;

/**
 * 商品搜索service实现
 * 
 * @author xfyin
 * 
 */
@Service
public class SearchServiceImpl implements SearchService {
  
  /**
   * 调用的基础URL
   */
  @Value("${SEARCH_BASE_URL}")
  private String SEARCH_BASE_URL;
  
  @Override
  public SearchResult search(String queryString, int page) {
    // 调用letus-search服务
    // 查询参数
    Map<String, String> params = new HashMap<>();
    params.put("q", queryString);
    params.put("page", page + "");
    // 调用服务
    try {
      String json = HttpClientUtil.doGet(SEARCH_BASE_URL, params);
      // 將json字符串转换成Java对象
      LetusResult letusResult = LetusResult.formatToPojo(json, SearchResult.class);
      if (letusResult.getStatus() == 200) {
        return (SearchResult) letusResult.getData();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
}
