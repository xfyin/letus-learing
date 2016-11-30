/**
 * File：ItemParamItemController.java
 * Package：com.letus.controller
 * Author：xfyin
 * Date：2016-11-30 下午11:38:15
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.letus.service.ItemParamItemService;

/**
 * 商品规格参数信息controller
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping(value = "/paramitem")
public class ItemParamItemController {
  
  /**
   * 注入service
   */
  @Autowired
  private ItemParamItemService itemParamItemService;
  
  /**
   * 展示商品规格参数信息
   * 
   * @param itemId
   *        商品id
   * @return html
   */
  @RequestMapping(value = "/show/{itemId}")
  public ModelAndView showItemParamItems(@PathVariable long itemId, ModelAndView model) {
    String string = itemParamItemService.getItemParamItemByItemId(itemId);
    model.addObject("itemParam", string);
    model.setViewName("item_param");
    return model;
  }
}
