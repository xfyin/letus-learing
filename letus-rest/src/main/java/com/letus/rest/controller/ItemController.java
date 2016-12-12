/**
 * File：ItemController.java
 * Package：com.letus.rest.controller
 * Author：xfyin
 * Date：2016-12-12 下午10:24:26
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.LetusResult;
import com.letus.rest.service.ItemService;

/**
 * 商品信息管理
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/item")
public class ItemController {
  
  /**
   * 注入service
   */
  @Autowired
  private ItemService itemService;
  
  /**
   * 查询商品基本信息
   * 
   * @param itemId
   *        商品ID
   * @return LetusResult
   */
  @RequestMapping("/baseinfo/{itemId}")
  @ResponseBody
  public LetusResult queryItemBaseInfo(@PathVariable long itemId) {
    return itemService.queryItemBaseInfo(itemId);
  }
  
  /**
   * 根据商品id获取商品描述信息
   * 
   * @param itemId
   *        商品id
   * @return LetusResult
   */
  @RequestMapping("/descinfo/{itemId}")
  @ResponseBody
  public LetusResult queryItemDescInfo(@PathVariable long itemId) {
    return itemService.queryItemDescInfo(itemId);
  }
}
