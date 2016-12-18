/**
 * File：Order.java
 * Package：com.letus.order.pojo
 * Author：xfyin
 * Date：2016-12-18 下午4:41:22
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.pojo;

import java.util.List;

import com.letus.pojo.TbOrder;
import com.letus.pojo.TbOrderItem;
import com.letus.pojo.TbOrderShipping;

/**
 * 订单pojo(根据接口文档创建)
 * 
 * @author xfyin
 * 
 */
public class Order extends TbOrder {
  
  /**
   * 订单明细列表--orderItems 需要和接口文档中的字符串一致，接触@requestBody转换StringToPojo
   */
  private List<TbOrderItem> orderItems;
  
  /**
   * 物流信息--orderShipping
   */
  private TbOrderShipping orderShipping;
  
  /**
   * @return orderItems
   */
  public List<TbOrderItem> getOrderItems() {
    return orderItems;
  }
  
  /**
   * @param orderItems
   *          set orderItems
   */
  public void setOrderItems(List<TbOrderItem> orderItems) {
    this.orderItems = orderItems;
  }
  
  /**
   * @return orderShipping
   */
  public TbOrderShipping getOrderShipping() {
    return orderShipping;
  }
  
  /**
   * @param orderShipping
   *          set orderShipping
   */
  public void setOrderShipping(TbOrderShipping orderShipping) {
    this.orderShipping = orderShipping;
  }
  
}
