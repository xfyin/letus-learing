/**
 * File：ItemService.java
 * Package：com.letus.portal.service
 * Author：xfyin
 * Date：2016-12-13 下午8:15:39
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service;

import com.letus.portal.pojo.ItemInfo;

/**
 * 商品详情
 * 
 * @author xfyin
 * 
 */
public interface ItemService {
  
  /**
   * 根据商品id获取商品信息
   * 
   * @param itemId
   *        商品id
   * @return 商品信息
   */
  ItemInfo queryItemBaseInfoById(long itemId);
  
  /**
   * 根据商品id返回商品描述信息
   * 
   * @param itemId
   *        商品id
   * @return html字符串片段
   */
  String queryItemDescInfo(long itemId);
  
  /**
   * 根据商品id查询 商品规格参数
   * 
   * @param itemId
   *        商品id
   * @return html字符串片段
   */
  String queryItemParamItem(long itemId);
}
