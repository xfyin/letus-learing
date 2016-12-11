/**
 * File：Item.java
 * Package：com.letus.search.pojo
 * Author：xfyin
 * Date：2016-12-10 下午9:35:40
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.search.pojo;

/**
 * solr索引 商品pojo
 * 
 * @author xfyin
 * 
 */
public class Item {
  
  /**
   * 商品id
   */
  private String id;
  
  /**
   * 商品标题
   */
  private String title;
  
  /**
   * 商品买点
   */
  private String sellPoint;
  
  /**
   * 价格
   */
  private long price;
  
  /**
   * 商品图片
   */
  private String image;
  
  /**
   * 商品分类名称
   */
  private String categoryName;
  
  /**
   * 商品描述
   */
  private String itemDesc;
  
  /**
   * @return id
   */
  public String getId() {
    return id;
  }
  
  /**
   * @param id
   *        set id
   */
  public void setId(String id) {
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
   * @return sellPoint
   */
  public String getSellPoint() {
    return sellPoint;
  }
  
  /**
   * @param sellPoint
   *        set sellPoint
   */
  public void setSellPoint(String sellPoint) {
    this.sellPoint = sellPoint;
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
  
  /**
   * @return categoryName
   */
  public String getCategoryName() {
    return categoryName;
  }
  
  /**
   * @param categoryName
   *        set categoryName
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
  
  /**
   * @return itemDesc
   */
  public String getItemDesc() {
    return itemDesc;
  }
  
  /**
   * @param itemDesc
   *        set itemDesc
   */
  public void setItemDesc(String itemDesc) {
    this.itemDesc = itemDesc;
  }
  
}
