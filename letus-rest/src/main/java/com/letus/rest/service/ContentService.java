/**
 * File：ContentService.java
 * Package：com.letus.rest.service
 * Author：xfyin
 * Date：2016-12-4 下午8:53:11
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.service;

import java.util.List;

import com.letus.pojo.TbContent;

/**
 * 首页展示商品内容信息service
 * 
 * @author xfyin
 * 
 */
public interface ContentService {
  
  /**
   * 根据商品分类id查询商品内容
   * 
   * @param categoryId
   *        商品分类id
   * @return LetusResult
   */
  List<TbContent> queryContentList(long categoryId);
}
