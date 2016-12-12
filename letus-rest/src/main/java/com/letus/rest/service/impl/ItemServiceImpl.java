/**
 * File：ItemServiceImpl.java
 * Package：com.letus.rest.service.impl
 * Author：xfyin
 * Date：2016-12-12 下午10:21:07
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.LetusResult;
import com.letus.mapper.TbItemMapper;
import com.letus.rest.service.ItemService;

/**
 * 商品信息管理service
 * 
 * @author xfyin
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {
  
  /**
   * 注入mapper
   */
  @Autowired
  private TbItemMapper itemMapper;
  
  @Override
  public LetusResult queryItemBaseInfo(long itemId) {
    return LetusResult.ok(itemMapper.selectByPrimaryKey(itemId));
  }
  
}
