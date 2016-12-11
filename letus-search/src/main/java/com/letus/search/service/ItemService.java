/**
 * File：ItemService.java
 * Package：com.letus.search.service
 * Author：xfyin
 * Date：2016-12-10 下午10:03:49
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.search.service;

import com.letus.common.pojo.LetusResult;

/**
 * solr索引库service接口
 * 
 * @author xfyin
 * 
 */
public interface ItemService {
  
  /**
   * 导入所有的商品信息
   * 
   * @return LetusResult
   */
  LetusResult importAllItems();
}
