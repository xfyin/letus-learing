/**
 * File：ItemParamItemService.java
 * Package：com.letus.service
 * Author：xfyin
 * Date：2016-11-30 下午11:09:26
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service;

/**
 * 商品规格参数
 * 
 * @author xfyin
 * 
 */
public interface ItemParamItemService {
  
  /**
   * 根据商品id获取商品规格参数信息
   * 
   * @param itemId
   *        商品id
   * @return 展示的html
   */
  String getItemParamItemByItemId(long itemId);
}
