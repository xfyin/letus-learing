/**
 * File：SearchService.java
 * Package：com.letus.portal.service
 * Author：xfyin
 * Date：2016-12-11 下午10:34:47
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service;

import com.letus.common.pojo.SearchResult;

/**
 * 商品搜索service
 * 
 * @author xfyin
 * 
 */
public interface SearchService {
  
  /**
   * 商品搜索
   * 
   * @param queryString
   *        查询条件
   * @param page
   *        当前页
   * @return SearchResult商品信息
   */
  SearchResult search(String queryString, int page);
}
