/**
 * File：ItemCatController.java
 * Package：com.letus.rest.controller
 * Author：xfyin
 * Date：2016-12-3 下午2:44:35
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.utils.JsonUtils;
import com.letus.rest.pojo.CatResult;
import com.letus.rest.service.ItemCatService;

/**
 * 
 * 前台商品分类展示
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/itemcat")
public class ItemCatController {
  
  /**
   * 注入service
   */
  @Autowired
  private ItemCatService itemCatService;
  
  /**
   * 方法一：返回 商品分类
   * produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8" 指定了json格式且编码是utf-8
   * 
   * @param callBack
   *        jsonp跨域调用js,callBack时回调函数
   * @return 将封装的json数据传递到回调函数中返回，这样调用者获取这个回调js回调函数后就能立即执行js
   */
  @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
  @ResponseBody
  public String queryItemCatList(String callback) {
    // 需要将下面的pojo转换为字符串
    CatResult catResult = itemCatService.queryItemCatList();
    String json = JsonUtils.objectToJson(catResult);
    // 拼装返回值
    return callback + "(" + json + ");";
  }
  
  /**
   * 方法二：返回 商品分类（spring 4.1后支持）
   * @param callback
   * @return
   */
  @RequestMapping(value = "/list2")
  @ResponseBody
  public Object queryItemCatListOther(String callback) {
    // 需要将下面的pojo转换为字符串
    CatResult catResult = itemCatService.queryItemCatList();
    MappingJacksonValue jacksonValue = new MappingJacksonValue(catResult);
    jacksonValue.setJsonpFunction(callback);
    return jacksonValue;
  }
  
}
