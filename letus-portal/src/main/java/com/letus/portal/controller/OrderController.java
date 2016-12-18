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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.letus.common.pojo.ShoppingItem;
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
   * 展示购物车订单确认页面
   * 
   * @return 订单确认页面
   */
  @RequestMapping("/order-shopping")
  public ModelAndView showOrderShopping(HttpServletRequest request, ModelAndView mv) {
    //获取购物车商品列表，并在页面展示
    List<ShoppingItem> shoppingItems = shoppingService.queryShoppingItemList(request);
    mv.addObject("shoppingList", shoppingItems);
    mv.setViewName("order-shopping");
    return mv;
  }
}
