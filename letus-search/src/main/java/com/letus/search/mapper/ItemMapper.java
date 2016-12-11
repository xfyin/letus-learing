/**
 * File：ItemMapper.java
 * Package：com.letus.search.mapper
 * Author：xfyin
 * Date：2016-12-10 下午9:47:08
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.search.mapper;

import java.util.List;

import com.letus.common.pojo.Item;

/**
 * 查询商品信息mapper
 * 
 * @author xfyin
 * 
 */
public interface ItemMapper {
  
  /**
   * 获取商品信息
   * 
   * @return 信息集合
   */
  List<Item> queryItemList();
}
