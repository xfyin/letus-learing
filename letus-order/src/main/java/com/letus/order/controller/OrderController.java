/**
 * File：OrderController.java
 * Package：com.letus.order.controller
 * Author：xfyin
 * Date：2016-12-18 下午4:36:10
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.ExceptionUtil;
import com.letus.order.pojo.Order;
import com.letus.order.service.OrderService;
import com.letus.pojo.TbOrderItem;
import com.letus.pojo.TbOrderShipping;

/**
 * 订单管理controller
 * 
 * @author xfyin
 * 
 */
@Controller
public class OrderController {
  
  /**
   * 注入service
   */
  @Autowired
  private OrderService orderService;
  
  /**
   * 创建订单信息
   * 其中@RequestBody将json格式的字符串转换成pojo同@ResponseBody作用恰好相反
   * 
   * @param order
   *          订单、订单明细、物流信息
   * @return LetusResult
   */
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @ResponseBody
  public LetusResult createOrder(@RequestBody Order order) {
    
    // 订单明细
    List<TbOrderItem> orderItems = order.getOrderItems();
    // 物流信息
    TbOrderShipping orderShipping = order.getOrderShipping();
    try {
      // 包含订单号
      return orderService.createOrder(order, orderItems, orderShipping);
    }
    catch (Exception e) {
      e.printStackTrace();
      return LetusResult.build(102, ExceptionUtil.getStackTrace(e));
    }
  }
}
