/**
 * File：CatNode.java
 * Package：com.letus.rest.pojo
 * Author：xfyin
 * Date：2016-12-3 下午1:53:11
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商品分类节点
 * 
 * @author xfyin
 * 
 */
public class CatNode {
  
  /**
   * 链接url @JsonProperty转换为json格式后，key值为‘u’,默认是url
   */
  @JsonProperty("u")
  private String url;
  
  /**
   * 分类名称
   */
  @JsonProperty("n")
  private String name;
  
  /**
   * 具体的分类项
   */
  @JsonProperty("i")
  private List<?> items;
  
  /**
   * @return url
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * @param url
   *        set url
   */
  public void setUrl(String url) {
    this.url = url;
  }
  
  /**
   * @return name
   */
  public String getName() {
    return name;
  }
  
  /**
   * @param name
   *        set name
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * @return items
   */
  public List<?> getItems() {
    return items;
  }
  
  /**
   * @param items
   *        set items
   */
  public void setItems(List<?> items) {
    this.items = items;
  }
  
}
