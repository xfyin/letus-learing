/**
 * File：OrderController.java
 * Package：com.letus.portal.controller
 * Author：xfyin
 * Date：2016-12-18 下午5:21:03
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
   * 展示购物车订单确认页面
   * 
   * @return 订单确认页面
   */
  @RequestMapping("/order-shopping")
  public String showOrderShopping() {
    return "order-shopping";
  }
}
