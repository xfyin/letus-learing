/**
 * File：ShoppingItem.java
 * Package：com.letus.common.pojo
 * Author：xfyin
 * Date：2016-12-17 下午9:41:43
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.common.pojo;

/**
 * 购物陈pojo
 * 
 * @author xfyin
 * 
 */
public class ShoppingItem {
  
  /**
   * 商品id
   */
  private long id;
  
  /**
   * 商品标题
   */
  private String title;
  
  /**
   * 购买的商品数量
   */
  private int num;
  
  /**
   * 价格
   */
  private long price;
  
  /**
   * 商品图片
   */
  private String image;
  
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
   * @return title
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * @param title
   *        set title
   */
  public void setTitle(String title) {
    this.title = title;
  }
  
  /**
   * @return num
   */
  public int getNum() {
    return num;
  }
  
  /**
   * @param num
   *        set num
   */
  public void setNum(int num) {
    this.num = num;
  }
  
  /**
   * @return price
   */
  public long getPrice() {
    return price;
  }
  
  /**
   * @param price
   *        set price
   */
  public void setPrice(long price) {
    this.price = price;
  }
  
  /**
   * @return image
   */
  public String getImage() {
    return image;
  }
  
  /**
   * @param image
   *        set image
   */
  public void setImage(String image) {
    this.image = image;
  }
  
}
