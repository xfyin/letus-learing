/**
 * File：CartService.java
 * Package：com.letus.portal.service
 * Author：xfyin
 * Date：2016-12-17 下午9:27:47
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.letus.common.pojo.LetusResult;
import com.letus.common.pojo.ShoppingItem;

/**
 * 购物车service
 * 
 * @author xfyin
 * 
 */
public interface ShoppingService {
  
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
   * @return LetusResult
   */
  LetusResult addItemToShopping(HttpServletRequest request, HttpServletResponse response,
                                long itemId, int num);
  
  /**
   * 获取购物车商品列表
   * 
   * @param request
   *          请求
   * @return 商品列表
   */
  List<ShoppingItem> queryShoppingItemList(HttpServletRequest request);
}
