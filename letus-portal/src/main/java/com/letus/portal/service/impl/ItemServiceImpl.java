/**
 * File：ItemServiceImpl.java
 * Package：com.letus.portal.service.impl
 * Author：xfyin
 * Date：2016-12-13 下午8:17:05
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.HttpClientUtil;
import com.letus.portal.pojo.ItemInfo;
import com.letus.portal.service.ItemService;

/**
 * 商品详情service
 * 
 * @author xfyin
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {
  
  /**
   * 基础URL
   */
  @Value("${REST_BASE_URL}")
  private String REST_BASE_URL;
  
  /**
   * 商品基本信息url
   */
  @Value("${ITEM_BASE_INFO_URL}")
  private String ITEM_BASE_INFO_URL;
  
  /**
   * 商品描述信息URL
   */
  @Value("${ITEM_DESC_INFO_URL}")
  private String ITEM_DESC_INFO_URL;
  
  /**
   * 商品规格参数信息URL
   */
  @Value("${ITEM_PARAM_ITEM_INFO_URL}")
  private String ITEM_PARAM_ITEM_INFO_URL;
  
  @Override
  public ItemInfo queryItemBaseInfoById(long itemId) {
    
    try {
      String itemJson = HttpClientUtil.doGet(REST_BASE_URL + ITEM_BASE_INFO_URL + itemId);
      if (!StringUtils.isBlank(itemJson)) {
        LetusResult letusResult = LetusResult.formatToPojo(itemJson, ItemInfo.class);
        if (letusResult.getStatus() == 200) {
          return (ItemInfo) letusResult.getData();
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
}
