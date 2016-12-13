/**
 * File：ItemController.java
 * Package：com.letus.portal.controller
 * Author：xfyin
 * Date：2016-12-13 下午8:31:17
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.letus.common.pojo.LetusResult;
import com.letus.portal.service.ItemService;

/**
 * 
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
   * 根据商品id获取商品基本信息
   * 
   * @param itemId
   *        商品id
   * @return ModelAndView
   */
  @RequestMapping("/{itemId}")
  @ResponseBody
  public ModelAndView queryItemBaseInfo(@PathVariable Long itemId) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("item", itemService.queryItemBaseInfoById(itemId));
    mv.setViewName("item");
    return mv;
  }
}
