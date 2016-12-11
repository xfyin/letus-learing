/**
 * File：SearchService.java
 * Package：com.letus.search.service
 * Author：xfyin
 * Date：2016-12-11 下午12:33:43
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.search.service;

import org.apache.solr.client.solrj.SolrServerException;

import com.letus.common.pojo.SearchResult;

/**
 * 查询索引库service
 * 
 * @author xfyin
 * 
 */
public interface SearchService {
  
  /**
   * 查询索引库
   * 
   * @param query
   *        查询条件
   * @param page
   *        第几页
   * @param rows
   *        每页显示记录数
   * @throws SolrServerException
   * @return SearchResult
   */
  SearchResult search(String query, int page, int rows) throws SolrServerException;
}
