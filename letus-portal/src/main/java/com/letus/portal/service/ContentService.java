/**
 * File：ContentService.java
 * Package：com.letus.portal.service
 * Author：xfyin
 * Date：2016-12-4 下午10:50:34
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service;

/**
 * 门户，商品内容--广告位展示
 * 
 * @author xfyin
 * 
 */
public interface ContentService {
  
  /**
   * 获取商品内容列表
   * 
   * @return json字符串
   */
  String queryContentList();
}
