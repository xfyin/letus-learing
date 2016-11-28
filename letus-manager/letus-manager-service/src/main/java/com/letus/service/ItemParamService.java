/**
 * File：ItemParamService.java
 * Package：com.letus.service
 * Author：xfyin
 * Date：2016-11-28 下午11:56:47
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service;

import com.letus.common.pojo.LetusResult;

/**
 * 商品规格参数模板service
 * 
 * @author xfyin
 * 
 */
public interface ItemParamService {
  
  /**
   * 查询表，返回查询结果
   * 
   * @param cid
   *        商品分类id
   * @return LetusResult
   */
  LetusResult getItemParamByCid(long cid);
}
