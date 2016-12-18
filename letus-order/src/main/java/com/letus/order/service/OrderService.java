/**
 * File：OrderService.java
 * Package：com.letus.order.service
 * Author：xfyin
 * Date：2016-12-18 下午3:51:13
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.order.service;

import java.util.List;

import com.letus.common.pojo.LetusResult;
import com.letus.pojo.TbOrder;
import com.letus.pojo.TbOrderItem;
import com.letus.pojo.TbOrderShipping;

/**
 * 订单管理service
 * 
 * @author xfyin
 * 
 */
public interface OrderService {
  
  /**
   * 创建订单，订单明细及物流信息
   * 
   * @param order
   *          订单
   * @param orderItems
   *          订单明细
   * @param orderShipping
   *          物流信息
   * @return LetusResult
   */
  LetusResult createOrder(TbOrder order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping);
}
