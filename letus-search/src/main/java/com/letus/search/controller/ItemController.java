/**
 * File：ItemController.java
 * Package：com.letus.search.controller
 * Author：xfyin
 * Date：2016-12-11 上午10:52:05
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.LetusResult;
import com.letus.search.service.ItemService;

/**
 * 
 * 索引库维护---发布服务controller
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/manager")
public class ItemController {
  
  /**
   * 注入service
   */
  @Autowired
  private ItemService itemService;
  
  /**
   * 导入所有商品信息到索引库
   * 
   * @return LetusResult
   */
  @RequestMapping("/importall")
  @ResponseBody
  public LetusResult importAllItems() {
    return itemService.importAllItems();
  }
}
