/**
 * File：ItemController.java
 * Package：com.letus.controller
 * Author：xfyin
 * Date：2016-11-24 下午10:34:55
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.EUDataGridResult;
import com.letus.common.pojo.LetusResult;
import com.letus.pojo.TbItem;
import com.letus.service.ItemService;

/**
 * 商品管理
 * 
 * @author xfyin
 * 
 */
@RequestMapping(value = "/item")
@Controller
public class ItemController {
  
  @Autowired
  private ItemService itemService;
  
  /**
   * 通过接受 HttpServletRequest获取参数 查询信息
   * 
   * @param request
   *        请求
   * @return 商品信息
   */
  @RequestMapping(value = "/itemid")
  @ResponseBody
  public TbItem getItemById(HttpServletRequest request) {
    String id = request.getParameter("id");
    return itemService.getItemById(Long.valueOf(id));
  }
  
  /**
   * 通过注解的形式接受同名参数
   * 
   * @param itemid
   *        value和@PathVariable 需要相同的 名称
   * @return 商品信息
   */
  @RequestMapping(value = "{itemid}")
  @ResponseBody
  public TbItem getItemsById(@PathVariable long itemid) {
    return itemService.getItemById(itemid);
  }
  
  /**
   * 查询所有的商品信息，并分页显示
   * 
   * @param page
   *        第几页
   * @param rows
   *        每页显示多少条记录
   * @return EUDataGridResult
   */
  @RequestMapping(value = "/list")
  @ResponseBody
  public EUDataGridResult getItemList(int page, int rows) {
    return itemService.getItemList(page, rows);
  }
  
  /**
   * 创建商品+添加商品描述信息+添加商品规格信息
   * 
   * @param item
   *        商品信息（页面上序列化后，传入一个pojo对象，页面name属性和pojo中的属性一致）
   * @param desc
   *        商品描述信息
   * @param itemParam
   *        商品规格信息
   * @return 响应结果
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public LetusResult createItem(TbItem item, String desc, String itemParams) {
    try {
      return itemService.createItemAndItemDesc(item, desc, itemParams);
    } catch (Exception e) {
      e.printStackTrace();
      return LetusResult.build(100, "添加商品失败,请检查内容重新提交!");
    }
  }
  
}
