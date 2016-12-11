/**
 * File：SearchServiceImpl.java
 * Package：com.letus.search.service.impl
 * Author：xfyin
 * Date：2016-12-11 下午12:35:17
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.SearchResult;
import com.letus.search.dao.SearchDao;
import com.letus.search.service.SearchService;

/**
 * 查询索引库service实现
 * 
 * @author xfyin
 * 
 */
@Service
public class SearchServiceImpl implements SearchService {
  
  /**
   * 注入dao
   */
  @Autowired
  private SearchDao searchDao;
  
  @Override
  public SearchResult search(String queryString, int page, int rows) throws SolrServerException {
    // 创建查询对象
    SolrQuery query = new SolrQuery();
    // 设置查询条件,不为空
    query.setQuery(queryString);
    // 设置分页，不为空
    query.setStart((page - 1) * rows);
    query.setRows(rows);
    // 设置默认搜索域，在配置文件中配置
    query.set("df", "item_keywords");
    // 设置高亮显示
    query.setHighlight(true);
    // 标题域高亮显示
    query.addHighlightField("item_title");
    // 高亮前缀
    query.setHighlightSimplePre("<em style=\"color:red\" >");
    // 后缀
    query.setHighlightSimplePost("</em>");
    // 执行查询
    SearchResult search = searchDao.search(query);
    // 总记录数
    long recordCount = search.getRecordCount();
    // 计算总页数
    long pageCount = recordCount / rows;
    if (recordCount % rows > 0) {
      pageCount++;
    }
    search.setPageCount(pageCount);
    search.setCurPage(page);
    return search;
  }
  
}
