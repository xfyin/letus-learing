/**
 * File：SearchDao.java
 * Package：com.letus.search.dao
 * Author：xfyin
 * Date：2016-12-11 上午11:42:25
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import com.letus.search.pojo.SearchResult;

/**
 * 查询索引库dao
 * 
 * @author xfyin
 * 
 */
public interface SearchDao {
  
  /**
   * 根据索引条件查询商品信息
   * 
   * @param query
   *        查询条件
   * @throws SolrServerException
   * @return 商品信息等
   */
  SearchResult search(SolrQuery query) throws SolrServerException;
  
}
