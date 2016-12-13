/**
 * File：ItemController.java
 * Package：com.letus.portal.controller
 * Author：xfyin
 * Date：2016-12-13 下午8:31:17
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
  public ModelAndView queryItemBaseInfo(@PathVariable Long itemId) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("item", itemService.queryItemBaseInfoById(itemId));
    mv.setViewName("item");
    return mv;
  }
  
  /**
   * 根据商品id获取商品描述信息(html片段有汉字，需要解决乱码)
   * 
   * @param itemId
   *        商品id
   * @return ModelAndView
   */
  @RequestMapping(value = "/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
  @ResponseBody
  public String queryItemDescInfo(@PathVariable Long itemId) {
    return itemService.queryItemDescInfo(itemId);
  }
  
  /**
   * 根据商品id获取商品规格参数信息(html片段有汉字，需要解决乱码)
   * 
   * @param itemId
   *        商品id
   * @return ModelAndView
   */
  @RequestMapping(value = "/param/{itemId}", produces = MediaType.TEXT_HTML_VALUE
      + ";charset=utf-8")
  @ResponseBody
  public String queryItemParamItem(@PathVariable Long itemId) {
    return itemService.queryItemParamItem(itemId);
  }
}
