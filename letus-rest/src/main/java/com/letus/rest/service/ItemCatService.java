/**
 * File：ItemCatService.java
 * Package：com.letus.rest.service
 * Author：xfyin
 * Date：2016-12-3 下午2:00:44
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.service;

import com.letus.rest.pojo.CatResult;

/**
 * 商品分类展示
 * 
 * @author xfyin
 * 
 */
public interface ItemCatService {
  
  /**
   * 查询出所有的商品类别
   * 
   * @return data
   */
  CatResult queryItemCatList();
}
