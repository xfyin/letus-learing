/**
 * File：EUDataGridResult.java
 * Package：com.letus.common.pojo
 * Author：xfyin
 * Date：2016-11-26 下午1:01:35
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.common.pojo;

import java.util.List;

/**
 * 返回EasyUI DataGrid支持的数据格式
 * 
 * @author xfyin
 * 
 */
public class EUDataGridResult {
  
  /**
   * 总数
   */
  private long total;
  
  /**
   * 查询出的集合信息
   */
  private List<?> rows;
  
  /**
   * 默认
   */
  public EUDataGridResult() {
    super();
  }
  
  /**
   * 构造器
   * 
   * @param total
   *        总数
   * @param rows
   *        list
   */
  public EUDataGridResult(long total, List<?> rows) {
    super();
    this.total = total;
    this.rows = rows;
  }
  
  /**
   * @return total
   */
  public long getTotal() {
    return total;
  }
  
  /**
   * @param total
   *        set total
   */
  public void setTotal(long total) {
    this.total = total;
  }
  
  /**
   * @return rows
   */
  public List<?> getRows() {
    return rows;
  }
  
  /**
   * @param rows
   *        set rows
   */
  public void setRows(List<?> rows) {
    this.rows = rows;
  }
  
}
