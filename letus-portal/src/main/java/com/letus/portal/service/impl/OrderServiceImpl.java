/**
 * File：OrderServiceImpl.java
 * Package：com.letus.portal.service.impl
 * Author：xfyin
 * Date：2016-12-18 下午6:12:26
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.HttpClientUtil;
import com.letus.common.utils.JsonUtils;
import com.letus.pojo.TbOrderItem;
import com.letus.portal.pojo.Order;
import com.letus.portal.service.OrderService;
import com.letus.portal.service.ShoppingService;

/**
 * 订单管理service
 * 
 * @author xfyin
 * 
 */
@Service
public class OrderServiceImpl implements OrderService {
  
  /**
   * 订单系统基础RUL
   */
  @Value("${ORDER_BASE_URL}")
  private String ORDER_BASE_URL;
  
  /**
   * ORDER_CREATE_URL
   */
  @Value("${ORDER_CREATE_URL}")
  private String ORDER_CREATE_URL;
  
  /**
   * 购物service
   */
  @Autowired
  private ShoppingService shoppingService;
  
  @Override
  public String createOrder(HttpServletRequest request, HttpServletResponse response, Order order) {
    try {
      String orderJson = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL,
          JsonUtils.objectToJson(order));
      LetusResult result = LetusResult.format(orderJson);
      if (result.getStatus() == 200) {
        
        // 将redis中的购物车信息清空
        List<TbOrderItem> orderItems = order.getOrderItems();
        for (TbOrderItem orderItem : orderItems) {
          shoppingService
              .deleteShoppintItem(request, response, Long.valueOf(orderItem.getItemId()));
        }
        
        return result.getData().toString();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
}
