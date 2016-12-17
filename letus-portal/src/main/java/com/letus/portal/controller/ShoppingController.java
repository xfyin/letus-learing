/**
 * File：ShoppingController.java
 * Package：com.letus.portal.controller
 * Author：xfyin
 * Date：2016-12-17 下午10:48:40
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.letus.common.pojo.ShoppingItem;
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
  
  /**
   * 添加商品到购物车
   * 
   * @param request
   *          请求
   * @param response
   *          响应
   * @param itemId
   *          商品id
   * @param num
   *          数量
   * @param modify
   *          ==1表示直接修改数量 ==0表示通过'+' 或者 '-'修改数量
   * @return 重定向到 {@link ShoppingController#showSucess()}
   */
  @RequestMapping("/add/{itemId}")
  public String addShoppingItem(HttpServletRequest request, HttpServletResponse response,
                                @PathVariable long itemId,
                                @RequestParam(defaultValue = "1") int num,
                                @RequestParam(defaultValue = "0") int modify) {
    shoppingService.addItemToShopping(request, response, itemId, num, modify);
    // 重定向到 【展示添加商品成功页面】, web.xml中配置了.do 也可以
    return "redirect:/shopping/success.html";
  }
  
  /**
   * 展示添加商品成功页面
   * 
   * @return 添加到购物车成功页面
   */
  @RequestMapping("/success")
  public String showSucess() {
    return "shoppingSuccess";
  }
  
  /**
   * 查看购物车，展示已经添加到购物车中的商品列表信息
   * 
   * @param request
   *          请求
   * @param response
   *          响应
   * @param mv
   *          ModelAndView
   * @return ModelAndView
   */
  @RequestMapping("/shopping")
  @ResponseBody
  public ModelAndView queryShoppingItemList(HttpServletRequest request, ModelAndView mv) {
    List<ShoppingItem> shoppingItems = shoppingService.queryShoppingItemList(request);
    mv.addObject("shoppingList", shoppingItems);
    mv.setViewName("shopping");
    return mv;
  }
  
  /**
   * 从购物车中删除商品信息
   * 
   * @param request
   *          请求
   * @param response
   *          响应
   * @param itemId
   *          商品id
   * @return 返回购物车 {@link ShoppingController#queryShoppingItemList(HttpServletRequest, ModelAndView)}
   */
  @RequestMapping("/delete/{itemId}")
  public String deleteShoppintItem(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable long itemId) {
    shoppingService.deleteShoppintItem(request, response, itemId);
    return "redirect:/shopping/shopping.html";
  }
  
}
