/**
 * File：CatResult.java
 * Package：com.letus.rest.pojo
 * Author：xfyin
 * Date：2016-12-3 下午1:58:09
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.pojo;

import java.util.List;

/**
 * 分类的根节点
 * 
 * @author xfyin
 * 
 */
public class CatResult {
  
  /**
   * 所有的数据
   */
  private List<?> data;
  
  /**
   * 无参构造器
   */
  public CatResult() {
    super();
  }
  
  /**
   * 分装data
   * 
   * @param data
   *        需要的分类
   */
  public CatResult(List<?> data) {
    super();
    this.data = data;
  }
  
  /**
   * @return data
   */
  public List<?> getData() {
    return data;
  }
  
  /**
   * @param data
   *        set data
   */
  public void setData(List<?> data) {
    this.data = data;
  }
  
}
