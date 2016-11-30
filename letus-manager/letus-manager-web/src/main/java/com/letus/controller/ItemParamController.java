/**
 * File：ItemParamController.java
 * Package：com.letus.controller
 * Author：xfyin
 * Date：2016-11-29 上午12:05:48
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.LetusResult;
import com.letus.pojo.TbItemParam;
import com.letus.service.ItemParamService;


/**
 * 商品规格参数模板管理
 * 
 * @author xfyin
 *
 */
@Controller
@RequestMapping(value="/item/param")
public class ItemParamController {
  
  /**
   * 注入service
   */
  @Autowired
  private ItemParamService itemParamService;
  
  @RequestMapping(value="/query/itemcatid/{itemCatId}")
  @ResponseBody
  public LetusResult getItemParamByCid(@PathVariable long itemCatId) {
    return itemParamService.getItemParamByCid(itemCatId);
  }
  
  
  @RequestMapping(value="/save/{cid}" , method = RequestMethod.POST)
  @ResponseBody
  public LetusResult saveItemParam(@PathVariable long cid, String paramData) {
    TbItemParam itemParam = new TbItemParam();
    itemParam.setParamData(paramData);
    itemParam.setItemCatId(cid);
    return itemParamService.addItemParam(itemParam);
  }
  
}
