/**
 * File：ItemServiceImpl.java
 * Package：com.letus.search.service.impl
 * Author：xfyin
 * Date：2016-12-10 下午10:05:08
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.Item;
import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.ExceptionUtil;
import com.letus.search.mapper.ItemMapper;
import com.letus.search.service.ItemService;

/**
 * 
 * solr索引库service实现
 * 
 * @author xfyin
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {
  
  /**
   * 注入mapper
   */
  @Autowired
  private ItemMapper itemMapper;
  
  /**
   * 注入server
   */
  @Autowired
  private SolrServer solrServer;
  
  @Override
  public LetusResult importAllItems() {
    // 查询所有商品信息
    List<Item> items = itemMapper.queryItemList();
    // 将商品信息导入索引库
    // 创建一个solrServer,并写入索引库
    try {
      for (Item item : items) {
        // 创建一个SolrInputDocument对象
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", item.getId());
        document.addField("item_title", item.getTitle());
        document.addField("item_sell_point", item.getSellPoint());
        document.addField("item_price", item.getPrice());
        document.addField("item_image", item.getImage());
        document.addField("item_category_name", item.getCategoryName());
        document.addField("item_desc", item.getItemDesc());
        solrServer.add(document);
      }
      // 提交到索引库
      solrServer.commit();
    }
    catch (Exception e) {
      e.printStackTrace();
      return LetusResult.build(102, ExceptionUtil.getStackTrace(e));
    }
    return LetusResult.ok();
  }
}
