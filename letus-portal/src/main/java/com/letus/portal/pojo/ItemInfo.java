/**
 * File：ItemInfo.java
 * Package：com.letus.portal.pojo
 * Author：xfyin
 * Date：2016-12-13 下午9:47:53
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.pojo;

import org.apache.commons.lang3.StringUtils;

import com.letus.pojo.TbItem;

/**
 * 商品信息扩展 -- 多张图片
 * 
 * @author xfyin
 * 
 */
public class ItemInfo extends TbItem {
  
  /**
   * 数据库表中image字段可能存储了多张图片信息
   * 
   * @return 图片数组
   */
  public String[] getItems() {
    String image = getImage();
    if (StringUtils.isEmpty(image)) {
      return null;
    }
    return image.split(",");
  }
}
