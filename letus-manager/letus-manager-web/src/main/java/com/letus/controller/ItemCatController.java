/**
 * File：ItemCatController.java
 * Package：com.letus.controller
 * Author：xfyin
 * Date：2016-11-26 下午2:48:44
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.EUTreeNode;
import com.letus.service.ItemCatService;

/**
 * 商品类目管理controller
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping(value = "/item/cat")
public class ItemCatController {
  
  /**
   * 注入itemCatService
   */
  @Autowired
  private ItemCatService itemCatService;
  
  @RequestMapping(value = "/list")
  @ResponseBody
  public List<EUTreeNode> getItemCatList(@RequestParam(value = "id", 
                                      required = true, defaultValue = "0") long parentId) {
    return itemCatService.getItemCatList(parentId);
  }
}
