/**
 * File：SearchDaoImpl.java
 * Package：com.letus.search.dao.Impl
 * Author：xfyin
 * Date：2016-12-11 上午11:45:10
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.letus.common.pojo.Item;
import com.letus.common.pojo.SearchResult;
import com.letus.search.dao.SearchDao;

/**
 * 查询索引库dao实现
 * 
 * @author xfyin
 * 
 */
@Repository
public class SearchDaoImpl implements SearchDao {
  
  @Autowired
  private SolrServer solrServer;
  
  @Override
  public SearchResult search(SolrQuery query) throws SolrServerException {
    SearchResult result = new SearchResult();
    // 商品列表信息
    List<Item> items = new ArrayList<>();
    QueryResponse response = solrServer.query(query);
    // 取高亮显示
    Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
    // 获取索引查询结果
    SolrDocumentList results = response.getResults();
    // 总记录数
    result.setRecordCount(results.getNumFound());
    for (SolrDocument solrDocument : results) {
      // 单个商品信息
      Item item = new Item();
      Object id = solrDocument.get("id");
      item.setId((String) id);
      // 标题高亮信息
      List<String> list = highlighting.get(id).get("item_title");
      String title = "";
      // 若高亮信息存在，取高亮
      if (list != null && list.size() > 0) {
        title = list.get(0);
      }
      // 不存在高亮，取原来的信息
      else {
        title = (String) solrDocument.get("item_title");
      }
      item.setTitle(title);
      item.setSellPoint((String) solrDocument.get("item_sell_point"));
      item.setPrice((long) solrDocument.get("item_price"));
      item.setImage((String) solrDocument.get("item_image"));
      item.setCategoryName((String) solrDocument.get("item_category_name"));
      // 商品描述没有存
      // item.setItemDesc((String) solrDocument.get("item_desc"));
      items.add(item);
    }
    result.setItems(items);
    return result;
  }
  
}
