/**
 * File：OrderController.java
 * Package：com.letus.portal.controller
 * Author：xfyin
 * Date：2016-12-18 下午5:21:03
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.letus.common.pojo.ShoppingItem;
import com.letus.portal.pojo.Order;
import com.letus.portal.service.OrderService;
import com.letus.portal.service.ShoppingService;

/**
 * 订单管理controller
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/order")
public class OrderController {
  
  /**
   * 注入购物车service
   */
  @Autowired
  private ShoppingService shoppingService;
  
  /**
   * 订单service
   */
  @Autowired
  private OrderService orderService;
  
  /**
   * 展示购物车订单确认页面
   * 
   * @return 订单确认页面
   */
  @RequestMapping("/order-shopping")
  public ModelAndView showOrderShopping(HttpServletRequest request, ModelAndView mv) {
    // 获取购物车商品列表，并在页面展示
    List<ShoppingItem> shoppingItems = shoppingService.queryShoppingItemList(request);
    mv.addObject("shoppingList", shoppingItems);
    mv.setViewName("order-shopping");
    return mv;
  }
  
  /**
   * 提交订单
   * 
   * @param order
   *          订单信息
   * @param mv
   *          mv
   * @return ModelAndView
   */
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ModelAndView createOrder(HttpServletRequest request, HttpServletResponse response, Order order, ModelAndView mv) {
    try {
      String orderId = orderService.createOrder(request, response, order);
      mv.addObject("orderId", orderId);
      mv.addObject("payment", order.getPayment());
      mv.addObject("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
      mv.setViewName("success");
      return mv;
    }
    catch (Exception e) {
      e.printStackTrace();
      mv.addObject("message", "下单失败,请稍后重试！");
      mv.setViewName("error/exception");
      return mv;
    }
  }
  
}
