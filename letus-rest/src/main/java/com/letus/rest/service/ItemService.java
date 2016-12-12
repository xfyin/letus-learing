/**
 * File：ItemService.java
 * Package：com.letus.rest.service
 * Author：xfyin
 * Date：2016-12-12 下午10:19:27
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.service;

import com.letus.common.pojo.LetusResult;

/**
 * 商品信息
 * 
 * @author xfyin
 * 
 */
public interface ItemService {
  
  /**
   * 获取商品基本信息
   * 
   * @param itemId
   *        商品id
   * @return LetusResult
   */
  LetusResult queryItemBaseInfo(long itemId);
  
  /**
   * 根据商品id查询商品描述信息
   * 
   * @param itemId
   *        商品id
   * @return LetusResult
   */
  LetusResult queryItemDescInfo(long itemId);
  
  /**
   * 根据商品id查询商品规格参数信息
   * 
   * @param itemId
   *        商品id
   * @return LetusResult
   */
  LetusResult queryItemParamItem(long itemId);
}
