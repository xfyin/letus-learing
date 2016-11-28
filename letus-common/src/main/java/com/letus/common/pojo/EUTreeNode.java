/**
 * File：EUTreeNode.java
 * Package：com.letus.common.pojo
 * Author：xfyin
 * Date：2016-11-26 下午2:11:29
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.common.pojo;

/**
 * EasyUI 树形控制节点
 * 
 * @author xfyin
 * 
 */
public class EUTreeNode {
  
  /**
   * 节点ID
   */
  private long id;
  
  /**
   * 显示节点文本
   */
  private String text;
  
  /**
   * 节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
   */
  private String state;
  
  /**
   * @return id
   */
  public long getId() {
    return id;
  }
  
  /**
   * @param id
   *        set id
   */
  public void setId(long id) {
    this.id = id;
  }
  
  /**
   * @return text
   */
  public String getText() {
    return text;
  }
  
  /**
   * @param text
   *        set text
   */
  public void setText(String text) {
    this.text = text;
  }
  
  /**
   * @return state
   */
  public String getState() {
    return state;
  }
  
  /**
   * @param state
   *        set state
   */
  public void setState(String state) {
    this.state = state;
  }
  
}
