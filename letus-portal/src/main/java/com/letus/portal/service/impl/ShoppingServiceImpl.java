/**
 * File：ShoppingServiceImpl.java
 * Package：com.letus.portal.service.impl
 * Author：xfyin
 * Date：2016-12-17 下午9:30:50
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.LetusResult;
import com.letus.common.pojo.ShoppingItem;
import com.letus.common.utils.CookieUtils;
import com.letus.common.utils.ExceptionUtil;
import com.letus.common.utils.HttpClientUtil;
import com.letus.common.utils.JsonUtils;
import com.letus.pojo.TbItem;
import com.letus.portal.service.ShoppingService;

/**
 * 购物车service实现
 * 
 * @author xfyin
 * 
 */
@Service
public class ShoppingServiceImpl implements ShoppingService {
  
  /**
   * 基础URL
   */
  @Value("${REST_BASE_URL}")
  private String REST_BASE_URL;
  
  /**
   * 商品基本信息基础URL
   */
  @Value("${ITEM_BASE_INFO_URL}")
  private String ITEM_BASE_INFO_URL;
  
  /**
   * 购物车cookie名称
   */
  @Value("${SHOPPING_ITEM_COOKIE_NAME}")
  public String SHOPPING_ITEM_COOKIE_NAME;
  
  @Override
  public LetusResult addItemToShopping(HttpServletRequest request, HttpServletResponse response,
                                       long itemId, int num) {
    // 1.取商品购物车列表，若该商品存在直接将num+原来的数量,否则调用用户的添加商品购物车逻辑
    List<ShoppingItem> shoppingItemList = this.getShoppingItemList(request);
    // 该商品信息是否在购物车存在
    boolean existsItem = false;
    // shoppingItemList 不会为空 new ArrayList()<>;
    for (ShoppingItem shoppingItem : shoppingItemList) {
      // 购物车中已经存在商品信息
      if (shoppingItem.getId() == itemId) {
        // 商品数量 = 已经存在的数量+ 新的数量
        shoppingItem.setNum(shoppingItem.getNum() + num);
        existsItem = true;
        break;
      }
    }
    
    // 该商品不存在购物车中，需要将商品添加到购物车
    if (!existsItem) {
      // 2.根据商品id查询商品基本信息（调用rest服务）
      // 购物车信息
      ShoppingItem shoppingItem = new ShoppingItem();
      try {
        String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_BASE_INFO_URL + itemId);
        LetusResult result = LetusResult.formatToPojo(json, TbItem.class);
        if (result.getStatus() == 200) {
          TbItem item = (TbItem) result.getData();
          shoppingItem.setId(itemId);
          String image = item.getImage();
          shoppingItem.setImage(image == null ? "" : image.split(",")[0]);
          shoppingItem.setNum(num);
          shoppingItem.setPrice(item.getPrice());
          shoppingItem.setTitle(item.getTitle());
          // 新商品添加到购物车
          shoppingItemList.add(shoppingItem);
        }
      }
      catch (Exception e) {
        e.printStackTrace();
        return LetusResult.build(102, ExceptionUtil.getStackTrace(e));
      }
    }
    // 购物车信息存到cookie中
    CookieUtils.setCookie(request, response, SHOPPING_ITEM_COOKIE_NAME,
        JsonUtils.objectToJson(shoppingItemList), true);
    return LetusResult.ok();
  }
  
  /**
   * 获取购物车中的商品信息列表
   * 
   * @param request
   *          请求
   * @param response
   *          响应
   * @return 商品列表
   */
  private List<ShoppingItem> getShoppingItemList(HttpServletRequest request) {
    String jsonCookie = CookieUtils.getCookieValue(request, SHOPPING_ITEM_COOKIE_NAME, true);
    if (StringUtils.isBlank(jsonCookie)) {
      // 避免空指针
      return new ArrayList<>();
    }
    try {
      return JsonUtils.jsonToList(jsonCookie, ShoppingItem.class);
    }
    catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
  
  @Override
  public List<ShoppingItem> queryShoppingItemList(HttpServletRequest request) {
    return this.getShoppingItemList(request);
  }
}
