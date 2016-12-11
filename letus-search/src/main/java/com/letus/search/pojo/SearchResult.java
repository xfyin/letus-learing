/**
 * File：SearchResult.java
 * Package：com.letus.search.pojo
 * Author：xfyin
 * Date：2016-12-11 上午11:37:50
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.search.pojo;

import java.util.List;

/**
 * 查询索引库dao层返回的查询结果
 * 
 * @author xfyin
 * 
 */
public class SearchResult {
  
  /**
   * 商品列表
   */
  private List<Item> items;
  
  /**
   * 记录总数
   */
  private long recordCount;
  
  /**
   * 总页数
   */
  private long pageCount;
  
  /**
   * 当前页码
   */
  private long curPage;
  
  /**
   * @return items
   */
  public List<Item> getItems() {
    return items;
  }
  
  /**
   * @param items
   *        set items
   */
  public void setItems(List<Item> items) {
    this.items = items;
  }
  
  /**
   * @return recordCount
   */
  public long getRecordCount() {
    return recordCount;
  }
  
  /**
   * @param recordCount
   *        set recordCount
   */
  public void setRecordCount(long recordCount) {
    this.recordCount = recordCount;
  }
  
  /**
   * @return pageCount
   */
  public long getPageCount() {
    return pageCount;
  }
  
  /**
   * @param pageCount
   *        set pageCount
   */
  public void setPageCount(long pageCount) {
    this.pageCount = pageCount;
  }
  
  /**
   * @return curPage
   */
  public long getCurPage() {
    return curPage;
  }
  
  /**
   * @param curPage
   *        set curPage
   */
  public void setCurPage(long curPage) {
    this.curPage = curPage;
  }
  
}
