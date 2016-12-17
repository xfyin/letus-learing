/**
 * File：ShoppingController.java
 * Package：com.letus.portal.controller
 * Author：xfyin
 * Date：2016-12-17 下午10:48:40
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.letus.portal.service.ShoppingService;

/**
 * 购物车controller
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/shopping")
public class ShoppingController {
  
  /**
   * 注入service
   */
  @Autowired
  private ShoppingService shoppingService;
  
  @RequestMapping("/add/{itemId}")
  public String addShoppingItem(HttpServletRequest request, HttpServletResponse response,
                                @PathVariable long itemId, @RequestParam(defaultValue = "1") int num) {
    shoppingService.addItemToShopping(request, response, itemId, num);
    return "shoppingSuccess";
    
  }
  
}
