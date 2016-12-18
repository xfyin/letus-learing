/**
 * File：OrderService.java
 * Package：com.letus.portal.service
 * Author：xfyin
 * Date：2016-12-18 下午6:10:12
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.letus.portal.pojo.Order;

/**
 * 订单管理
 * 
 * @author xfyin
 * 
 */
public interface OrderService {
  
  /**
   * 创建订单
   * 
   * @param order
   *          订单信息
   * @return 提交订单成功页面
   */
  String createOrder(HttpServletRequest request , HttpServletResponse response ,Order order);
}
