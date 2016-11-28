/**
 * File：ItemService.java
 * Package：com.letus.service
 * Author：xfyin
 * Date：2016-11-24 下午9:16:26
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service;

import com.letus.common.pojo.EUDataGridResult;
import com.letus.common.pojo.LetusResult;
import com.letus.pojo.TbItem;

/**
 * 商品相关service
 * 
 * @author xfyin
 */
public interface ItemService {
  
  /**
   * 根据商品id获取商品信息
   * 
   * @param itemId
   *        商品id
   * @return 商品信息
   */
  TbItem getItemById(long itemId);
  
  /**
   * 查询出所有的商品信息并分页显示
   * 
   * @param page
   *        第几页
   * @param rows
   *        每页显示多少条记录
   * @return EUDataGridResult
   */
  EUDataGridResult getItemList(int page, int rows);
  
  /**
   * 创建商品信息和商品描述信息
   * 
   * @param item
   *        商品信息
   * @param desc
   *        商品描述信息
   * @return 响应格式
   */
  LetusResult createItemAndItemDesc(TbItem item, String desc);
  
}
